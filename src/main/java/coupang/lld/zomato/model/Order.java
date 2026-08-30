package coupang.lld.zomato.model;

public class Order {
    private String orderId;
    private String restuarantId;
    private Cart cart;
    private String userId;
    private UserAddress deliveryAddress;
    private OrderStatus orderStatus;
    private double finalComputedPrice;
    private long createdTimeStampsInMillis;

    //Skipping discount price && all that can be added here later
    //Skipping Rider information here as part of v1
}
