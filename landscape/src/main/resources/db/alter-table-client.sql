--liquibase formatted sql

--changeset bnkspbrus:2
CREATE TABLE client_type_v2
(
    id       INT,
    typeName VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO client_type_v2
VALUES (1, 'handyman'),
       (2, 'rancher');

ALTER TABLE client
    ADD clientTypeV2 INT REFERENCES client_type_v2 (id);

--changeset bnkspbrus:3 splitStatements:false
create procedure migrateBatch(batch integer, pause integer)
language sql
as $$
    update client
    set clientTypeV2 = 1, clientType = null
    where id in (select id from client where clientType = 'handyman' limit batch);

    update client
    set clientTypeV2 = 2, clientType = null
    where id in (select id from client where clientType = 'rancher' limit batch);

    select pg_sleep(pause);
$$;

do $$
declare
    batch integer := 10;
    pause integer := 1;
begin
raise info '% - Starting migration', now();
loop
    raise info '% - Starting migration of batch. Size %', now(), batch;
    call migrateBatch(batch, pause);
    continue when exists (select 1 from client where clientType = 'handyman');
    continue when exists (select 1 from client where clientType = 'rancher');
    exit;
end loop;
raise info '% - Completing migration', now();
end; $$;