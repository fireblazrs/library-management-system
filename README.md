
# Library Management System

This is a Library Management System application that is built with Spring Boot which has a MySQL database and uses RabbitMQ. 

The application allows the user to create/save, update, delete and view books, book properties (genre, format), authors,  users and rooms. It automatically generates tasks which can be queried for status updates when an attempt to borrow a book is made. Admin and user roles have different levels of access in the application.
 

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

Additional features: 
MySQL database 

###How do I deploy it ?
###Option 1

Download the latest release [here](https://github.com/fireblazrs/library-management-system/pkgs/container/library-management-system)


####To run the application:
1. Install Docker Desktop: https://www.docker.com/products/docker-desktop/

###Option 2 

Download Docker Image:

    docker pull ghcr.io/fireblazrs/library-management-system:latest


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
