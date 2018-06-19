package writer.command;


import job.transform.types.FieldConversionException;
import job.transform.types.RwRecord;
import objectmodel.RwField;
import writer.RecordWriterException;
import writer.statementbuild.StatementBuilder;
import writer.statementexecutor.StatementExecutorFactory;

import java.sql.PreparedStatement;

public class InsertCommand extends AbstractDatabaseCommand {
    private PreparedStatement preparedStatement;

    public InsertCommand(StatementBuilder statementBuilder,
                         StatementExecutorFactory statementExecutorFactory) {
        super(statementBuilder, statementExecutorFactory);
    }

    public int writeRecord(RwRecord record) throws RecordWriterException, FieldConversionException {
        if (statementExecutor == null) {
            String query = statementBuilder.buildInsertStatement(record);
            statementExecutor = statementExecutorFactory.createStatementExecutor(query);
        }
        preparedStatement = statementExecutor.getStatement();
        position = 1;

        for (RwField field : record.getRwFields()) {
            writeValue(field,preparedStatement);
            position++;
        }

        return statementExecutor.executeStatement(record);
    }
}