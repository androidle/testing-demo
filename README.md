[![Platform](https://img.shields.io/badge/platform-android-brightgreen)](https://developer.android.com/reference)

# Testing Demo

## Table of Contents

- [Introduction](#introduction)
- [Testing](#testing)
- [Demo](#demo)
- [Libraries](#Libraries)
- [Plugins](#Plugins)

## Introduction

This project is to show the practice testing on android

## Testing

* Instrumentation testing
  with [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
* Screenshot testing
  with [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)
* Improve espresso tests
  with [Robot Pattern](https://academy.realm.io/posts/kau-jake-wharton-testing-robots)

## Demo

## Libraries

- [Espresso](https://developer.android.com/training/testing/espresso)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
- [screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android)

## Plugins

* Code coverage with [jacoco](https://github.com/jacoco/jacoco)
* Code scan with [sonarqube](https://github.com/SonarSource/sonar-scanning-examples)

    1. how to integrate for gradle refers
       to [sonarscanner-for-gradle](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/)
    2. To generate a report, we need to run a gradle command:
  ```
  ./gradlew :journeylib:jacocoTestReport sonarqube -Dsonar.host.url=http://localhost:9000/
  ```





