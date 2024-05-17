# DPE University Training

<p align="left">
<img width="10%" height="10%" src="https://user-images.githubusercontent.com/120980/174325546-8558160b-7f16-42cb-af0f-511849f22ebc.png">
</p>

## Gradle Build Cache Deep Dive - Unstable Inputs Exercise

This is a hands-on exercise to go along with the
[Gradle Build Cache Deep Dive](https://dpeuniversity.gradle.com/app/courses/54469478-55ba-416d-9cef-3b5aa33dd2a5)
training module. In this exercise you will go over the following:

* Practice troubleshooting cache misses due to unstable inputs
* Get familiar with using input normalization

---
## Prerequisites

* Finished going through the troubleshooting section in [Gradle Build Cache Deep Dive](https://dpeuniversity.gradle.com/app/courses/54469478-55ba-416d-9cef-3b5aa33dd2a5)

---
## Develocity Authentication

If you haven't already done so, you can authenticate with the training Develocity service by running:

```shell
./gradlew provisionGradleEnterpriseAccessKey
```

The output of the task will indicate a browser window will come up from which you can complete the authentication:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/ccafa270-dbab-4c66-ba12-caabcd10399c">
</p>

Once the browser window comes up you can enter a title for the access key that will be created or go with the suggested title:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/1aeef46a-2fb6-472a-8d87-82af31b20799">
</p>

Once confirmed you will see the following message and you can close the browser window and return to the editor:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/1711c9db-814c-4df1-9d18-42fe5d1b82f8">
</p>

---
## Steps

1. Open the Gradle project in this repository in an editor of your choice
2. Run `./gradlew :app:test` twice. You will notice the task is not up-to-date

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/013aabed-a8b3-435c-beec-8a16bd931e35">
</p>

3. Create a couple of build scans to get insights: `./gradlew :app:test --scan`
4. Perform a build scan comparison if more insights are needed

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/88139bb8-f328-436d-899c-37c659e681f6">
</p>

5. Notice the `common-1.0.jar` file input changed for the `:app:test` task. This is produced by the `:common:jar` task, whose `MANIFEST.MF` file input changed:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/87bb519e-0290-461a-b1a5-2b1c5a444d7c">
</p>

6. Inspect the file `common/build/tmp/jar/MANIFEST.MF` to figure out what is changing. Notice the timestamp variable:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/96964cea-c618-4ea7-9427-519d9dd86563">
</p>

7. Go to the `common/build.gradle.kts` and look at the `jar` task configuration:

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/61826984-d5a3-4017-9029-8d74fb497e74">
</p>

8. Refer to the [input normalization doc](https://docs.gradle.org/current/userguide/incremental_build.html#sec:meta_inf_normalization) and add the appropriate configuration to `app/build.gradle.kts`

9. Run the `:app:test` task a couple of times to verify it's now `UP-TO-DATE`

<p align="center">
<img width="75%" height="75%" src="https://github.com/gradle/build-tool-training-exercises/assets/120980/6a3e4d5b-4093-4cc3-a693-a9bd65905609">
</p>

---
## Solution Reference

If you get stuck you can refer to the `solution` branch of this repository.