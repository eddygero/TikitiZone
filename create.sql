CREATE DATABASE tikitiZone;
\c tikitiZone;
CREATE TABLE buyer(id serial PRIMARY KEY, name varchar,age varchar,ticket varchar,price varchar,location varchar,paymentModels);
CREATE TABLE  events(id serial PRIMARY KEY, title varchar,location varchar,price INT,host varchar,imageUrl varchar,description varchar,Buyer_id INT,eventTime TIMESTAMP);
CREATE DATABASE tikitiZone_test WITH TEMPLATE tikitiZone;