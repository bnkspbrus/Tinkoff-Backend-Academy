--liquibase formatted sql

--changeset bnkspbrus:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE client_type AS ENUM('handyman', 'rancher');

CREATE TABLE client
(
    id         UUID DEFAULT uuid_generate_v4(),
    clientType client_type,
    login      VARCHAR(255),
    email      VARCHAR(255),
    phone      VARCHAR(255),
    creation   timestamp without time zone,
    updating   timestamp without time zone,
    PRIMARY KEY (id)
);