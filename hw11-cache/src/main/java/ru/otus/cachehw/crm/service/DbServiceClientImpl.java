package ru.otus.cachehw.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.core.repository.DataTemplate;
import ru.otus.cachehw.core.sessionmanager.TransactionRunner;
import ru.otus.cachehw.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> dataTemplate;
    private final TransactionRunner transactionRunner;
    private final boolean isUseCache;
    private final HwCache<String, Client> myCache;

    public DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate, HwCache<String, Client> myCache, boolean isUseCache) {
        this.transactionRunner = transactionRunner;
        this.dataTemplate = dataTemplate;
        this.myCache = myCache;
        this.isUseCache = isUseCache;
    }

    @Override
    public Client saveClient(Client client) {
        Client savedClient = putInDB(client);

        if (isUseCache) {
            putInCache(savedClient);
        }

        return savedClient;
    }

    private Client putInDB(Client client) {
        return transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                var clientId = dataTemplate.insert(connection, client);
                var createdClient = new Client(clientId, client.getName());
                log.info("created client: {}", createdClient);
                return createdClient;
            }
            dataTemplate.update(connection, client);
            log.info("updated client: {}", client);
            return client;
        });
    }

    private void putInCache(Client client) {
        if (client != null) {
            myCache.put(getKey(client.getId()), client);
        }
    }

    @Override
    public Optional<Client> getClient(long id) {
        if (isUseCache) {
            Optional<Client> fromCache = getFromCache(id);

            if (fromCache.isPresent()) {
                return fromCache;
            }
        }

        Optional<Client> fromDB = getFromDB(id);

        fromDB.ifPresent(this::putInCache);

        return fromDB;
    }

    private Optional<Client> getFromDB(long id) {
        return transactionRunner.doInTransaction(connection -> {
            var clientOptional = dataTemplate.findById(connection, id);
            log.info("client: {}", clientOptional);
            return clientOptional;
        });
    }

    private Optional<Client> getFromCache(long id) {
        return Optional.ofNullable(myCache.get(getKey(id)));
    }

    private String getKey(long id) {
        return "Client:".concat(String.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        return transactionRunner.doInTransaction(connection -> {
            var clientList = dataTemplate.findAll(connection);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
