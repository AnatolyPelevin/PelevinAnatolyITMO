public class TransactionEvent {


    private final int accountIdSRC;
    private final int accountIdDST;
    private final int userIdSRC;
    private final int userIdDST;
    private final int amount;
    private final int amountSRC;
    private final int amountDST;
    private final boolean state;

    public TransactionEvent(int accountIdSRC, int accountIdDST, int userIdSRC, int userIdDST, int amount, int amountSRC, int amountDST, boolean state) {
        this.accountIdSRC = accountIdSRC;
        this.accountIdDST = accountIdDST;
        this.userIdSRC = userIdSRC;
        this.userIdDST = userIdDST;
        this.amount = amount;
        this.amountSRC = amountSRC;
        this.amountDST = amountDST;
        this.state = state;
    }

    public String transactionEventMessage(){
        StringBuilder stringBuilder = new StringBuilder("***********")
                .append(System.lineSeparator());
        stringBuilder.append("Transaction was ");
        String stateForTansaction = (state)? "completed successfully. " :" failed. ";
        stringBuilder.append(stateForTansaction);
        stringBuilder.append("User ")
                .append(userIdSRC)
                .append(" transfer ")
                .append(amount)
                .append(" from account ")
                .append(accountIdSRC);
        if (userIdSRC == accountIdDST){
            stringBuilder.append(" to account ")
                    .append(accountIdDST);
        } else {
            stringBuilder.append(" to User ")
                    .append(userIdDST)
                     .append(" account ")
                    .append(accountIdDST);
        }
        stringBuilder
                .append(System.lineSeparator())
                .append("***********");
        return stringBuilder.toString();
    }

    public int getAccountIdSRC() {
        return accountIdSRC;
    }

    public int getAccountIdDST() {
        return accountIdDST;
    }

    public int getUserIdSRC() {
        return userIdSRC;
    }

    public int getUserIdDST() {
        return userIdDST;
    }

    public int getAmount() {
        return amount;
    }

    public int getAmountSRC() {
        return amountSRC;
    }

    public int getAmountDST() {
        return amountDST;
    }

    public boolean isState() {
        return state;
    }
}
