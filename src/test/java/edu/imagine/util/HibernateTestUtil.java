package edu.imagine.util;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public final class HibernateTestUtil {
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16");

    static {
        POSTGRES.withDatabaseName("postgres")
                .withInitScript("fill-database.sql")
                .start();
    }

    public static Session buildSession() {
        return new Configuration() {{
            setProperty("hibernate.connection.url", POSTGRES.getJdbcUrl());
            setProperty("hibernate.connection.username", POSTGRES.getUsername());
            setProperty("hibernate.connection.password", POSTGRES.getPassword());
        }}
                .configure()
                .buildSessionFactory()
                .openSession();
    }

}
