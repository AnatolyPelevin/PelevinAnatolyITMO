package reader;

public class RecordReaderException  extends Exception {
    private String filename;

    public RecordReaderException() {
    }

    public RecordReaderException(String message) {
        super(message);
    }

    public RecordReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordReaderException(Throwable cause) {
        super(cause);
    }

    public RecordReaderException(Throwable cause, String filename) {
        super(cause);
        this.filename = filename;
    }

    public RecordReaderException(String message, Throwable cause, String filename) {
        super(message, cause);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}