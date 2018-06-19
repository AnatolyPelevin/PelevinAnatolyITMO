package writer.statementbuild;

import job.transform.types.RwRecord;
import objectmodel.RwField;
import objectmodel.RwView;
import utils.CollectionUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StatementBuilderImpl implements StatementBuilder {

    public String buildSelectStatement(RwView view) throws DatabaseException {

        Collection<RwRecord> rootRecords = CollectionUtils.safeCollection(view.getRwRecords());
        StringBuilder select = new StringBuilder("SELECT ");

        if (rootRecords.isEmpty() || rootRecords.size() > 1) {
            select.append("*");
        } else {
            RwRecord record = view.getRwRecords().iterator().next();
            Set<RwField> fields = record.getRwFields();

            if (CollectionUtils.isEmpty(fields)) {
                select.append("*");
            } else {
                for (RwField field : fields) {
                    String fieldName = field.getFieldName();

                    select.append(fieldName).append(", ");
                }

                StringUtils.cutTail(select, ", ".length());
            }
        }

        String tableName = view.getViewName();

        select.append(" FROM ").append(tableName);

        return select.toString();
    }

    public String buildInsertStatement(RwRecord record) throws DatabaseException {
        if (CollectionUtils.isEmpty(record.getRwFields())) {
            throw new DatabaseException("Unable to build an insert statement for record with " +
                    "no fields defined: " + record.getRecordName());
        }
        List<RwField> fields = record.getRwFields()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));

        StringBuilder statement = new StringBuilder("INSERT INTO ");

        String tableName = record.getRwView().getViewName();
        statement.append(tableName).append(" (");

        for (RwField field : fields) {
            String fieldName =field.getFieldName();
            statement.append(fieldName).append(", ");
        }

        statement.delete(statement.length() - ", ".length(), statement.length());
        statement.append(") VALUES (");

        for (int i = 0; i < fields.size() - 1; i++) {
            statement.append("?, ");
        }
        statement.append("?)");

        return statement.toString();
    }

    public String buildInsertUpdateStatement(RwRecord record) throws DatabaseException {
        StringBuilder statement = new StringBuilder();
        String tableName = record.getRwView().getViewName();

        statement.append("IF NOT EXISTS (SELECT * FROM ").append(tableName).append(" ");
        statement.append(buildWhereStatement(record)).append(") ");
        statement.append(buildInsertStatement(record));
        statement.append(" ELSE ");
        statement.append(buildUpdateStatement(record));

        return statement.toString();
    }

    public String buildUpdateStatement(RwRecord record) throws DatabaseException {
        List<RwField> keyFields = record.getKeyFields();

        if (CollectionUtils.isEmpty(keyFields)) {
            throw new DatabaseException("Unable to build an update statement for record with " +
                    "no key fields defined: " + record.getRecordName());
        }

        StringBuilder statement = new StringBuilder("UPDATE ");

        String tableName =  record.getRwView().getViewName();
        statement.append(tableName);
        statement.append(" SET ");

        for (RwField field : record.getRwFields()) {
            String fieldName = field.getFieldName();
            statement.append(fieldName).append(" = ?").append(", ");
        }

        StringUtils.cutTail(statement, ", ".length());
        statement.append(" ").append(buildWhereStatement(record));

        return statement.toString();
    }

    public String buildDeleteStatement(RwRecord record) throws DatabaseException {
        List<RwField> keyFields = record.getKeyFields();

        if (CollectionUtils.isEmpty(keyFields)) {
            throw new DatabaseException("Unable to build a delete statement for record with " +
                    "no key fields defined: " + record.getRecordName());
        }

        StringBuilder statement = new StringBuilder("DELETE FROM ");

        String tableName = record.getRwView().getViewName();
        statement.append(tableName);
        statement.append(" ").append(buildWhereStatement(record));

        return statement.toString();
    }

    public String buildSPCallStatement(RwRecord record) throws DatabaseException {
        if (CollectionUtils.isEmpty(record.getRwFields())) {
            throw new DatabaseException("Unable to build a call sp statement for record with " +
                    "no fields defined: " + record.getRecordName());
        }

         List<RwField> fields = record.getRwFields()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));

        String spName = record.getRwView().getViewName();
        StringBuilder statement = new StringBuilder();

        statement.append("{call ").append(spName).append("(");

        for (RwField field : record.getRwFields()) {
            statement.append("@").append(field.getFieldName()).append(" = ?, ");
        }

        StringUtils.cutTail(statement, ", ".length());
        statement.append(")}");

        return statement.toString();
    }

    protected String buildWhereStatement(RwRecord record) throws DatabaseException {
        List<RwField> keyFields = record.getKeyFields();

        if (CollectionUtils.isEmpty(record.getRwFields())) {
            throw new DatabaseException("Unable to build a where statement for record with " +
                    "no fields defined: " + record.getRecordName());
        }

        StringBuilder where = new StringBuilder("WHERE ");

        for (RwField field : keyFields) {
            String fieldName = field.getFieldName();
            where.append(" (? = ").append(fieldName).append(" OR (? IS NULL AND ").append(fieldName).append(" IS NULL)) AND ");

        }

        StringUtils.cutTail(where, " AND ".length());
        return where.toString();
    }
}
