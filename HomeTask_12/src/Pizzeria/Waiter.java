package Pizzeria;

import java.util.ArrayList;

import static Pizzeria.OrderStatus.COOKED;
import static Pizzeria.OrderStatus.WAITING_FOR_WAITER;

public class Waiter implements Runnable {
    private final Order order;
    private final String waiterName;
    private ArrayList<OrderStatus> orderSteps  = new ArrayList<OrderStatus>();

    public Waiter(Order order, String waiterName) {
        this.order = order;
        this.waiterName = waiterName;
        orderSteps.add(WAITING_FOR_WAITER);
        orderSteps.add(COOKED);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                synchronized (order){
                    order.wait();
                    if(orderSteps.contains(order.getStatus())) {
                        order.setNextOrderStep();
                        System.out.println(order.getStatus().getOrderStatusName());
                        order.notifyAll();

                        if(order.getStatus() == OrderStatus.DELIVERING_TO_CLIENT){
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Waiter--> run method: ");
                e.printStackTrace();
            }
        }


    }
}
