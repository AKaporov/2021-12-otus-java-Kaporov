package ru.otus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionRunner;
import ru.otus.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> dataTemplate;
    private final TransactionRunner transactionRunner;
    private final HwCache<String, Client> cache = new MyCache<>();

    public DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate) {
        this.transactionRunner = transactionRunner;
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Client saveClient(Client client) {
        Client savedClient = putClientInDB(client);

        putClientInCache(savedClient);

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
            cache.put(getKey(client.getId()), client);
        }
    }

    @Override
    public Optional<Client> getClient(long id) {
        Optional<Client> clientFoundInCache = getClientFromCache(id);

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
        Client clientFoundInCache = cache.get(getKey(id));

        return clientFoundInCache == null ? null : Optional.of(clientFoundInCache);
    }

    private String getKey(long id) {
        return "Client:".concat(String.valueOf(id));
    }

    @Override
    public List<Client> findAll() {
        List<Client> resultList = getAllClientsFromCache();

        if (resultList.isEmpty()) {
            return getAllClientsFromDB();
        }

        return resultList;
    }

    private List<Client> getAllClientsFromCache() {
        return cache.getAll();
    }

    private List<Client> getAllClientsFromDB() {
        return transactionRunner.doInTransaction(connection -> {
            var clientList = dataTemplate.findAll(connection);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
