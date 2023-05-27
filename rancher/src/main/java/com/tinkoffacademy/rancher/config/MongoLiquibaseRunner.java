package com.tinkoffacademy.rancher.config;

import liquibase.Liquibase;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import liquibase.integration.spring.SpringResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

@Setter
@RequiredArgsConstructor
public class MongoLiquibaseRunner implements CommandLineRunner, ResourceLoaderAware {
    public final MongoLiquibaseDatabase database;
    private final String changeLogFile;
    protected ResourceLoader resourceLoader;

    public void run(final String... args) throws Exception {
        Liquibase liquiBase = new Liquibase(changeLogFile, new SpringResourceAccessor(resourceLoader), database);
        liquiBase.update("");
    }
}
