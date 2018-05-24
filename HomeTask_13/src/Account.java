public class Account {
    private static int accountIDGenerate =0;
    private final int accountID;
    private final int userID;
    private int amount;


    public Account(int userID) {
        this.accountID = accountIDGenerate++;
        this.userID = userID;
    }

    public Account(int userID, int amount) {
       this(userID);
       this.amount = amount;
    }


    public int getAccountID() {
        return accountID;
    }

    public int getUserID() {
        return userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return accountID == account.accountID;
    }

    @Override
    public int hashCode() {
        return 31 * accountID + userID;
    }
}
