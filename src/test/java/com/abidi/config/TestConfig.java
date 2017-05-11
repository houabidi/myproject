package com.abidi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;
import static org.springframework.jdbc.datasource.DataSourceUtils.releaseConnection;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

/**
 * Created by houssemabidi on 11/05/17.
 */
@Configuration
public class TestConfig {
    private static final String CLASS_PATH_RESOURCE = "data.sql";

    @Bean
    @Lazy(false)
    public static ResourceDatabasePopulator populateDatabase() throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource(CLASS_PATH_RESOURCE));
        Connection connection = null;

        try {
            connection = getConnection(dataSource());
            populator.populate(connection);
        } finally {
            if (connection != null) {
                releaseConnection(connection, dataSource());
            }
        }
        return populator;
    }
    /**
     * Bootstraps an in-memory HSQL database.
     */
    @Bean
    public static DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(HSQL).build();
    }

}
