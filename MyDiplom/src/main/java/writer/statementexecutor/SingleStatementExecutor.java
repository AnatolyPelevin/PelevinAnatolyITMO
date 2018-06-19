package writer.statementexecutor;


import job.transform.types.RwRecord;
import writer.statementbuild.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SingleStatementExecutor extends AbstractStatementExecutor {
  //  private static final Logger LOG = LoggerFactory.getLogger(SingleStatementExecutor.class);
    private boolean returnGeneratedKeys;

    public SingleStatementExecutor(Connection connection,
                                   PreparedStatement statement,
                                   boolean returnGeneratedKeys) {
        super(connection, statement);
        this.returnGeneratedKeys = returnGeneratedKeys;
    }

    public int executeStatement(RwRecord record) throws DatabaseException {
        try {
            int result = statement.executeUpdate();

            if (returnGeneratedKeys) {
                ResultSet resultSet = statement.getGeneratedKeys();
                Long generatedId = null;

                try {
                    while (resultSet.next()) {
                        generatedId = resultSet.getLong(1);
                    }
                }
                finally {
                    resultSet.close();
                }

                //record.setGeneratedId(generatedId);
            }

            return result;
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void close() throws DatabaseException {
        try {
            statement.close();
        }
        catch (SQLException e) {
        //    LOG.error("Error while closing statement", e);
        }
    }
}
