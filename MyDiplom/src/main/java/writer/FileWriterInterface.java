package writer;

import job.transform.types.RwRecord;

public interface FileWriterInterface {
    int writeRecord(RwRecord rwRecord ) throws RecordWriterException;
    void close() throws RecordWriterException;
}
