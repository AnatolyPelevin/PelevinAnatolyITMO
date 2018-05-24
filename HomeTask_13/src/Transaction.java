import java.util.concurrent.Callable;

public  class Transaction implements Callable<TransactionEvent> {

    private final Account srcAcc;
    private final Account dstAcc;
    private final Bank bank;
    private final int amount;


    public  Transaction(Account srcAcc, Account dstAcc, Bank bank, int amount){
        this.srcAcc = srcAcc;
        this.dstAcc = dstAcc;
        this.bank = bank;
        this.amount = amount;
    }

    @Override
    public TransactionEvent call() throws Exception {
        return bank.transferMoney(srcAcc,dstAcc,amount);
    }

    public Account getSrcAcc() {
        return srcAcc;
    }

    public Account getDstAcc() {
        return dstAcc;
    }

    public Bank getBank() {
        return bank;
    }

    public int getAmount() {
        return amount;
    }
}
