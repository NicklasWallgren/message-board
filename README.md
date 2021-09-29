# Message Board

[![Continuous Integration](https://github.com/nicklaswallgren/message-board/workflows/ci/badge.svg)](https://github.com/nicklaswallgren/message-board/actions)
[![License](https://img.shields.io/github/license/nicklaswallgren/message-board)](https://github.com/nicklaswallgren/message-board/blob/master/LICENSE)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/NicklasWallgren/message-board.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/NicklasWallgren/message-board/context:java)


The main focus has been on the backend. 

## Documentation
See the project's [Javadoc](https://nicklaswallgren.github.io/message-board/).

## Code Guide

We use GitHub Actions to make sure the codebase is consistent and continuously tested (`gradle check`). We try to keep
comments at a maximum of 120 characters of length and code at 120.

## Architecture
Hexagonal architecture
DDD

## Prerequisites
Docker
Make

```bash
make
```

Frontend
http://localhost:3000/

Backend
http://localhost:8080/api

Swagger
http://localhost:8080/swagger-ui/index.html?configUrl=/swagger-ui/v3/api-docs/swagger-config
