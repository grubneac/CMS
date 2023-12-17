CREATE TABLE users (
    id serial PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    first_name varchar(50) NOT NULL,
    last_name varchar(100) NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(20) NOT NULL DEFAULT 'USER',
    status varchar(50) NOT NULL DEFAULT 'ACTIVE'
);