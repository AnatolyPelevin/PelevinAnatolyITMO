package writer.statementexecutor;

import writer.statementbuild.DatabaseException;

public interface StatementExecutorFactory {
    StatementExecutor createStatementExecutor(String statement) throws DatabaseException;
}
