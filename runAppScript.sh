#! /bin/sh

docker-compose -f /msqlDocker/docker-compose.yml
./mvnw spring-boot:run



