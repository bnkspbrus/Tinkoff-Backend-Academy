--liquibase formatted sql

--changeset bnkspbrus:2
create table account_type_v2
(
    id        int,
    type_name varchar(255) unique,
    primary key (id)
);

insert into account_type_v2
values (1, 'handyman'),
       (2, 'rancher');

alter table account
    add type_v2 int references account_type_v2 (id);
