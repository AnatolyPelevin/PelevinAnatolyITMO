package job.transform.types;

public class FieldConversionException extends Exception {
    public FieldConversionException() {
    }

    public FieldConversionException(String message) {
        super(message);
    }

    public FieldConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldConversionException(Throwable cause) {
        super(cause);
    }
}
