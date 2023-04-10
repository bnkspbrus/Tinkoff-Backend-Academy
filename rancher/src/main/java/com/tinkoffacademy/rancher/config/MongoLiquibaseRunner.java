package com.tinkoffacademy.rancher.config;

import liquibase.Liquibase;
import liquibase.ext.mongodb.database.MongoLiquibaseDatabase;
import liquibase.integration.spring.SpringResourceAccessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;


public class MongoLiquibaseRunner implements CommandLineRunner, ResourceLoaderAware {

    public final MongoLiquibaseDatabase database;

    protected ResourceLoader resourceLoader;

    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public MongoLiquibaseRunner(MongoLiquibaseDatabase database) {
        this.database = database;
    }

    public void run(final String... args) throws Exception {
        Liquibase liquiBase = new Liquibase("db/changelog/db.changelog-master.yaml", new SpringResourceAccessor(resourceLoader), database);
        liquiBase.update("");
    }

}
