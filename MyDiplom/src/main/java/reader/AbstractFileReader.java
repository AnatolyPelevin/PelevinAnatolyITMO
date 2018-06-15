package reader;

import java.io.File;

public class AbstractFileReader implements FileReaderInterface {
    private File file;
    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public String nextRecord() throws RecordReaderException {
        return null;
    }

    @Override
    public void close() throws RecordReaderException {

    }
}
