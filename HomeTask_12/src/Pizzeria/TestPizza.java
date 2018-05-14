package Pizzeria;

public class TestPizza {
    public static void testPizza(){
        System.out.println("*******Test Pizza!*******");
        Order order = new Order("New pizza");
        Cook cook   = new Cook(order, "Cook 1");
        Waiter waiter  = new Waiter(order,"Waiter 1");
        Client client  = new Client(order,"Client 1");

        Thread cookThread  = new Thread(cook);
        Thread waiterThread  = new Thread(waiter);
        Thread clientThread  = new Thread(client);

        cookThread.start();
        waiterThread.start();
        clientThread.start();
        System.out.println("*************************");
    }
}
