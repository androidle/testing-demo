[![Platform](https://img.shields.io/badge/platform-android-brightgreen)](https://developer.android.com/reference)

# Testing Demo

## Table of Contents

- [Introduction](#introduction)
- [Testing](#testing)
- [Demo](#demo)
- [Libraries](#Libraries)
- [Plugins](#Plugins)
- [Issues](#Issues)

## Introduction

This project is to show the practice testing on android

## Testing

* Instrumentation testing with [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
    * How to start [mockwebserver-https](https://adambennett.dev/2021/09/mockwebserver-https/)
    * Test using a local HTTP server [http-server](https://github.com/http-party/http-server)
    * Google code labs[android-network-security-config](https://developer.android.com/codelabs/android-network-security-config)
    * others [HTTPS_TLS](https://www.mock-server.com/mock_server/HTTPS_TLS.html)
    * local-https-development[ssl-certificate-authority](https://deliciousbrains.com/ssl-certificate-authority-for-local-https-development/)
* Screenshot testing
  with [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)
* Improve espresso tests
  with [Robot Pattern](https://academy.realm.io/posts/kau-jake-wharton-testing-robots)

## Demo

## Libraries

- [Espresso](https://developer.android.com/training/testing/espresso)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)
- [Dagger2](https://dagger.dev/)

  1. The target reference ![App dependency diagram](dagger-dependency-diagram.png)
     that from the codelab [android-dagger](https://developer.android.com/codelabs/android-dagger)
  2. Base on #1 graph above, this leads to a new issue on ui testing of how to handle dependency inject?
     The FragmentScenario can't be used directly.
- [Hilt](https://developer.android.google.cn/training/dependency-injection/hilt-android)

    1. [Dagger to Hilt](https://developer.android.com/codelabs/android-dagger-to-hilt)
    2. [HiltDemo](https://github.com/googlecodelabs/android-hilt)

## Plugins

* Code coverage with [jacoco](https://github.com/jacoco/jacoco)
* Code scan with [sonarqube](https://github.com/SonarSource/sonar-scanning-examples)

    1. how to integrate for gradle refers
       to [sonarscanner-for-gradle](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/)
    2. To generate a report, we need to run a gradle command:
  ```
  ./gradlew :journeylib:jacocoTestReport sonarqube -Dsonar.host.url=http://localhost:9000/
  ```
* Publish android
  artifact [maven-publish-plugin](https://docs.gradle.org/current/userguide/publishing_maven.html)

    1. Google
       doc [Use the Maven Publish plugin](https://developer.android.com/studio/build/maven-publish-plugin)
    2. Code gist refers
       to [maven-publish-gradle](https://gist.github.com/Robyer/a6578e60127418b380ca133a1291f017)

* Code style with [ktlint](https://ktlint.github.io/)

## Issues
  ```
    java.lang.RuntimeException: Could not launch activity
  ```
  Fix by 'ActivityScenario.launch' instead of ActivityTestRule
