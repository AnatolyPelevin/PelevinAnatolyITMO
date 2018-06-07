package reader.recordparser;

import objectmodel.RwFile;

public interface RawRecordParserFactory {
    RawRecordParserInterface createRecordParser(RwFile file) throws RecordParserException;
}
