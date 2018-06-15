package writer.statementbuild;


import job.transform.types.RwRecord;
import objectmodel.RwView;

public interface StatementBuilder {
    String buildSelectStatement(RwView view) throws DatabaseException;
    String buildInsertStatement(RwRecord rwRecord) throws DatabaseException;
    String buildInsertUpdateStatement(RwRecord rwRecord) throws DatabaseException;
    String buildUpdateStatement(RwRecord rwRecord) throws DatabaseException;
    String buildDeleteStatement(RwRecord rwRecord) throws DatabaseException;
    String buildSPCallStatement(RwRecord rwRecord) throws DatabaseException;
}
