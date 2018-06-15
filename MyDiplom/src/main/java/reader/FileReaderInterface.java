package reader;

import java.io.File;

public interface FileReaderInterface extends RecordReader<String> {
    void setFile(File file);
    File getFile();

}
