# Overview
The project is a practice for familiarising myself with Git, submodule, and full-stack development operations.

## tech stack
- Use Spring Boot (v2.7) as the back-end service
  - `javax.persistence` to map communicate with MySQL
  - `lombok` to generate default getters and setters behind the scene
  - `javax.transaction` to deal with database transactions
  - `JpaRepository` to support CRUD operations to the database
  - it's necessary to run the app with the `application.properties` file, and the code here is for demo purposes only
- Use MySQL workbench as the database
- Use Angular as the front-end
  - `FormGroup` with custom `validators`
  - [submodule](https://angular.io/guide/architecture-modules) for separating concerns and providing a clear, readable code
  - `lazy loading` child module for performance improvement
  - `typescript`
  - `Observable` for front-end micro-services and data sharing among several components
  - `Okta` for user authentication

## Submodule
To pull out a specific submodule branch for development:
`git submodule foreach git pull origin <BRANCH>`

## application.properties
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/full-stack-ecommerce?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=ecommerceapp
spring.datasource.password=ecommerceapp
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.data.rest.base-path=/api
allowed.origins=http://localhost:4200
spring.data.rest.detection-strategy=ANNOTATED
okta.oauth2.client-id={YourClientId}
okta.oauth2.client-secret={YourClientSecret}
okta.oauth2.issuer={YourIssuer}
```