
# Library Management System

This is a Library Management System built with Spring Boot.

### Instruction to set up MySQL database container

IF this is the first time running this program: create & run a MySQL container 

```
docker run --name MySqlDatabase -e MYSQL_ROOT_PASSWORD=my_secret_password -e 'MYSQL_ROOT_HOST=%' -e MYSQL_DATABASE=test -e MYSQL_USER=user -e MYSQL_PASSWORD=password -p 3308:3306 mysql:latest
```

ELSE restart the container

```
docker container start MySqlDatabase
```             
     



______________________________________________________________________________________________________________________________________________________________________________       
Contributors: [Toni](https://github.com/ToniKaru), [Ahsan](https://github.com/Ahsanadam), [Joel](https://github.com/joejoh84) & [Vimbayi](https://github.com/Vimbayinashe)
