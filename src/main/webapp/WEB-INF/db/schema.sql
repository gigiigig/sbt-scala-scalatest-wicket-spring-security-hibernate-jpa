CREATE TABLE customer
(int bigint by default as identity (start with 1),
    first_name char(50),
    last_name char(50),
    address char(50),
    city char(50),
    country char(25),
    birth_date date)


INSERT INTO customer (first_name , address, city , country, birth_date)
	values ('luigi' , 'antonini' , 'via trallallero', 'terni' , 'Italy' , '10/2/1990') 
