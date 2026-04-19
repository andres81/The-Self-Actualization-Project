# Quick Setup

Getting up and running with this project in no time!

## Docker

To run the backend against a Postgresql database, one might wish to consider
using Docker with a Postgres container running to connect to.

### How to setup a Postgres container

```Bash
docker run \
--name local_postgres_db \
-e POSTGRES_PASSWORD=mypassword \
-e POSTGRES_USER=myuser \
-e POSTGRES_DB=mydatabase \
-p 55000:5432 \
-d postgres
```
