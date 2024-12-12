# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/servlet.html)
* [Rest Repositories](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/how-to/data-access.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Thymeleaf](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Apache Freemarker](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/spring-security.html)
* [OAuth2 Client](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/spring-security.html#web.security.oauth2.client)
* [OAuth2 Authorization Server](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/spring-security.html#web.security.oauth2.authorization-server)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/web/spring-security.html#web.security.oauth2.server)
* [JDBC API](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/data/sql.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/data/sql.html#data.sql.jdbc)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/data/nosql.html#data.nosql.redis)
* [Java Mail Sender](https://docs.spring.io/spring-boot/3.4.1-SNAPSHOT/reference/io/email.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

