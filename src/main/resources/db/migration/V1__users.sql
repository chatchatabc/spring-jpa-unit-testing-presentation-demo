CREATE TABLE users
(
    id SERIAL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    salt varchar(16) NOT NULL,
    username varchar(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);