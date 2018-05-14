package Pizzeria;

public enum OrderStatus {
    NOT_ISSUED ("Order is not issued!"),
    WAITING_FOR_WAITER("Order is waiting for Waiter!"),
    ORDERED_BY_WAITER ("Ordered by Waiter!"),
    COOKING ("The order is cooking!"),
    COOKED ("The order cooked!"),
    DELIVERING_TO_CLIENT ("The order is delivering to the Client!"),
    DELIVERED ("The Order delivered to the Client!")
    ;


    String orderStatusName;

    OrderStatus(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

}
