# Message Board

[![Continuous Integration](https://github.com/nicklaswallgren/message-board/workflows/ci/badge.svg)](https://github.com/nicklaswallgren/message-board/actions)
[![License](https://img.shields.io/github/license/nicklaswallgren/message-board)](https://github.com/nicklaswallgren/message-board/blob/master/LICENSE)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/NicklasWallgren/message-board.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/NicklasWallgren/message-board/)
[![Language grade: JavaScript](https://img.shields.io/lgtm/grade/javascript/g/NicklasWallgren/message-board.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/NicklasWallgren/message-board/context:javascript)

A restful API that serves as the backend for a message board. 
The branch also includes a simple frontend built on React, that is not feature complete or can be considered "production ready". 

The main focus has been on backend.

## Documentation
See the project's [Javadoc](https://nicklaswallgren.github.io/message-board/).

## Code Guide
We use GitHub Actions to make sure the codebase is consistent and continuously tested (`gradle check`). We try to keep
comments at a maximum of 120 characters of length and code at 120.

## Architecture
* Hexagonal architecture. 
* DDD

## Tools
* Spring Boot
* Hibernate
* H2
* SpotBugs
* Error Prone
* Checkstyle
* Jacoco
* Swagger
* QueryDSL
* React

## Prerequisites
* Docker
* Make

Start the environment  
```bash
make
```
Or directly using docker-compose
```bash
docker-compose up --build
```

The frontend can be accessed via
```bash
http://localhost:3000/
```

The backend can be accessed via
```
http://localhost:8080/api
http://localhost:8080/swagger-ui/index.html?configUrl=/swagger-ui/v3/api-docs/swagger-config
```