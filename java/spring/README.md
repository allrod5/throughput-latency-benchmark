# spring-boot-api-template

The purpose of this repository is to serve as a boilerplate for projects that require nonfunctional requirements very similar to those already implemented here.

The structure of this project is dedicated to an API project without GUI (Graphical user interface)

## Getting Started
Just create a fork of this project and start your own system.

For a complete explanation of the fork workflow, you can read it here: https://help.github.com/articles/fork-a-repo/

### Prerequisites

* [Java](https://www.java.com/) 11+
* [Gradle](https://gradle.org/) 5.2.1+

Obs.: to manage multiple Java/Gradle versions in your local environment, check [our wiki article](https://github.com/quintoandar/spring-boot-api-template/wiki/Managing-multiple-Java-and-Gradle-versions-(SDKMAN)) on that to setup [SDKMAN](https://sdkman.io/).


### Installing

On the terminal:
* Set the Java 11 (if you're not using SDKMAN)
```
export JAVA_HOME=<jdk-11-path>
export PATH=$JAVA_HOME/bin:$PATH
```
* If you are using IntelliJ, configure your checkstyle to be the project one
```
File -> Settings -> Editor -> Code Style -> Scheme -> Use Project
```
* Build the project
```
cd <path>/spring-boot-api-template/
gradle clean build
```
Run the project
```
cd api
gradle bootRun
```
* For Hot Reloading (Continuous Build Execution)
```
gradle clean build -t (use & to run in background)
gradle bootRun (in other console, if you didn't run clean build in brackground)
```

## Swagger
We are using Swagger 2 to document our APIs, with the Springfox implementation for Java. To access it, you can run the project and then access it on:
```
http://localhost:8080/swagger-ui.html
```
By default, all the controllers will be mapped in this interface. It can be used to test endpoints and understand which parameters are required to make a request to the API.

## Docker
If you want to use Docker to run your service, we have a Dockerfile example in the root directory:
```
spring-boot-api-template.dev.Dockerfile
```
To run it, just type: 
```
sudo docker build -t spring-template -f spring-template.dev.Dockerfile .
```

## Kubernetes
If you want to have advanced access to our Kubernetes cluster (to read logs, application state, etc), you can use the kubectl tool.

To use it, first you need to install the tool. Follow this Wiki: https://github.com/quintoandar/k8s/wiki/Kubectl

After installing it, you can use this command to see the list of available pods:
```
kubectl get po -n <env>
```
Then, after getting the id of the pod which you want to inspect:
```
kubectl logs <pod-id> <container> -n <env>
```
To check the metadata of a pod, you can describe it. It's useful to see the running version and the history of the deploy:
```
kubectl describe po <pod-id> <container> -n <env>
```
Using stern (https://github.com/wercker/stern):
```
stern <pod-id-regex> -c <container> -n <env>
```

## Kibana
Kibana is a front-end tool that can help you search for logs indexed into ElasticSearch. This application is configured with kubernets to verify the logs presented in the cluster. 
To search for logs in Kibana, go to [https://kibana.quintoandar.com.br/](https://kibana.quintoandar.com.br/) and in the discover tab, type:

Search for app: (Sample is the app name configured in kubernets sample file)
```
kubernetes.labels.app:*sample*
```

To search for logs presented in a specific environment:
```
kubernetes.labels.env:*forno*
```

Other useful keywords are: _app_, _env_, _timestamp_ and _message_ included in the JSON log structure

## Running the tests
* To run the code coverage
```
gradle jacocoTestReport
```
Check the report: <path>/spring-boot-api-template/<module>/build/reports/jacoco/test/html/index.html

### End to end tests
* To run tests:
```
gradle test
```

### Coding style tests
* To run the checkstyle
```
gradle checkstyleMain
```

### Dependencies upgrade check
* To verify if there are dependencies not up to date
```
gradle dependencyUpdates -Drevision=release
```

## Deployment
This project has a sample Kubernetes configuration. It's splitted in two parts: the kube folder, and the drone file.

[Drone](https://drone.quintoandar.com.br/) is the system we use to deploy our repositories.

The Drone file will execute the tests, build the project, publish its Docker image, push it to the Kubernetes pods, and then it will notify on your slack channel.

Tips:
* You need to ask for the Infrastructure team to create your repository on the ECR.
* If your first deploy fails on the kubernetes, you need to ask for the Infrastructure team to delete the pod. Otherwise it will never work.

## Hibernate
Our current DDL config is:
```
spring.jpa.hibernate.ddl-auto=update
```
The `update` mode will automatically make the changes on the schema based on our model objects. So, if you create a new Hibernate mapping on the project, it will be directly replicated to the database. To be sure that you are creating the correct tables and constraints, you will to use the correct annotations, like: @Collumn (with the size or nullable, for example), @Unique and others.

Note that if you remove a property on a class, it will not be removed from the database. So you will need to create a migration to drop the column.

## Built With

* [Java](https://www.java.com/) - Programming language
* [Gradle](https://gradle.org/) - Build automation tool
* [Spring](https://spring.io/) - Comprehensive programming and configuration model for modern Java-based applications
* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications
* [Hamcrest](http://hamcrest.org/) - Framework for writing matcher objects allowing 'match' rules to be defined declaratively. There are a number of situations where matchers are invaluable, such as UI validation, or data filtering, but it is in the area of writing flexible tests that matchers are most commonly used
* [Checkstyle](https://docs.gradle.org/5.2.1/userguide/checkstyle_plugin.html) - Performs quality checks on your project’s Java source files using [Checkstyle](http://checkstyle.sourceforge.net/index.html) and generates reports from these checks
* [JaCoCo](https://docs.gradle.org/5.2.1/userguide/jacoco_plugin.html) - The JaCoCo plugin provides code coverage metrics for Java code via integration with [JaCoCo](https://www.eclemma.org/jacoco/)
* [PMD](https://docs.gradle.org/5.2.1/userguide/pmd_plugin.html) - Performs quality checks on your project's Java source files using [PMD](https://pmd.github.io/). It finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth. Additionally include CPD (copy-paste-detector) that finds duplicated code.
* [SpotBugs](https://spotbugs.github.io/) SpotBugs is a program which uses static analysis to look for bugs in Java code.
* [Sonarqube](https://www.sonarqube.org/) - Sonarqube provides the capability to not only show health of an application but also to highlight issues newly introduced. Check the how to configure it [here](https://github.com/quintoandar/spring-boot-api-template/wiki/Sonarqube-Configuration). 
* [Sentry](https://sentry.io/) - Open-source error tracking that helps developers monitor and fix crashes in real time. Iterate continuously. Boost efficiency. Improve user experience.. Check the how to configure it [here](https://github.com/quintoandar/spring-boot-api-template/wiki/How-to-setup-Sentry). 
* [Kiabana](https://www.elastic.co/products/kibana) -  Is an open source data visualization plugin for Elasticsearch. It provides visualization capabilities on top of the content indexed on an Elasticsearch cluster. 
* [Swagger](https://swagger.io/) - Enabling development across the entire API lifecycle, from design and documentation, to test and deployment
* [git-properties](https://github.com/n0mer/gradle-git-properties) - Plugin that uses [spring-boot-actuator](https://github.com/spring-guides/gs-actuator-service) to give information about the current version that running trought the endpoint `/actuator/info`.
* [gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin) Plugin that provides a task to determine which dependencies have updates.
