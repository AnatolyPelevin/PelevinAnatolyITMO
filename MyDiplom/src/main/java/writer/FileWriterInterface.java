package writer;

import job.transform.types.FieldConversionException;
import job.transform.types.RwRecord;

public interface FileWriterInterface {
    int writeRecord(RwRecord rwRecord ) throws RecordWriterException, FieldConversionException;
    void close() throws RecordWriterException;
}
