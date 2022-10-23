# Exchange Rate App

Hi! Welcome to home page of Exchange Rate App. This application daily gathers exchange rates data from https://fixer.io/ API nad exposes it further via own API. 


# Installation, configuration and running

You can run the app directly without modifications with prepared MySql docker image. 

**Preconditions:**
 - Any Docker installation allowing docker-compose. 

App should be up after running **[runAppScript.sh](https://github.com/sebcicho/exchange-rate-app/commit/9d2545029b894cc296f6c1c669167e425d723951#diff-62df637e6147609c7fa3c3c787bb90233d104a7bd16a2202aa30af2b40ed9fa3 "runAppScript.sh")** script.

There is possibility to just run application using Maven but you need to provide your own database credentials and configure it here:  [exchange-rate-app](https://github.com/sebcicho/exchange-rate-app)/[exchange-rate](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate)/[application](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate/application)/[src](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate/application/src)/[main](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate/application/src/main)/[resources](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate/application/src/main/resources)/[application.properties](https://github.com/sebcicho/exchange-rate-app/tree/main/exchange-rate/application/src/main/resources/application.properies).
In config file above you can change Base currency of your API plan and also APi URL with different **apiKey**

# Swagger documentation and App URL
Base url of application is http://localhost:8080.
Then Swagger documentation UI can be found here: http://localhost:8080/swagger-ui.html 
