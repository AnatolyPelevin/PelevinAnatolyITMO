package writer;

import java.math.BigDecimal;
import java.util.Date;

public interface TypedRecordWriter extends FileWriterInterface {
    void writeLong(Long value) throws RecordWriterException;
    void writeDate(Date value) throws RecordWriterException;
    void writeBoolean(Boolean value) throws RecordWriterException;
    void writeString(String value) throws RecordWriterException;
    void writeDouble(Double value) throws RecordWriterException;
    void writeBigDecimal(BigDecimal value) throws RecordWriterException;
}
