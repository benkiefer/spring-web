Spring 3.0 Web Example
======================

A web app playground for spring 3.0.

Technologies used:
 - Groovy
 - Spring
 - Hibernate
 - Maven
 - Jetty

This build requires that you:
 - Have maven set up. (For help, see: http://maven.apache.org/)
 - Have an H2 database server running. (For help, see: http://www.h2database.com/html/main.html)
 - Have my custom maven enforcer rules jar installed in your maven local repo.*
 - Have the following environment variables: DB_URL, DB_USERNAME, DB_PASSWORD

To run the app:

1. Check out and build the app (https://github.com/kingOburgers/spring-web)
2. From the checkout directory, cd to the mvc-example directory.
3. Start jetty (mvn jetty:run)
4. Browse to the home page (http://localhost:8080/mvc-example/)
5. Have fun.

* You can install this jar by building: https://github.com/kingOburgers/maven-support

[![Build Status](https://secure.travis-ci.org/kingOburgers/pdf-processing.png?branch=master)](http://travis-ci.org/kingOburgers/pdf-processing)