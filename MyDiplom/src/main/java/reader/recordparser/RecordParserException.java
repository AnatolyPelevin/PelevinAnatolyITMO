package reader.recordparser;

import job.transform.types.RwRecord;
import reader.RecordReaderException;

public class RecordParserException extends RecordReaderException {
    private RwRecord recordMetadata;
    private String record;
    public RecordParserException(String record) {
        this((RwRecord) null, record);
    }

    public RecordParserException(RwRecord recordMetadata, String record) {
        this.recordMetadata = recordMetadata;
        this.record = record;
    }

    public RecordParserException(Throwable cause, RwRecord recordMetadata, String record) {
        super(cause);
        this.recordMetadata = recordMetadata;
        this.record = record;
    }

    public RecordParserException(String message,
                                 Throwable cause,
                                 RwRecord recordMetadata,
                                 String record) {
        super(message, cause);
        this.recordMetadata = recordMetadata;
        this.record = record;
    }

    public RecordParserException(Throwable cause, String record) {
        super(cause);
        this.record = record;
    }

    public RwRecord getRecordMetadata() {
        return recordMetadata;
    }

    public String getRecord() {
        return record;
    }
}
