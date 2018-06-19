package writer.command;

import job.transform.types.FieldConversionException;
import job.transform.types.RwRecord;
import objectmodel.RwField;
import writer.RecordWriterException;
import writer.statementbuild.StatementBuilder;
import writer.statementexecutor.StatementExecutor;
import writer.statementexecutor.StatementExecutorFactory;

import java.sql.PreparedStatement;


public class InsertUpdateCommand  extends AbstractDatabaseCommand {
   // private static final Logger LOG = LoggerFactory.getLogger(InsertUpdateCommand.class);
    private StatementExecutor statementExecutor;
    private PreparedStatement preparedStatement;

    public InsertUpdateCommand(StatementBuilder statementBuilder,  StatementExecutorFactory statementExecutorFactory){
        super(statementBuilder, statementExecutorFactory);
    }

    @Override
    public int writeRecord(RwRecord record) throws RecordWriterException, FieldConversionException {
        if (statementExecutor == null) {
            String query = statementBuilder.buildInsertUpdateStatement(record);
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("SQL Statement: " + query);
//                LOG.debug("SQL Statement record : " + record.toString());
//            }
            statementExecutor = statementExecutorFactory.createStatementExecutor(query);
        }

         preparedStatement = statementExecutor.getStatement();

        position = 1;

        // select parameters
        whereParameters(record);

        // insert parameters
        for (RwField field : record.getRwFields()) {
            writeValue(field,preparedStatement);
            position++;
        }

        // update parameters
        for (RwField field : record.getRwFields()) {
            writeValue(field,preparedStatement);
            position++;
        }

        // update where parameters
        whereParameters(record);

        return statementExecutor.executeStatement(record);
    }

    private void whereParameters(RwRecord record) throws RecordWriterException, FieldConversionException {
        for (RwField field : record.getKeyFields()) {
            writeValue(field,preparedStatement);
            position++;
            writeValue(field,preparedStatement);
            position++;
        }
    }




    @Override
    public void close() throws RecordWriterException {

    }
}

