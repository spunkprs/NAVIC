package coupang.lld.zomato.model;

import java.util.List;

public class OrderItem {
    private String orderId;
    private String foodItemId;
    private String foodItemName;
    private double priceAtOrderTime;
    private int qtyOrdered;
    private List<OrderItemTopping> selectedToppingsList;
}
