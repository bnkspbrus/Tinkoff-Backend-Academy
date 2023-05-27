--liquibase formatted sql

--changeset bnkspbrus:2
create table account_type_v2
(
    id        int8,
    type_name varchar(255) unique not null,
    primary key (id)
);

insert into account_type_v2
values (1, 'handyman'),
       (2, 'rancher');

alter table account
    add type_v2 int8 references account_type_v2 (id) not null;
