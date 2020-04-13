# Rest API with Postgres as a DB

## Overview

Repo contains java application to expose rest apis with Postgres as database.

## Requirements

  * Maven (3.5+)
  * Oracle Java JDK 1.8
  
## Installation

Checkout or fork the repository. Execute the Maven install goal to build the target .jar file.

## Technologies Used

  * Oracle Java 1.8
  * Spring dependency with spring boot
  * Maven
  * Jetbrains IntelliJ IDEA CE


## Operation

The service operation is normally automated but can be manually operated

  * Navigate to the filesystem path containing the assembled jar file
  * Use `mvn clean package` to generate the jar artifacts.
  * Use `java -jar dsi-api-1.0-SNAPSHOT.jar` at the command line to start the service.
  
## Database 

Following are the database configuration
  
  * URL `jdbc:postgresql://localhost:5432/postgres`
  * Driver class `org.postgresql.Driver`
  * Username `postgres`
  * Password `abc123`
  

## Server port 

  * 8081
  
## Security
  
  * username `user`
  * password `can be obtained from console logs`
