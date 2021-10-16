# Rest API Endpoints for manging users and associated permisssions
A user should have at least the following attributes: family name, given name, birthdate, email, password

A user's permission should have at least the following attributes: type, granted date


The API should provide the endpoints to satisfy at least the following functional requirements:
list all users, add user ,remove user, get single user, grant permission for a user, revoke permission for a user, search users by family name 

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 8.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/learningisawesome/benevity_assessment.git
```

**2. Create Mysql database**
```bash
create database benevity
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql config


**4. Build and run the app using maven (eventsetracker)**
```bash
mvn clean package
```

NOTE: If you are running 'mvn clean package' subsequent times, make sure to Drop all the tables
```bash
DROP TABLE USERS_ROLES;DROP TABLE USERS;DROP TABLE roles;commit;
```

You can run the app on Tomcat by copying war present in target folder into $CATAliNE_HOME/webapps or  
Alternatively, you can start using spring-boot

```bash
mvn spring-boot:run
```

The spring boot will start running at <http://localhost:8082>, as the port is set to 8082
For Production deploy war file generated in target/ folder

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /register
    
    POST /process_register
    
    GET /users

    GET /users/edit/{id}

    GET /users/filter

    POST /users/save
    
    GET /users/delete/{id}

You can test them using postman or any other rest client.

The app will start running at <http://localhost:8082>.   

