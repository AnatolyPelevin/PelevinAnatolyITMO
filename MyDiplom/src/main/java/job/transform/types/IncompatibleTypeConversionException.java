package job.transform.types;

public class IncompatibleTypeConversionException extends FieldConversionException {
    public IncompatibleTypeConversionException() {
    }

    public IncompatibleTypeConversionException(String message) {
        super(message);
    }

    public IncompatibleTypeConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleTypeConversionException(Throwable cause) {
        super(cause);
    }
}
