#! /bin/sh

docker-compose -f ./msqlDocker/docker-compose.yml up -d 
cd ./exchange-rate
./mvnw spring-boot:run




