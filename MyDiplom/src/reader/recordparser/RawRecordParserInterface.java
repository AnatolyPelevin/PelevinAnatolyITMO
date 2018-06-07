package reader.recordparser;

import job.transform.types.ETLRecord;

import java.util.List;

public interface RawRecordParserInterface {
    List<ETLRecord> parseRecord(String record) throws RecordParserException;
}
