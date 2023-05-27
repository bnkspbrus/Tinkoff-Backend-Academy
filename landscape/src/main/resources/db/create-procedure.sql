--liquibase formatted sql

--changeset bnkspbrus:3 splitStatements:false
create procedure migrateBatch(batch integer, pause integer)
language sql
as $$
    update account
    set type_v2 = 1, type_v1 = null
    where id in (select id from account where type_v1 = 'handyman' limit batch);

    update account
    set type_v2 = 2, type_v1 = null
    where id in (select id from account where type_v1 = 'rancher' limit batch);

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
        continue when exists (select 1 from account where type_v1 = 'handyman');
        continue when exists (select 1 from account where type_v1 = 'rancher');
        exit;
    end loop;
    raise info '% - Completing migration', now();
end; $$;