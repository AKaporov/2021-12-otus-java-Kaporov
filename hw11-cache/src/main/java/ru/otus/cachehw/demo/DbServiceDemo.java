package ru.otus.cachehw.demo;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.core.repository.executor.DbExecutorImpl;
import ru.otus.cachehw.core.sessionmanager.TransactionRunnerJdbc;
import ru.otus.cachehw.crm.datasource.DriverManagerDataSource;
import ru.otus.cachehw.crm.model.Client;
import ru.otus.cachehw.crm.repository.ClientDataTemplateJdbc;
import ru.otus.cachehw.crm.service.DbServiceClientImpl;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.Instant;

public class DbServiceDemo {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();
///
        var clientTemplate = new ClientDataTemplateJdbc(dbExecutor); //реализация DataTemplate, заточена на Client

///
        boolean withCache = true;
        Instant start = Instant.now();
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, clientTemplate, withCache);
        dbServiceClient.saveClient(new Client("dbServiceFirst"));

        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));
        var clientSecondSelected = dbServiceClient.getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);
///
        dbServiceClient.saveClient(new Client(clientSecondSelected.getId(), "dbServiceSecondUpdated"));
        var clientUpdated = dbServiceClient.getClient(clientSecondSelected.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecondSelected.getId()));
        log.info("clientUpdated:{}", clientUpdated);

        log.info("All clients");
        dbServiceClient.findAll().forEach(client -> log.info("client:{}", client));

        Duration elapsedTime = getElapsedTime(start);
        printElapsedTime(elapsedTime.toMillis());
    }

    private static Duration getElapsedTime(Instant start) {
        return Duration.between(start, Instant.now());
    }

    private static void printElapsedTime(long milliseconds) {
        System.out.println("Total time = " + (milliseconds / 1000) + " (seconds), " + milliseconds + " (milliseconds)");
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
