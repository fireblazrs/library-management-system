
# Library Management System

This is a Spring Boot application that implements books, authors, users, tasks etc. that are gathered in a MySQL database. Each role has a different degree of access to the application.
 

### How do I start the application?

```
docker-compose up --build
```
###What features are implemented?
- Database relations
- Dockerfile/Docker compose
- Create, Read, Update and Delete operations
- Customized exceptions
- Spring Boot Security 
- RabbitMQ and JMS
- Integration Testing
- Thymeleaf 

Additional features: 
MySQL database 

###How do I deploy it ?
###Option 1

Clone the repository:

- Navigate to the folder where you want the application to be saved.

- Use your console to run the following:

   git clone https://github.com/fireblazrs/library-management-system

###Or

Navigate to the latest release: https://github.com/fireblazrs/library-management-system/pkgs/container/library-management-system

Save the compressed file to the location where you want to
store the application after unzipping it.

####To run the application:
1. Install Docker Desktop: https://www.docker.com/products/docker-desktop/
2. Navigate to the application's folder and run the following 
command from your console: 
docker-compose up

###Option 2 

Download Docker Image:

1. Install Docker Desktop:
   https://www.docker.com/products/docker-desktop/
2. Create a docker-compose.yml file as follows:
   
         version: "2.1"
         services:
           mysql:
             image: mysql:8.0.28
             restart: always
             ports:
               - "3307:3306"
             environment:
               MYSQL_ROOT_HOST: "%"
               MYSQL_ROOT_PASSWORD: root
               MYSQL_USER: user
               MYSQL_PASSWORD: password
               MYSQL_DATABASE: test
           server:
             build: .
             depends_on:
               - mysql
             ports:
               - "8080:8080"
             environment:
               DB_HOST: mysql
               DB_USER: user
               DB_PASSWORD: password
           rabbit_queue:
             image: rabbitmq:3-management
             ports:
               - "5672:5672"
               - "15672:15672"


####To run the application:
1. Navigate to the folder of the docker-compose.yml file and run the following
   command from your console:
   docker-compose up

###How do i reach the application? 

Run the endpoints below by using Insomnia

- Use Basic Auth:

  Admin:

       Username: 

       Password: 

  User:

       Username: 

       Password: 
 

###Endpoints to use:

All URLs for our API begin with:

    http://localhost:8080/api

Endpoints for Book:

| HTTP -verb    | URL | Information | Authorization
| -------- | -------- | ------ |  --------
| GET  | /books     | Shows all the books.  | Authenticated user
| GET | /books/{id}    | Shows a book by the id.   | Authenticated user
| DELETE    | /books/{id}    | Deletes a book with id.  | Authenticated user
| PUT  | /books/{id}      | Updates a book with id.  | Authenticated user
| POST | /books    | Creates a book. | Authenticated user

POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:


    {
      "title": "Ahsan, Vimbayi, Toni and Joel´s Book",
      "subtitle": "",
      "edition": 1,
      "printed": "2020-02-04",
      "isbn": 123456789
    }

Endpoints for BookFormat:

| HTTP -verb    | URL | Information | Authorization
| -------- | -------- | ------ |  --------
| GET  | /bookformats     | Shows all the book formats.  | Authenticated user
| GET | /bookformats/{id}    | Shows a book format by the id.   | Authenticated user
| DELETE    | /bookformats/{id}    | Deletes a book format with id.  | Authenticated user
| PUT  | /bookformats/{id}      | Updates a book format with id.   | Authenticated user
| POST | /bookformats   | Creates a book format. | Authenticated user

POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "formatName": "Hard copy",
      "digital": "true/false",
      "pageCount": 33,
      "length": "5"
    }

Endpoints for Author:

| HTTP -verb    | URL | Information | Authorization|
| -------- | -------- | ------ |  --------|
| GET  | /authors     | Shows all the authors.  | Authenticated user |
| GET | /authors/{id}    | Shows an author by the id.   | Authenticated user|
| DELETE    | /authors/{id}    | Deletes an author with id.  | Authenticated user|
| PUT  | /authors/{id}      | Updates an author with id.   | Authenticated user|
| POST | /authors   | Creates an author.  | Authenticated user|
POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson",
    }

Endpoints for Genre:

| HTTP -verb    | URL | Information | Authorization|
| -------- | -------- | ------ |  --------|
| GET  | /genres     | Shows all the genres.  | Authenticated user |
| GET | /genres/{id}    | Shows a genre by the id.   | Authenticated user|
| DELETE    | /genres/{id}    | Deletes a genre with id.  | Authenticated user|
| PUT  | /genres/{id}      | Updates a genre with id.   | Authenticated user|
| POST | /genres   | Creates a genre.  | Authenticated user|
POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "genreName": "Horror",
      "fiction": "true/false"
    }
______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
