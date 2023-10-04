CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY ,
    fullName varchar(50),
    userName varchar(50) UNIQUE NOT NULL ,
    password_ varchar(50) NOT NULL
)