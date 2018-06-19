package writer.command;

import dictionaries.DataType;
import job.transform.types.FieldConversionException;
import objectmodel.RwField;
import utils.NullArgumentException;
import writer.RecordWriterException;
import writer.TypedPositionedStatementWriter;
import writer.TypedRecordWriter;
import writer.statementbuild.StatementBuilder;
import writer.statementexecutor.StatementExecutor;
import writer.statementexecutor.StatementExecutorFactory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public abstract class AbstractDatabaseCommand implements TypedRecordWriter, TypedPositionedStatementWriter {
    protected StatementExecutor statementExecutor;
    protected int position;
    protected StatementBuilder statementBuilder;
    protected StatementExecutorFactory statementExecutorFactory;

    protected AbstractDatabaseCommand(StatementBuilder statementBuilder,
                                      StatementExecutorFactory statementExecutorFactory) {
        if (statementBuilder == null) {
            throw new NullArgumentException("statementBuilder");
        }

        if (statementExecutorFactory == null) {
            throw new NullArgumentException("statementExecutorFactory");
        }

        this.statementBuilder = statementBuilder;
        this.statementExecutorFactory = statementExecutorFactory;
    }

    public void writeLong(Long value) throws RecordWriterException {
        writeLong(value, position, statementExecutor.getStatement());
    }

    public void writeDate(Date value) throws RecordWriterException {
        writeDate(value, position, statementExecutor.getStatement());
    }

    public void writeBoolean(Boolean value) throws RecordWriterException {
        writeBoolean(value, position, statementExecutor.getStatement());
    }

    public void writeString(String value) throws RecordWriterException {
        writeString(value, position, statementExecutor.getStatement());
    }

    public void writeDouble(Double value) throws RecordWriterException {
        writeDouble(value, position, statementExecutor.getStatement());
    }

    public void writeBigDecimal(BigDecimal value) throws RecordWriterException {
        writeBigDecimal(value, position, statementExecutor.getStatement());
    }

    @Override
    public void writeLong(Long value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            if (value == null) {
                statement.setNull(position, Types.BIGINT);
            }
            else {
                statement.setLong(position, value);
            }
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }

    @Override
    public void writeDate(Date value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            if (value == null) {
                statement.setNull(position, Types.TIMESTAMP);
            }
            else {
                statement.setTimestamp(position, new Timestamp(value.getTime()));
            }
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }

    @Override
    public void writeBoolean(Boolean value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            if (value == null) {
                statement.setNull(position, Types.BOOLEAN);
            }
            else {
                statement.setBoolean(position, value);
            }
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }

    @Override
    public void writeString(String value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            statement.setString(position, value);
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }

    @Override
    public void writeDouble(Double value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            if (value == null) {
                statement.setNull(position, Types.DOUBLE);
            }
            else {
                statement.setDouble(position, value);
            }
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }

    @Override
    public void writeBigDecimal(BigDecimal value, int position, PreparedStatement statement) throws RecordWriterException {
        try {
            statement.setBigDecimal(position, value);
        }
        catch (SQLException e) {
            throw new RecordWriterException(e);
        }
    }


    public void writeValue(RwField field, PreparedStatement preparedStatement) throws RecordWriterException, FieldConversionException {
       DataType dataType  = field.getDataType();

       switch (dataType){
           case STRING:
               writeString(field.getFieldValue(), position, preparedStatement) ;
               break;
           case DATE:
               Date valueDate = ConvertValues.setDateFromString(field);
               writeDate(valueDate, position, preparedStatement) ;
               break;
           case DECIMAL:
               BigDecimal valueDecimal = ConvertValues.setBigDecimalFromString(field);
               writeBigDecimal(valueDecimal, position, preparedStatement) ;
               break;
           case INTEGER:
               Long valueInteger = Long.valueOf(field.getFieldValue());
               writeLong(valueInteger, position, preparedStatement);
               break;
           case DOUBLE:
               Double valueDouble = Double.parseDouble(field.getFieldValue());
               writeDouble(valueDouble, position, preparedStatement);
               break;
           case BOOLEAN:
               Boolean valueBoolean = ConvertValues.setBooleanFromString(field);
               writeBoolean(valueBoolean, position, preparedStatement);
               break;
               default:
                   throw new RecordWriterException("Type not supported!");
       }
    }


    public void close() throws RecordWriterException {
        if (statementExecutor != null) {
            statementExecutor.close();
        }
    }
}
