* changelog для liquibase [db.changelog-master.yaml](landscape/src/main/resources/db/changelog/db.changelog-master.yaml)
* сначала применятется changeset [create-table-account.sql](landscape/src/main/resources/db/create-table-account.sql)
* потом changeset [alter-table-account.sql](landscape/src/main/resources/db/alter-table-account.sql), там же находится процедура для миграции таблицы `account`