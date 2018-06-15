package reader;

import java.io.*;

public class FlatFileReader extends AbstractFileReader {
    private BufferedReader reader;
    private File file;

    public FlatFileReader(File file) throws RecordReaderException {
        try {
            reader = new BufferedReader(new FileReader(file));
        }
        catch (FileNotFoundException e) {
            throw new RecordReaderException(e, file.getName());
        }

        this.file = file;
    }

    public String nextRecord() throws RecordReaderException {
        try {
            return reader.readLine();
        }
        catch (IOException e) {
            throw new RecordReaderException(e, file.getName());
        }
    }

    public void close() throws RecordReaderException {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException e) {
                throw new RecordReaderException(e);
            }
        }
    }

}
