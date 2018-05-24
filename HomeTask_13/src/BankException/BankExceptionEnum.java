package BankException;

public enum BankExceptionEnum {
    EMPTY_ACCOUNT("Empty account!"),
    SAME_ACCOUNT("You try to make operation on same account!"),
    WRONG_AMOUNT("Wrong amount value! It must be > 0 !");


    private String errorText;

     BankExceptionEnum(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
