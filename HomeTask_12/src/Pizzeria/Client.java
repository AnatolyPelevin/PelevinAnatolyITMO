package Pizzeria;

import java.util.ArrayList;

public class Client implements Runnable {
    private final Order order;
    private final String clientName;
    private ArrayList<OrderStatus> orderSteps  = new ArrayList<OrderStatus>();

    public Client(Order order, String clientName) {
        this.order = order;
        this.clientName = clientName;
        orderSteps.add(OrderStatus.NOT_ISSUED);
        orderSteps.add(OrderStatus.DELIVERING_TO_CLIENT);
    }

    @Override
    public void run() {
        synchronized (order){
            System.out.println(order.getStatus().getOrderStatusName());
            order.setNextOrderStep();
            System.out.println(order.getStatus().getOrderStatusName());
            order.notifyAll();
        }

        while(!Thread.currentThread().isInterrupted()){
            try{
                synchronized (order){
                    order.wait();
                    if (orderSteps.contains(order.getStatus())) {
                        order.setNextOrderStep();
                        System.out.println(order.getStatus().getOrderStatusName());
                        order.setNextOrderStep();
                        System.out.println(order.getStatus().getOrderStatusName());
                        if(order.getStatus() == OrderStatus.NOT_ISSUED){
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Client --> run: ");
                e.printStackTrace();
            }

        }
    }
}
