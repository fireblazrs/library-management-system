
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

| HTTP   | Path                   | Information                    | Access Level | Status Code | Response Body |
|--------|------------------------|--------------------------------|--------------|-------------|---------------|
| GET    | /api/books             | Get all books                  | User         | 200         | List of books |
| GET    | /api/books?isbn={ISBN} | Get all books filtered by ISBN | User         | 200         | List of books |
| GET    | /api/books/{id}        | Get a book by the id           | User         | 200 / 404   | Book          |
| DELETE | /api/books/{id}        | Delete a book by id            | User         | 204         | -             |
| PUT    | /api/books/{id}        | Update a book by id            | User         | 200 / 404   | Updated book  |
| POST   | /api/books             | Create a book.                 | User         | 201 / 400   | New book      |

POST and PUT require a body, containing a JSON object.
An example body for POST is shown below:


    {
      "title": "Ahsan, Vimbayi, Toni and Joel´s Book",
      "subtitle": "",
      "edition": 1,
      "printed": "2020-02-04",
      "isbn": 123456789
    }

# USER

#### Endpoints for Genre:

| HTTP   | Path             | Information          | Access Level | Status Code | Response Body  |
|--------|------------------|----------------------|--------------|-------------|----------------|
| GET    | /api/genres      | Get all genres       | User         | 200         | List of genres |
| GET    | /api/genres/{id} | Get a genre by id    | User         | 200 / 404   | Genre          |
| DELETE | /api/genres/{id} | Delete a genre by id | User         | 204         | -              |
| PUT    | /api/genres/{id} | Update a genre by id | User         | 200 / 404   | Genre          |
| POST   | /api/genres      | Create a genre       | User         | 201 / 400   |                |
POST and PUT requests require a body as shown below:

    {
      "genreName": "Horror",
      "fiction": "false"
    }


#### Endpoints for Roles:

| HTTP | Path            | Information      | Access Level | Status Code | Response Body |
|------|-----------------|------------------|--------------|-------------|---------------|
| GET  | /api/roles      | Get all roles    | Admin        | 200         | List of roles |
| GET  | /api/roles/{id} | Get a role by id | Admin        | 200 / 404   | Role          |
| POST | /api/roles      | Create a role    | Admin        | 201 / 400   |               |
POST requires a body, as shown by the example below:

    {
      "role": "ROLE_LIBRARIAN"
    }


#### Endpoints for Tasks:

| HTTP | Path            | Information      | Access Level | Status Code | Response Body |
|------|-----------------|------------------|--------------|-------------|---------------|
| GET  | /api/tasks      | Get all tasks    | Admin        | 200         | List of tasks |
| GET  | /api/tasks/{id} | Get a task by id | User         | 200 / 404   | Task          |



#### Endpoints for BookFormat:

| HTTP   | Path                  | Information                | Access Level | Status Code | Response Body   |
|--------|-----------------------|----------------------------|--------------|-------------|-----------------|
| GET    | /api/bookformats      | Get all book formats       | User         | 200         | List of formats |
| GET    | /api/bookformats/{id} | Get a book format by id    | User         | 200 / 404   | Format          |
| DELETE | /api/bookformats/{id} | Delete a book format by id | User         | 204         | -               |
| POST   | /api/bookformats      | Create a book format       | User         | 201 / 400   | New format      |

POST requires a body, as shown by the example below:

    {
      "formatName": "Hard copy",
      "digital": "false",
      "pageCount": 33,
      "length": "5"
    }


#### Endpoints for Author:

| HTTP   | Path              | Information             | Access Level | Status Code | Response Body   |
|--------|-------------------|-------------------------|--------------|-------------|:----------------|
| GET    | /api/authors      | Get all authors         | User         | 200         | List of authors |
| GET    | /api/authors/{id} | Get an author by the id | User         | 200 / 404   | Author          |
| DELETE | /api/authors/{id} | Delete an author by id  | User         | 204         | -               |
| POST   | /api/authors      | Create an author        | User         | 201 / 400   | Author          |
POST requires a body, as shown by the example below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson",
    }





"username": "adam",
"password": "**********",
"ssn": "YYYYMMDD-XXXX",
"email": "adamm@mail.com"

______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
