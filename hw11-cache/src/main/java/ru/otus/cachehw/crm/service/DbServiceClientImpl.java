package ru.otus.cachehw.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.cachehw.core.repository.DataTemplate;
import ru.otus.cachehw.core.sessionmanager.TransactionRunner;
import ru.otus.cachehw.crm.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> dataTemplate;
    private final TransactionRunner transactionRunner;
    private final boolean isUseCache;
    private final HwCache<String, Client> myCache = new MyCache<>();

    public DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate, boolean isUseCache) {
        this.transactionRunner = transactionRunner;
        this.dataTemplate = dataTemplate;
        this.isUseCache = isUseCache;
    }

    @Override
    public Client saveClient(Client client) {
        Client savedClient = putClientInDB(client);

        if (isUseCache) {
            putClientInCache(savedClient);
        }

        return savedClient;
    }

    private Client putClientInDB(Client client) {
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

    private void putClientInCache(Client client) {
        if (client != null) {
            myCache.put(getKey(client.getId()), client);
        }
    }

    @Override
    public Optional<Client> getClient(long id) {
        Optional<Client> clientFoundInCache = isUseCache ? getClientFromCache(id) : Optional.empty();

        return clientFoundInCache.isPresent() ? clientFoundInCache : getClientFromDB(id);
    }

    private Optional<Client> getClientFromDB(long id) {
        return transactionRunner.doInTransaction(connection -> {
            var clientOptional = dataTemplate.findById(connection, id);
            log.info("client: {}", clientOptional);
            return clientOptional;
        });
    }

    private Optional<Client> getClientFromCache(long id) {
        Client clientFoundInCache = myCache.get(getKey(id));

        return clientFoundInCache == null ? null : Optional.of(clientFoundInCache);
    }

    private String getKey(long id) {
        return "Client:".concat(String.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        List<Client> resultList = isUseCache ? getAllClientsFromCache() : new ArrayList<>();

        return resultList.isEmpty() ? getAllClientsFromDB() : resultList;
    }

    private List<Client> getAllClientsFromCache() {
        return myCache.getAll();
    }

    private List<Client> getAllClientsFromDB() {
        return transactionRunner.doInTransaction(connection -> {
            var clientList = dataTemplate.findAll(connection);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
