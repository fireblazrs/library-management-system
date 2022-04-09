
# Library Management System

This is a Library Management System application that is built with Spring Boot which has a MySQL database and uses RabbitMQ. 

The application allows the user to create/save, update, delete and view books, book properties (genre, format), authors,  users and rooms. It automatically generates tasks which can be queried for status updates when an attempt to borrow a book is made. Admin and user roles have different levels of access in the application.
 

### Start the application

```
docker-compose up --build
```
### Features Implemented
- Database relations
- Dockerfile/Docker compose
- Create, Read, Update and Delete operations
- Customized exceptions
- Spring Boot Security 
- RabbitMQ queue
- Integration Testing
- MySQL database 

### Download the Application

1. Download the latest release as a zip file [here](https://github.com/fireblazrs/library-management-system/pkgs/container/library-management-system).

2. Download Docker Image:
    ```
    docker pull ghcr.io/fireblazrs/library-management-system:latest
    ```

### Default Users

1. Access-level: "ADMIN"     
   Username: admin   
   Password: admin    


2. Access-level: "USER"     
   Username: user   
   Password: user
 

###Endpoints to use:

Default URL for the project: 

    http://localhost:8080

Endpoints for Book:

| HTTP   | URL             | Information             | Authorization      |
|--------|-----------------|-------------------------|--------------------|
| GET    | /api/books      | Shows all the books.    | Authenticated user |
| GET    | /api/books/{id} | Shows a book by the id. | Authenticated user |
| DELETE | /api/books/{id} | Deletes a book with id. | Authenticated user |
| PUT    | /api/books/{id} | Updates a book with id. | Authenticated user |
| POST   | /api/books      | Creates a book.         | Authenticated user |

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

| HTTP   | URL                   | Information                    | Authorization      |
|--------|-----------------------|--------------------------------|--------------------|
| GET    | /api/bookformats      | Shows all the book formats.    | Authenticated user |
| GET    | /api/bookformats/{id} | Shows a book format by the id. | Authenticated user |
| DELETE | /api/bookformats/{id} | Deletes a book format with id. | Authenticated user |
| PUT    | /api/bookformats/{id} | Updates a book format with id. | Authenticated user |
| POST   | /api/bookformats      | Creates a book format.         | Authenticated user |

POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "formatName": "Hard copy",
      "digital": "true/false",
      "pageCount": 33,
      "length": "5"
    }


Endpoints for Author:

| HTTP   | URL               | Information                | Authorization      |
|--------|-------------------|----------------------------|--------------------|
| GET    | /api/authors      | Shows all the authors.     | Authenticated user |
| GET    | /api/authors/{id} | Shows an author by the id. | Authenticated user |
| DELETE | /api/authors/{id} | Deletes an author with id. | Authenticated user |
| PUT    | /api/authors/{id} | Updates an author with id. | Authenticated user |
| POST   | /api/authors      | Creates an author.         | Authenticated user |
POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson",
    }


Endpoints for Genre:

| HTTP   | URL              | Information              | Authorization      |
|--------|------------------|--------------------------|--------------------|
| GET    | /api/genres      | Shows all the genres.    | Authenticated user |
| GET    | /api/genres/{id} | Shows a genre by the id. | Authenticated user |
| DELETE | /api/genres/{id} | Deletes a genre with id. | Authenticated user |
| PUT    | /api/genres/{id} | Updates a genre with id. | Authenticated user |
| POST   | /api/genres      | Creates a genre.         | Authenticated user |
POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "genreName": "Horror",
      "fiction": "true/false"
    }
______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
