docker run -d --name postgres -e POSTGRES_PASWORD=root -v postgres-data:/var/lib/postgresql/data -p 5432:5432 postgres
