package designPatterns.paginationImplementation;

public class CoffeeOrder {

    private String orderId;
    private String customerName;
    private boolean completionStatus;
    private Long completionTimeInEpochs;

    public CoffeeOrder(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setCompletionTimeInEpochs(Long completionTimeInEpochs) {
        this.completionTimeInEpochs = completionTimeInEpochs;
    }
}
