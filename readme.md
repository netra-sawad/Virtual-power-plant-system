# Virtual Power Plant Spring Boot Project

This project is a Virtual Power Plant Spring Boot application.

## Dependencies

The project uses the following Spring Boot starters and dependencies:

- **Spring Boot Web Starter**: Provides the framework for building web applications.
- **Spring Boot Data JPA Starter**: Offers easy access to the database with JPA.
- **Spring Boot Validation Starter**: Adds validation support for RESTful services.
- **MySQL Connector**: Connects the application to the MySQL database. (optional)
- **H2 Database Connector** :Connects the H2 in-memory database. 
- **Lombok**: Reduces boilerplate code by auto-generating getters, setters, and more.
- **Spring Boot Test Starter**: Provides testing support for Spring Boot applications.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Maven
- MySQL Database (Optional)
- H2 database

## Getting Started

1. Clone the repository:

   ```sh
   git clone https://github.com/netra-sawad/virtual-power-plant-system.git
   cd virtual-power-plant-system

2. Configure MySQL Properties (Optional): 

   Open the src/main/resources/application.properties file in the project and update the properties according to your MySQL configuration

####
3. Build and run the application:
    
    ```sh 
   mvn spring-boot:run

4. To post the data, you can use the following `curl` command:

```sh
curl -X POST -H "Content-Type: application/json" -d '{
  "batteries": [
    {
      "name": "Cannington",
      "postcode": "6107",
      "capacity": 13500
    },
    {
      "name": "Midland",
      "postcode": "6057",
      "capacity": 50500
    },
    {
      "name": "Hay Street",
      "postcode": "6000",
      "capacity": 23500
    },
    {
      "name": "Mount Adams",
      "postcode": "6525",
      "capacity": 12000
    },
    {
      "name": "Koolan Island",
      "postcode": "6733",
      "capacity": 10000
    },
    {
      "name": "Armadale",
      "postcode": "6992",
      "capacity": 25000
    },
    {
      "name": "Lesmurdie",
      "postcode": "6076",
      "capacity": 13500
    },
    {
      "name": "Kalamunda",
      "postcode": "6076",
      "capacity": 13500
    },
    {
      "name": "Carmel",
      "postcode": "6076",
      "capacity": 36000
    },
    {
      "name": "Bentley",
      "postcode": "6102",
      "capacity": 85000
    },
    {
      "name": "Akunda Bay",
      "postcode": "2084",
      "capacity": 13500
    },
    {
      "name": "Werrington County",
      "postcode": "2747",
      "capacity": 13500
    },
    {
      "name": "Bagot",
      "postcode": "0820",
      "capacity": 27000
    },
    {
      "name": "Yirrkala",
      "postcode": "0880",
      "capacity": 13500
    },
    {
      "name": "University of Melbourne",
      "postcode": "3010",
      "capacity": 85000
    },
    {
      "name": "Norfolk Island",
      "postcode": "2899",
      "capacity": 13500
    },
    {
      "name": "Ootha",
      "postcode": "2875",
      "capacity": 13500
    },
    {
      "name": "Kent Town",
      "postcode": "5067",
      "capacity": 13500
    },
    {
      "name": "Northgate Mc",
      "postcode": "9464",
      "capacity": 13500
    },
    {
      "name": "Gold Coast Mc",
      "postcode": "9729",
      "capacity": 50000
    }
  ]
}' http://localhost:8080/power-plant/api/v1/batteries

```

4. To fetch the data, you can use the following `curl` command:
    ```sh 
   curl -H "Accept: application/json" -H "Content-Type: application/json" -X GET
    http://localhost:8080/power-plant/api/v1/batteries/6000-9000

5. Testing
    ```sh
   mvn test
6. Building and Packaging
    ```sh
   mvn clean package
