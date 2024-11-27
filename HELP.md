The application uses H2 in memory DB for convenience.
It uses Kotlin 1.9.25 and Springboot 3.3.5.
OpenApi version 3.
It is available to see under:

http://localhost:8080/swagger-ui/index.html

Api docs available under:
http://localhost:8080/v3/api-docs

The db console is available under:

http://localhost:8080/h2_console

JDBC URL - jdbc:h2:mem:testdb
USER NAME - sa
Password - password

Through the console all 3 tables will be seen filled with test data.


The APIS implemented are taken from:
https://popshop.atlassian.net/wiki/external/Y2ZlNDViODdkYmY4NDg5OWE0MjgwNjdiMTFjMTZjYjg

The internal endpoint for adding show times and prices should have at least Basic Auth implemented through Spring Security - I haven't done that because of time restrictions