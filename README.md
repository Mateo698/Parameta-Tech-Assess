### Database Setup
To run the MySQL database using Docker, execute the following command:

```bash
docker run --name mysql-techassess -e MYSQL_ROOT_PASSWORD=rootpass -e MYSQL_DATABASE=techassess_db -e MYSQL_USER=techuser -e MYSQL_PASSWORD=techpass -p 3306:3306 -d mysql:8.0
```

This will:
- Create a MySQL 8.0 container named 'mysql-techassess'
- Set up the database 'techassess_db'
- Create a user 'techuser' with password 'techpass'
- Expose the database on port 3306
