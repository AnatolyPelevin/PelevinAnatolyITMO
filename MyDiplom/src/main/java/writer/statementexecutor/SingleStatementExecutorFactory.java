package writer.statementexecutor;


import utils.NullArgumentException;
import writer.statementbuild.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SingleStatementExecutorFactory implements StatementExecutorFactory {
    private Connection connection;
    private boolean returnGeneratedKeys;
    private boolean spCall;

    public SingleStatementExecutorFactory(Connection connection,
                                          boolean returnGeneratedKeys,
                                          boolean spCall) {
        if (connection == null) {
            throw new NullArgumentException("connection");
        }

        this.connection = connection;
        this.returnGeneratedKeys = returnGeneratedKeys;
        this.spCall = spCall;
    }

    public StatementExecutor createStatementExecutor(String statement) throws DatabaseException {
        StatementExecutor statementExecutor = null;

        try {
            if (spCall) {
               //TODO call sp
            }
            else {
                PreparedStatement preparedStatement = returnGeneratedKeys
                        ? connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS)
                        : connection.prepareStatement(statement);

                statementExecutor = new SingleStatementExecutor(
                        connection,
                        preparedStatement,
                        returnGeneratedKeys);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Error while preparing a statement: " + statement, e);
        }

        return statementExecutor;
    }
}
