package reader.fileattribute;

import reader.FileReaderInterface;
import reader.RecordReaderException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AttributeAwareFileParserImpl implements AttributeAwareFileParser {
    private FileReaderInterface wrappedParser;
    private FileAttributeParser attributeParser;
    private Map<String, Object> fileAttributes = new HashMap<String, Object>();
    private String header;

    public AttributeAwareFileParserImpl(FileReaderInterface wrappedParser,
                                        FileAttributeParser attributeParser) {
        if (wrappedParser == null) {
            throw new IllegalArgumentException("Wrapped parser is null.");
        }

        if (attributeParser == null) {
            throw new IllegalArgumentException("Attribute parser is null.");
        }

        this.wrappedParser = wrappedParser;
        this.attributeParser = attributeParser;
    }

    public String getFileHeader() {
        return header;
    }

    public Map<String, Object> getFileAttributes() {
        return fileAttributes;
    }

    @Override
    public void setFile(File file) {

    }

    public File getFile() {
        return wrappedParser.getFile();
    }

    public String nextRecord() throws RecordReaderException {
        String record = wrappedParser.nextRecord();

        if (header == null) {
            header = record;
        }

        if (record != null) {
            FileAttribute attribute = attributeParser.parseAttribute(record);

            if (attribute != null) {
                fileAttributes.put(attribute.getName(), attribute.getValue());
            }
        }

        return record;
    }

    public void close() throws RecordReaderException {
        wrappedParser.close();
    }
}
