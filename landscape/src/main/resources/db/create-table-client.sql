--liquibase formatted sql

--changeset bnkspbrus:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE client_type AS ENUM('handyman', 'rancher');

CREATE TABLE client
(
    id           UUID DEFAULT uuid_generate_v4(),
    clientType   client_type,
    login        VARCHAR(255),
    email        VARCHAR(255),
    phone        VARCHAR(255),
    creationDate DATE,
    updatingTime DATE,
    PRIMARY KEY (id)
);