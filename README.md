# Supporting Multi-Tenancy in Hibernate ORM

This example is based on [hibernate-orm-multi-tenancy-quickstart](https://github.com/quarkusio/quarkus-quickstarts/tree/main/hibernate-orm-multi-tenancy-quickstart).

The goal is to find out if the multitenant handling can also be used when receiving Kafka messages.

## Start the demo
Database:
> docker run -it --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3

Application:
> ./mvnw compile quarkus:dev

## Test if it's working
Repeat the command a few times.
> curl http://localhost:8080/fruits

You should see different lists.
