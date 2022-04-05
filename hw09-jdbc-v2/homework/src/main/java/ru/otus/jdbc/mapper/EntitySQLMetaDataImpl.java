package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {
    private final EntityClassMetaData entity;

    public EntitySQLMetaDataImpl(EntityClassMetaData entity) {
        this.entity = entity;
    }

    @Override
    public String getSelectAllSql() {
        return "select * from ".concat(entity.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return getSelectAllSql() + " where " + entity.getIdField().getName() + " = ?";
    }

    @Override
    public String getInsertSql() {
        var sql = new StringBuilder("insert into ")
                .append(entity.getName())
                .append("(");

        List<Field> fieldsWithoutId = entity.getFieldsWithoutId();
        var fieldList = getFieldListFromEntityToInsert(fieldsWithoutId);

        sql.append(fieldList).append(") values (");

        var valueList = getValueListFromEntity(fieldsWithoutId);
        sql.append(valueList).append(")");

        return sql.toString();
    }

    @Override
    public String getUpdateSql() {

        var sql = new StringBuilder("update ")
                .append(entity.getName())
                .append(" set ");

        String setFields = getFieldListFromEntityToUpdate(entity.getFieldsWithoutId());

        sql.append(setFields)
                .append(" where ")
                .append(entity.getIdField())
                .append(" = ?");

        return sql.toString();
    }

    private String getFieldListFromEntityToUpdate(List<Field> fieldsWithoutId) {
        return fieldsWithoutId.stream()
                .map(f -> f.getName().concat(" = ? "))
                .collect(Collectors.joining(", "));
    }

    private String getValueListFromEntity(List<Field> fieldsWithoutId) {
        var joiner = new StringJoiner(", ");
        fieldsWithoutId.forEach(f -> joiner.add("?"));
        return joiner.toString();
    }

    private String getFieldListFromEntityToInsert(List<Field> fieldsWithoutId) {
        return fieldsWithoutId.stream()
                .map(Field::getName)
                .collect(Collectors.joining(", "));
    }
}
