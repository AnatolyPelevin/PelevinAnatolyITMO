package Pizzeria;

import static Pizzeria.OrderStatus.*;

public class Order {
   private OrderStatus status;
   private final String dishName;
   private OrderStatus[] orderSteps = {NOT_ISSUED, WAITING_FOR_WAITER, ORDERED_BY_WAITER, COOKING, COOKED, DELIVERING_TO_CLIENT, DELIVERED};
   private int stepNum=0;

   public Order(String dishName){
       this.dishName = dishName;
       this.setStatus(NOT_ISSUED);
   }

   public void setNextOrderStep(){
       stepNum  =(stepNum < (orderSteps.length-1)) ?  stepNum+1 : 0;
       setStatus(orderSteps[stepNum]);
   }

   public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDishName() {
        return dishName;
    }


}
