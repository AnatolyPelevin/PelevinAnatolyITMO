package HomeTask9Utils;

public enum ExceptionEnum {
    NO_FILE("File does not exists!"),
    NOT_WRITABLE("Can't write to file! It is not writable"),
    EMPTY_ARG("Empty argument!");


    private String errorText;

    ExceptionEnum(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
