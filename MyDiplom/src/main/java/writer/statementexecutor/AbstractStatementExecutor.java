package writer.statementexecutor;

import utils.NullArgumentException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class AbstractStatementExecutor implements StatementExecutor {
    protected Connection connection;
    protected PreparedStatement statement;

    protected AbstractStatementExecutor(Connection connection, PreparedStatement statement) {
        if (connection == null) {
            throw new NullArgumentException("connection");
        }

        if (statement == null) {
            throw new NullArgumentException("statement");
        }

        this.connection = connection;
        this.statement = statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }
}
