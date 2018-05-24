import BankException.BankExceptionEnum;
import BankException.BankException;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accList;

  public Bank(){
      accList = new ArrayList<>();
  }

    public Bank(List<Account> accList){
        this.accList = accList;
    }

  public TransactionEvent transferMoney(Account src, Account dst, int amount) throws BankException {

      if(src == null || dst==null){
          throw new BankException(BankExceptionEnum.EMPTY_ACCOUNT.getErrorText());
      }

      if (src.equals(dst)){
          throw new BankException(BankExceptionEnum.SAME_ACCOUNT.getErrorText());
      }

      if (amount<=0) {
          throw new BankException(BankExceptionEnum.WRONG_AMOUNT.getErrorText());
      }
      final Account o1;
      final Account o2;

      if (src.getAccountID() <= dst.getAccountID()) {
         o1 = src;
         o2 = dst;
      } else {
          o1 = dst;
          o2 = src;
      }

      synchronized (o1){
          synchronized (o2){
              int oldSRCAmount= src.getAmount();
              int oldDSTAmount = dst.getAmount();
              if ((oldSRCAmount - amount)<0){
                  return new TransactionEvent(src.getAccountID(),dst.getAccountID() , src.getUserID(),dst.getUserID() , amount, oldSRCAmount, oldDSTAmount, false);
              } else {
                  src.setAmount(oldSRCAmount - amount);
                  src.setAmount(oldDSTAmount + amount);
              }
              return new TransactionEvent(src.getAccountID(), dst.getAccountID() , src.getUserID(),dst.getUserID() , amount, oldSRCAmount, oldDSTAmount, true);
          }
      }
  }


    public List<Account> getAccList() {
        return accList;
    }

    public void setAccList(List<Account> accList) {
        this.accList = accList;
    }

    public Account resolveAccountById(int accountId) {
        return accList.stream().filter(account -> account.getAccountID() == accountId).findFirst().get();
    }

    public void addAccount(Account account) {
        this.accList.add(account);
    }
}
