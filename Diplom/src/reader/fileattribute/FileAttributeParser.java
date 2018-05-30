package reader.fileattribute;

import reader.RecordReaderException;

public interface FileAttributeParser {
    FileAttribute parseAttribute(String record) throws RecordReaderException;
}
