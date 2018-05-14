package Pizzeria;

import java.util.ArrayList;

public class Cook implements Runnable{
    private final Order order;
    private final String cookName;
    private ArrayList<OrderStatus> orderSteps  = new ArrayList<OrderStatus>();

    public Cook(Order order, String cookName) {
        this.order = order;
        this.cookName = cookName;
        orderSteps.add(OrderStatus.ORDERED_BY_WAITER);
        orderSteps.add(OrderStatus.COOKING);
    }



    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try{
                synchronized (order){
                    order.wait();
                    while(order.getStatus() != OrderStatus.COOKED){
                        if (orderSteps.contains(order.getStatus())){
                            order.setNextOrderStep();
                            System.out.println(order.getStatus().getOrderStatusName());
                            order.notifyAll();
                        }
                    }

                    if(order.getStatus() == OrderStatus.COOKED){
                        Thread.currentThread().interrupt();
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("Cook--> run method: ");
                e.printStackTrace();
            }
        }


    }
}
