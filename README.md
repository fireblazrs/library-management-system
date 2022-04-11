
# Library Management System

This is a Library Management System application that is built with Spring Boot which has a MySQL database and uses RabbitMQ. 

The application allows the user to create/save, update, delete and view books, book properties (genre, format), authors,  users and rooms. It automatically generates tasks which can be queried for status updates when an attempt to borrow a book is made. Admin and user roles have different levels of access in the application.
 

### Features Implemented
- Database relations
- Dockerfile/Docker compose
- Create, Read, Update and Delete operations
- Customized exceptions
- Spring Boot Security 
- RabbitMQ queue
- Integration Testing
- MySQL database 


### Start the application

```
docker-compose up --build
```
### Download the Application

1. Download the latest release as a zip file [here](https://github.com/fireblazrs/library-management-system/pkgs/container/library-management-system).

2. Download Docker Image:
```
docker pull ghcr.io/fireblazrs/library-management-system:latest
   ```

### Default Users

1. **Admin**     
   username: admin   
   password: admin    


2. **User**   
   username: user   
   password: user
 

### Endpoints:

_Default port for the application is **8080**._   

#### Endpoints for Book:

| HTTP   | Path                   | Information                    | Access Level | Status Code | Response Body |
|--------|------------------------|--------------------------------|--------------|-------------|---------------|
| GET    | /api/books             | Get all books                  | User         | 200         | List of books |
| GET    | /api/books?isbn={ISBN} | Get all books filtered by ISBN | User         | 200         | List of books |
| GET    | /api/books/{id}        | Get a book by the id           | User         | 200 / 404   | Book          |
| DELETE | /api/books/{id}        | Delete a book by id            | User         | 204         | -             |
| PUT    | /api/books/{id}        | Update a book                  | User         | 200 / 404   | Updated book  |
| POST   | /api/books             | Create a book.                 | User         | 201 / 400   | New book      |

POST and PUT requests require a request body as shown below:

    {
      "title": "Ahsan, Vimbayi, Toni and Joel´s Book",
      "subtitle": "",
      "edition": 1,
      "printed": "2020-02-04",
      "isbn": 123456789
    }

    ** "id" is also required for update (PUT)

#### Endpoints for User:

| HTTP   | Path                       | Request Body | Information               | Access Level | Status Code | Response Body |
|--------|----------------------------|--------------|---------------------------|--------------|-------------|---------------|
| GET    | /api/users                 |              | Get all users             | Admin        | 200         | List of users |
| GET    | /api/users/{id}            |              | Get a user by id          | User         | 200 / 404   | User          |
| POST   | /api/users/new             | yes          | Create a user.            | Any          | 201 / 400   | New user      |
| PUT    | /api/users/{id}            | yes          | Update a user             | User         | 200 / 404   | Updated user  |
| DELETE | /api/users/{id}            |              | Delete a user by id       | User         | 204         | -             |
| GET    | /api/users/{id}/books      |              | Get a user's books        | User         | 200         | User's books  |
| POST   | /api/users/{id}/books      | yes          | Add a book to a user      | User         | 200         | Queued Task   |
| DELETE | /api/users/{id}/books/{id} |              | Remove a book from a user | User         | 204         | -             |
| GET    | /api/users/{id}/role       |              | Get a user's role         | Admin        | 200         | User's role   |
| PATCH  | /api/users/{id}/role/{id}  |              | Add a role to a user      | Admin        | 200         | Updated User  |

Create / Update a user require a request body as shown below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson",
      "username": "adam",
      "password": "**********",
      "ssn": "YYYYMMDD-XXXX",
      "email": "adamm@mail.com"
    }

    ** "id" is also required for update (PUT)
Add a book to a user (borrow) requires a request body as shown below:

    {  
      "isbn": "##########"
    }


#### Endpoints for Room:

| HTTP   | Path                             | Information          | Access Level | Status Code | Response Body |
|--------|----------------------------------|----------------------|--------------|-------------|---------------|
| GET    | /api/rooms                       | Get all rooms        | User         | 200         | List of rooms |
| GET    | /api/rooms/{id}                  | Get a room by id     | User         | 200 / 404   | Room          |
| POST   | /api/rooms                       | Create a room.       | User         | 201 / 400   | New room      |
| PUT    | /api/rooms/{id}                  | Update a room        | User         | 200 / 404   | Updated room  |
| DELETE | /api/rooms/{id}                  | Delete a room by id  | User         | 204         | -             |
| GET    | /api/rooms/getbyname?name={name} | Get a room by name   | User         | 200         | Room          |
| GET    | /api/rooms/{id}/user             | Get a room's user    | User         | 200         | Room's user   |
| PATCH  | /api/rooms/{id}/user/{id}        | Add a user to a room | User         | 204         | Room          |

POST and PUT requests require a body as shown below:

    {
      "name": "Blue Room",
      "groupSize": 6,
      "internetAccess": true,
      "wheelchairAccess": false,
      "hasProjector": true
    }

    ** "id" is also required for update (PUT)


#### Endpoints for Roles:

| HTTP | Path            | Information      | Access Level | Status Code | Response Body |
|------|-----------------|------------------|--------------|-------------|---------------|
| GET  | /api/roles      | Get all roles    | Admin        | 200         | List of roles |
| GET  | /api/roles/{id} | Get a role by id | Admin        | 200 / 404   | Role          |
| POST | /api/roles      | Create a role    | Admin        | 201 / 400   |               |
POST requests requires a body, as shown by the example below:

    {
      "role": "ROLE_LIBRARIAN"
    }


#### Endpoints for Genre:

| HTTP   | Path             | Information          | Access Level | Status Code | Response Body  |
|--------|------------------|----------------------|--------------|-------------|----------------|
| GET    | /api/genres      | Get all genres       | User         | 200         | List of genres |
| GET    | /api/genres/{id} | Get a genre by id    | User         | 200 / 404   | Genre          |
| DELETE | /api/genres/{id} | Delete a genre by id | User         | 204         | -              |
| POST   | /api/genres      | Create a genre       | User         | 201 / 400   |                |
POST requests require a body as shown below:

    {
      "genreName": "Horror",
      "fiction": "false"
    }


#### Endpoints for BookFormat:

| HTTP   | Path                  | Information                | Access Level | Status Code | Response Body   |
|--------|-----------------------|----------------------------|--------------|-------------|-----------------|
| GET    | /api/bookformats      | Get all book formats       | User         | 200         | List of formats |
| GET    | /api/bookformats/{id} | Get a book format by id    | User         | 200 / 404   | Format          |
| DELETE | /api/bookformats/{id} | Delete a book format by id | User         | 204         | -               |
| POST   | /api/bookformats      | Create a book format       | User         | 201 / 400   | New format      |

POST requests requires a body, as shown by the example below:

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
POST requests requires a body, as shown by the example below:

    {
      "firstName": "Adam",
      "lastName": "Magnusson"
    }


#### Endpoints for Tasks:

| HTTP | Path            | Information      | Access Level | Status Code | Response Body |
|------|-----------------|------------------|--------------|-------------|---------------|
| GET  | /api/tasks      | Get all tasks    | Admin        | 200         | List of tasks |
| GET  | /api/tasks/{id} | Get a task by id | User         | 200 / 404   | Task          |


______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
