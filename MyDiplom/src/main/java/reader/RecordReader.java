package reader;

public interface RecordReader <T> {
    T nextRecord() throws RecordReaderException;
    void close() throws RecordReaderException;
}
