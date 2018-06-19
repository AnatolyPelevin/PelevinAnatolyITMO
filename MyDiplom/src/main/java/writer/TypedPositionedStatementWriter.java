package writer;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Date;

public interface TypedPositionedStatementWriter {
    void writeLong(Long value, int position, PreparedStatement statement) throws RecordWriterException;
    void writeDate(Date value, int position, PreparedStatement statement) throws RecordWriterException;
    void writeBoolean(Boolean value, int position, PreparedStatement statement) throws RecordWriterException;
    void writeString(String value, int position, PreparedStatement statement) throws RecordWriterException;
    void writeDouble(Double value, int position, PreparedStatement statement) throws RecordWriterException;
    void writeBigDecimal(BigDecimal value, int position, PreparedStatement statement) throws RecordWriterException;
}
