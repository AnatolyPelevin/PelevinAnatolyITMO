import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Bank bank = new Bank();
        for (int i = 0; i < 10; i++ ) {
            User u = new User("User mail: " + i);
            Account a = new Account(u.getUserID(),100 + i*4);
            bank.getAccList().add(a);
            if (i%2 == 0){
                Account a1 = new Account(u.getUserID(),100 + i*4);
                bank.getAccList().add(a1);
            }
        }

        BlockingQueue<Transaction> transactions = new LinkedBlockingQueue<>();
        int n= bank.getAccList().size();
        for (int i = 0; i < 10; i++ ) {
            Transaction transaction = new Transaction(
                    bank.getAccList().get(i),
                    bank.getAccList().get(10-i),
                    bank,
                    10*i + 2);
            transactions.add(transaction);
        }


        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpuCount);
        List<Future<TransactionEvent>> futs = new ArrayList<>();


        for (int i = 0; i < cpuCount; i++) {
            Future<TransactionEvent> fut = pool.submit(transactions.take());
            futs.add(fut);
        }

        for (Future<TransactionEvent> fut : futs) {
            TransactionEvent transactionEvent = fut.get();
            System.out.println(transactionEvent.transactionEventMessage());
        }

        pool.shutdown();
    }

}
