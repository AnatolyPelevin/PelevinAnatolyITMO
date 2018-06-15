package writer;

public class RecordWriterException extends Exception {
    private boolean notToLog = false;

    public RecordWriterException() {
    }

    public RecordWriterException(String message) {
        super(message);
    }

    public RecordWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordWriterException(Throwable cause) {
        super(cause);
    }

    public boolean isNotToLog() {
        return notToLog;
    }

    public void setNotToLog(boolean notToLog) {
        this.notToLog = notToLog;
    }
}
