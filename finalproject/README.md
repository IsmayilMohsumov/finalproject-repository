# Ismayil-Mid-Term-Assignment

Final Project

A chatbot appliaction has been built using Java Spring boot.
Features:
Register User -> send verification email
Login user -> if user is not verified email then don't let
Admin panel -> edit, add questions and see chats
And much more



## General info

This backend-service developed to present HCL team in order to prove my knowledge that I have gained during the training.


### Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Gradle](https://gradle.org/install/)


### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.midterm.exam.FinalprojectApplicaion` class from your IDE.

But first of all you need to configure `application.yml` with your database and then run :
```shell
cd project-folder
docker-compose up
```

In case you don't have `docker` you can change `application.yml` and just run the project

### Contribution
This project isn't open for contributions

### Authors
* **Ismayil Mohsumov**