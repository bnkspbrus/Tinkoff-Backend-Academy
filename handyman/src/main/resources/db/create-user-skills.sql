--liquibase formatted sql

--changeset bnkspbrus:create-user-skills
create type skill as enum('SHOVEL','PLANT','WATER','SOW');

create table user_skills
(
    user_id int8 references users not null,
    skills  skill not null
);