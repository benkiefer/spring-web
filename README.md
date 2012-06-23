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
 - Have a MYSQL database server running. (For help, see: http://www.mysql.com/)
 - Specify the following environment variables with appropriate values: DB_URL, DB_USERNAME, DB_PASSWORD

To run the app:

1. Check out and build the app (https://github.com/kingOburgers/spring-web)
2. From the checkout directory, cd to the mvc-example directory.
3. Start jetty (mvn jetty:run)
4. Browse to the home page (http://localhost:8080/mvc-example/)
5. Have fun.

* You can install this jar by building: https://github.com/kingOburgers/maven-support

[![Build Status](https://secure.travis-ci.org/kingOburgers/spring-web.png?branch=master)](http://travis-ci.org/kingOburgers/spring-web)