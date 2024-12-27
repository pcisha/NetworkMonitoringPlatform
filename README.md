# Network Monitoring Platform

### Overview

- The application monitors and manages global network usage data efficiently.
- It reads network usage data from a CSV file and loads it into a relational datastore (H2 database).
- Provides CRUD RESTful API operations to interact with the network usage data.


### Features

- CSV File Parser for data ingestion. Batch data processing supported.
- CRUD operations through RESTful APIs.
- H2 in-memory database for quick data storage and retrieval.
- Export data from the database to a CSV file.
- Health Check Endpoint: `GET http://localhost:8080/actuator/health`


### Technology Stack

- Java 8 / 18
- Spring Boot 2.7.16
- REST API with Jackson for JSON processing
- Maven 3.6.0 for build automation
- SQL for database queries
- H2 in-memory database for lightweight storage
- MVC Design Pattern for clean architecture


### Execution

To build and run the application:

1. Build the Project: ```mvn clean install -U```

2. Run the Application: ```mvn spring-boot:run```

3. Access the H2 Database Console:
   - Open a browser and navigate to: `http://localhost:8080/h2`
   - Use the following details to log in:
     - Datasource URL: `jdbc:h2:file:~/network_details`
       - Update the URL as per the primary datasource in `application.properties`.
       - Example: `spring.datasource.url=jdbc:h2:file:~/network_details`
     - Username and Password: As specified in the `application.properties` file.
   - Click on the table name and then click the 'Run' button to view records.


### APIs

- GET by ID: GET http://localhost:8080/network-monitoring-service/api/v1/network-details/{id}
- GAT ALL: GET http://localhost:8080/network-monitoring-service/api/v1/network-details/
- ADD: POST http://localhost:8080/network-monitoring-service/api/v1/network-details/
- UPDATE by ID: PUT http://localhost:8080/network-monitoring-service/api/v1/network-details/{id}
- DELETE by ID: DELETE http://localhost:8080/network-monitoring-service/api/v1/network-details/{id}

##

Date: December 26, 2024

Author: Prachi Shah @ https://pcisha.my.canva.site/

P.S. The default copyright laws apply.