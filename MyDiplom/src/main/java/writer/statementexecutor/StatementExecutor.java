package writer.statementexecutor;

import job.transform.types.RwRecord;
import writer.statementbuild.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface StatementExecutor {
    int executeStatement(RwRecord record) throws DatabaseException;
    Connection getConnection();
    PreparedStatement getStatement();
    void close() throws DatabaseException;
}
