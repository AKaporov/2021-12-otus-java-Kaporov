package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
//        throw new UnsupportedOperationException();

        return (Optional<T>) dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(),
                Collections.singletonList(id), rs -> {
                    try {
                        if (rs.next()) {
                            String tableName = getTableNameByResultSet(rs);

                            if ("Client".equalsIgnoreCase(tableName)) {
                                return new Client(id, rs.getString("name"));
                            } else if ("Manager".equalsIgnoreCase(tableName)) {
                                return (new Manager(id, rs.getString("label"), rs.getString("param1")));
                            }
                        }
                        return null;
                    } catch (SQLException e) {
                        throw new DataTemplateException(e);
                    }
                });
    }

    @Override
    public List<T> findAll(Connection connection) {
//        throw new UnsupportedOperationException();

        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            try {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    String tableName = getTableNameByResultSet(rs);

                    if ("Client".equalsIgnoreCase(tableName)) {
                        list.add((T) new Client(rs.getLong("id"), rs.getString("name")));
                    } else if ("Manager".equalsIgnoreCase(tableName)) {
                        list.add((T) new Manager(rs.getLong("no"), rs.getString("label"), rs.getString("param1")));
                    }
                }
                return list;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    private String getTableNameByResultSet(ResultSet rs) throws SQLException {
        return rs.getMetaData().getTableName(1);
    }

    @Override
    public long insert(Connection connection, T client) {
        try {
            List<Object> params = getParamsWithOutId(client);

            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
//        throw new UnsupportedOperationException();
        try {
            List<Object> params = getParamsForUpdate(client);
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private List<Object> getParamsForUpdate(T client) {
        List<Object> allParams = getParamsWithOutId(client);
        allParams.add(getParamsId(client));

        return allParams;
    }

    private List<Object> getParamsId(T client) {
        List<Object> params = new ArrayList<>();
        if (client instanceof Client) {
            var c = (Client) client;

            params.add(c.getId() == null ? 0L : c.getId());
        } else if (client instanceof Manager) {
            var manager = (Manager) client;

            params.add(manager.getNo() == null ? 0L : manager.getNo());
        } else {
            throw new UnsupportedOperationException();
        }

        return params;
    }

    private List<Object> getParamsWithOutId(T client) {
        List<Object> params = new ArrayList<>();

        if (client instanceof Client) {
            var c = (Client) client;

            params.add(c.getName() == null ? "" : c.getName());
        } else if (client instanceof Manager) {
            var manager = (Manager) client;

            params.add(manager.getLabel() == null ? "" : manager.getLabel());
            params.add(manager.getParam1() == null ? "" : manager.getParam1());
        } else {
            throw new UnsupportedOperationException();
        }

        return params;
    }
}
