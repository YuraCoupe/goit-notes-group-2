To connect to Heroku PostgreSQL DB you should use next environmental variables
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.url=${DB_URL}
They are already configured at heroku app.

To connect to Heroku PostgreSQL DB use spring profile prod.
Add the next line to InteliiJ IDEA VM options:
-Dspring.profiles.active=prod