--liquibase formatted sql

--changeset bnkspbrus:1
create
extension if not exists "uuid-ossp";

create type account_type_v1 as enum('handyman', 'rancher');

create table account
(
    id        uuid default uuid_generate_v4(),
    type_v1   account_type_v1,
    login     varchar(255) not null,
    email     varchar(255) not null,
    phone     varchar(255) not null,
    creation  timestamp without time zone,
    updating  timestamp without time zone,
    latitude  float8       not null,
    longitude float8       not null,
    primary key (id)
);