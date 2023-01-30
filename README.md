# Spring-JPA-Unit-Testing-Presentation-Demo

Basic login-register web app to demonstrate how to use JPA and JDBC with Spring Boot. This is a demo for my presentation on unit testing with JPA and JDBC with Spring Boot.

# Requirements

- [Docker](https://docs.docker.com/get-docker/)
- [Postgresql (v15.0)](https://www.postgresql.org/download/)
- [Intellij (EAP) *preferred*](https://www.jetbrains.com/toolbox-app/)
- [Spring Boot (v2.7.5)](https://spring.io/quickstart)
- [Maven](https://maven.apache.org/index.html)
- [Java SDK (v17.0.5)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Github](https://git-scm.com/downloads)

# Features

* Login page
* Registration page
* Will add more in the future

# Tools
  * Basic
    * JUnit5
    * AssertJ
    * Spring Testing
  * Advance
    * Mockito
    * Database Rider
    * Flyway
    * Data Faker
    * Test Container


# Getting Started

 1. Download and install Docker, Postgresql, Java with their appropriate versions.
 2. Open the application then enter the command at terminal/shell
    ```sh
        docker-compose up
    ```
 3. check application.properties in the `/src/main/resources` and configure the database connection 
     ```  
        spring.jpa.hibernate.ddl-auto=create
        spring.jpa.show-sql=false
        spring.datasource.url=jdbc:postgresql://localhost:<port>/<db-name>
        spring.datasource.username=<username>
        spring.datasource.password=<password>
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     ```
 4. Build then run the application (Automatically creates table needed to store data if it does not exist)
    
# Issues

- Login page functionality is not yet implemented but basic query is already implemented in the `UserRepository`
- Registration page functionality is not yet implemented
- H2 or hsqldb?
- H2 for unit test
- [Data Faker](https://github.com/datafaker-net/datafaker/)
- javax validation

# Todo
- Add test container for integration testing

# References
 - [Thymeleaf](https://www.baeldung.com/thymeleaf-in-spring-mvc)
 - [How to use spring security](https://www.baeldung.com/security-none-filters-none-access-permitAll)
 - [Make a registration page with spring](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security)
 - [Making a login page with spring](https://www.baeldung.com/spring-security-login)
 - [JPA and JDBC with Spring Guide](https://www.jpa-buddy.com/blog/spring-data-jpa-to-spring-data-jdbc-a-smooth-ride/)
 - [JPA and JDBC comparison](https://www.baeldung.com/jpa-vs-jdbc)