package reader.fileattribute;

import reader.FileReaderInterface;
import reader.RecordReaderException;

import java.io.File;
import java.util.Map;

public class AttributeAwareFileParserWrapper implements AttributeAwareFileParser {
    private FileAttributesProvider fileAttributeProvider;
    private FileReaderInterface wrappedParser;

    public AttributeAwareFileParserWrapper(FileAttributesProvider fileAttributeProvider,
                                           FileReaderInterface wrappedParser) {
        if (fileAttributeProvider == null) {
            throw new IllegalArgumentException("File attribute provider is null.");
        }

        if (wrappedParser == null) {
            throw new IllegalArgumentException("Wrapped parser is null.");
        }

        this.fileAttributeProvider = fileAttributeProvider;
        this.wrappedParser = wrappedParser;
    }

    public String getFileHeader() {
        return fileAttributeProvider.getFileHeader();
    }

    public Map<String, Object> getFileAttributes() {
        return fileAttributeProvider.getFileAttributes();
    }

    @Override
    public void setFile(File file) {

    }

    public File getFile() {
        return wrappedParser.getFile();
    }

    public String nextRecord() throws RecordReaderException {
        return wrappedParser.nextRecord();
    }

    public void close() throws RecordReaderException {
        wrappedParser.close();
    }
}
