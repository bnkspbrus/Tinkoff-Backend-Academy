--liquibase formatted sql

--changeset bnkspbrus:1
create extension if not exists "uuid-ossp";

create type account_type_v1 as enum('handyman', 'rancher');

create table account
(
    id       uuid default uuid_generate_v4(),
    type_v1  account_type_v1,
    login    varchar(255),
    email    varchar(255),
    phone    varchar(255),
    creation timestamp without time zone,
    updating timestamp without time zone,
    latitude float8,
    longitude float8,
    primary key (id)
);