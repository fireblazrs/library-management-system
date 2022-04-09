
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
 

### Endpoints to use:

Default URL for the project: 

    http://localhost:8080

#### Endpoints for Book:

| HTTP   | Path             | Information             | Access Level | Status Code | Response Body |
|--------|-----------------|-------------------------|---------------|-------------|---------------|
| GET    | /api/books      | Shows all the books.    | User          | 200         | List of books |
| GET    | /api/books/{id} | Shows a book by the id. | User          | 200 / 404   | Book          |
| DELETE | /api/books/{id} | Deletes a book with id. | User          | 204         | -             |
| PUT    | /api/books/{id} | Updates a book with id. | User          | 200 / 404   | Updated book  |
| POST   | /api/books      | Creates a book.         | User          | 201         | New book      |

POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:


    {
      "title": "Ahsan, Vimbayi, Toni and Joel´s Book",
      "subtitle": "",
      "edition": 1,
      "printed": "2020-02-04",
      "isbn": 123456789
    }


#### Endpoints for BookFormat:

| HTTP   | Path                  | Information                    | Access Level | Status Code | Response Body   |
|--------|-----------------------|--------------------------------|--------------|-------------|-----------------|
| GET    | /api/bookformats      | Shows all the book formats.    | User         | 200         | List of formats |
| GET    | /api/bookformats/{id} | Shows a book format by the id. | User         | 200 / 404   | Format          |
| DELETE | /api/bookformats/{id} | Deletes a book format with id. | User         | 204         | -               |
| POST   | /api/bookformats      | Creates a book format.         | User         | 201         | New format      |

POST requires a body, as shown by the example below:

    {
      "formatName": "Hard copy",
      "digital": "false",
      "pageCount": 33,
      "length": "5"
    }


#### Endpoints for Author:

| HTTP   | Path              | Information                | Access Level | Status Code | Response Body   |
|--------|-------------------|----------------------------|--------------|-------------|:----------------|
| GET    | /api/authors      | Shows all the authors.     | User         | 200         | List of authors |
| GET    | /api/authors/{id} | Shows an author by the id. | User         | 200 / 404   | author          |
| DELETE | /api/authors/{id} | Deletes an author with id. | User         | 204         | -               |
| POST   | /api/authors      | Creates an author.         | User         | 201         | author          |
POST requires a body, as shown by the example below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson",
    }


#### Endpoints for Genre:

| HTTP   | Path              | Information              | Access Level      |
|--------|------------------|--------------------------|--------------------|
| GET    | /api/genres      | Shows all the genres.    | User |
| GET    | /api/genres/{id} | Shows a genre by the id. | User |
| DELETE | /api/genres/{id} | Deletes a genre with id. | User |
| PUT    | /api/genres/{id} | Updates a genre with id. | User |
| POST   | /api/genres      | Creates a genre.         | User |
POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:

    {
      "genreName": "Horror",
      "fiction": "true/false"
    }

"username": "adam",
"password": "**********",
"ssn": "YYYYMMDD-XXXX",
"email": "adamm@mail.com"

______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
