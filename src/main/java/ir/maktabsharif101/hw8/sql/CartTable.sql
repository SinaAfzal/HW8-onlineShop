CREATE TABLE IF NOT EXISTS cart(
    id serial PRIMARY KEY ,
itemName varchar(50),
itemUnitPrice decimal,
itemCount int,
user_id int REFERENCES users(id)
)