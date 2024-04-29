import java.time.Instant
import java.time.format.DateTimeFormatter

plugins {
    id("java-library")
}

group = "com.gradlelab"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Build-Unix-Timestamp" to Instant.now().epochSecond
        )
    }
}
