package reader.recordparser;

import job.transform.types.RwRecord;

public interface RawRecordParserInterface {
    //List<ETLRecord> parseRecord(String record) throws RecordParserException;
   RwRecord parseRecord(String record) throws RecordParserException;
}
