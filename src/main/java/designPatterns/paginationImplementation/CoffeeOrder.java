package designPatterns.paginationImplementation;

public class CoffeeOrder {

    private String orderId;
    private String customerName;
    private boolean completionStatus;
    private Long completionTimeInEpochs;
    private long orderCreationTime;

    public CoffeeOrder(String orderId, String customerName, long orderCreationTime) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderCreationTime = orderCreationTime;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setCompletionTimeInEpochs(Long completionTimeInEpochs) {
        this.completionTimeInEpochs = completionTimeInEpochs;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public Long getCompletionTimeInEpochs() {
        return completionTimeInEpochs;
    }

    public long getOrderCreationTime() {
        return orderCreationTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", completionStatus=" + completionStatus +
                ", completionTimeInEpochs=" + completionTimeInEpochs +
                ", orderCreationTime=" + orderCreationTime +
                '}';
    }
}
