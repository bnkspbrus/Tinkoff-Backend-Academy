package com.tinkoffacademy.landscape.config;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.spatial.dialect.postgis.PostgisPG10Dialect;
import org.hibernate.type.StandardBasicTypes;

public class STAreaPostgisDialect extends PostgisPG10Dialect {
    public STAreaPostgisDialect() {
        super();
        registerFunction(
                "ST_Area",
                new StandardSQLFunction(
                        "ST_Area",
                        StandardBasicTypes.DOUBLE
                )
        );
    }
}
