package reader;

import reader.recordfilter.RawRecordFilter;

import java.io.File;

public class FilteredFileParser implements FileReaderInterface {
    private FileReaderInterface wrappedParser;
    private RawRecordFilter recordFilter;

    public FilteredFileParser(FileReaderInterface wrappedParser, RawRecordFilter recordFilter) {
        this.wrappedParser = wrappedParser;
        this.recordFilter = recordFilter;
    }

    public String nextRecord() throws RecordReaderException {
        String line;

        while ((line = wrappedParser.nextRecord()) != null && !recordFilter.accepts(line)) {
        }

        return line;
    }

    public void close() throws RecordReaderException {
        wrappedParser.close();
    }

    @Override
    public void setFile(File file) {

    }

    public File getFile() {
        return wrappedParser.getFile();
    }
}
