public class OrderManager {
    private Order currentOrder;
    private boolean isConfirmed;

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    public Order getOrderDetails() {
        return currentOrder;
    }

    public void confirmOrder() {
        if (currentOrder != null) {
                currentOrder.confirm();
                isConfirmed = true;
        }
    }

    public boolean cancelOrder() {
        if (currentOrder != null) {
            currentOrder.cancel();
            isConfirmed = false;
            return true;
        }
        return false;
    }
}

