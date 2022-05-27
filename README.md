
This is a simple application that exposes endpoints to fetch and upload data from a csv file in a predefined format

## Prerequisites

Following platform is required to run the application:

- Java JDK 11
  - For example: <https://adoptopenjdk.net/releases.html?variant=openjdk8&jvmVariant=openj9>
  - Set `JAVA_HOME` system variable with a valid JDK path and add `${JAVA_HOME}/bin` to the PATH variable

- Maven
  - For example: <https://www.npmjs.com/package/@zowe/cli>

## Quick Start

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.test.naceapi.NaceApiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
