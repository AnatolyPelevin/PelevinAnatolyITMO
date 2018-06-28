package writer;

import datasource.DataSource;
import dictionaries.ViewType;
import job.transform.types.FieldConversionException;
import job.transform.types.RwRecord;
import objectmodel.RwView;
import objectmodel.TransformRuleType;
import writer.command.InsertCommand;
import writer.command.InsertUpdateCommand;
import writer.statementbuild.StatementBuilder;
import writer.statementexecutor.SingleStatementExecutorFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static utils.Arguments.checkNotNull;


public class AbstractFileWriter implements FileWriterInterface {
    private Map<Integer, FileWriterInterface> writersMap = new HashMap<>();
    private StatementBuilder statementBuilder;
  //  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileWriter.class);
    private Map<String, Connection> connectionsMap = new HashMap<>();

    public AbstractFileWriter(StatementBuilder statementBuilder){
        this.statementBuilder = checkNotNull(statementBuilder, "statementBuilder");
    }

    @Override
    public int writeRecord(RwRecord rwRecord) throws RecordWriterException, FieldConversionException {
        Integer writerKey = getWriterKey(rwRecord);
        FileWriterInterface writer = writersMap.get(writerKey);


        if (writer == null) {
            if (rwRecord.getRwView() != null) {
                writer = createDatabaseWriter(rwRecord.getRwView());
            } else if (rwRecord.getRwFile() != null) {
                // writer = createFileWriter(rwRecord.getRwFile()); TODO work with file-to-file
            } else {
                throw new RecordWriterException("tid[" + Thread.currentThread().getId() + "] - " + " - Record writer not supported " +
                        "for records with no file or view defined.");
            }

            writersMap.put(writerKey, writer);
        }
        return writer.writeRecord(rwRecord);
    }

    @Override
    public void close() throws RecordWriterException {
        connectionsMap.values().forEach(connection -> {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private Integer getWriterKey(RwRecord record) {
        if (record.getRwFile() != null) {
            return -record.getRwFile().getRwFileId();
        }
        return null;
    }

    FileWriterInterface createDatabaseWriter(RwView view) throws RecordWriterException {
        Connection connection;

        String connectionKey = view.getRwDatabase().getUniqueid();
        connection = connectionsMap.get(connectionKey);

        if (connection == null) {
            try {
                connection = DataSource.getConnection();
            } catch (Exception e) {
                throw new RecordWriterException("tid[" + Thread.currentThread().getId() + "] - Unable to create connection for database: "
                        + view.getRwDatabase().getRwdatabaseName(), e);
            }
            connectionsMap.put(connectionKey, connection);
        }


        ViewType viewType = view.getViewType();
        FileWriterInterface writer = null;
        boolean returnGeneratedKeys = false; //TODO return veribles
        TransformRuleType TransformRule = TransformRuleType.INSERT;

        switch (viewType) {
            case STORED_PROCEDURE:
                break;
            case TABLE:
                switch (TransformRule) { //TODO ADD Transform Rule to check insert/update/delete
                    case DELETE:
                        break;

                    case INSERT:
                        writer = new InsertCommand(statementBuilder,
                                new SingleStatementExecutorFactory(connection, returnGeneratedKeys, false));
                        break;

                    case INSERT_UPDATE:
                        writer = new InsertUpdateCommand(statementBuilder,
                                 new SingleStatementExecutorFactory(connection, returnGeneratedKeys, false));
                        break;

                    case UPDATE:
                        break;

                    case UPDATE_INSERT:
                        break;
                    default:
                        throw new RecordWriterException("tid[" + Thread.currentThread().getId() + "] - Can not create a record writer  ");
                }
                break;

            default:
                throw new RecordWriterException("tid[" + Thread.currentThread().getId() + "] - Can not create a record writer for " +
                        "view with type: " + view.getViewType());
        }
        return writer;
    }

}
