package com.tinkoffacademy.handyman.config;

import liquibase.database.DatabaseFactory;
import liquibase.exception.DatabaseException;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoLiquibaseRunnerConfig {
    @Bean
    public MongoLiquibaseRunner liquibaseRunner(
            MongoLiquibaseDatabase database,
            @Value("${MONGO_CHANGELOG_FILE:db/changelog/db.changelog-master.yaml}") String changeLogFile
    ) {
        return new MongoLiquibaseRunner(database, changeLogFile);
    }

    /**
     * @return Database with connection
     * @throws DatabaseException when cannot connect
     */
    @Bean
    public MongoLiquibaseDatabase database(@Value("${spring.data.mongodb.uri}") String url) throws DatabaseException {
        return (MongoLiquibaseDatabase) DatabaseFactory.getInstance().openDatabase(
                url,
                null,
                null,
                null,
                null
        );
    }
}
