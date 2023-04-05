* changelog для liquibase [db.changelog-master.yaml](landscape/src/main/resources/db/changelog/db.changelog-master.yaml)
* сначала применятется changeset [create-table-client.sql](landscape/src/main/resources/db/create-table-client.sql)
* потом changeset [alter-table-client.sql](landscape/src/main/resources/db/alter-table-client.sql), там же находится процедура для миграции таблицы `client`