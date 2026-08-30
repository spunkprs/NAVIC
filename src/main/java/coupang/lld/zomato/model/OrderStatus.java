package coupang.lld.zomato.model;

public enum OrderStatus {

    INITIATED,
    PAYMENT_SUCCESSFUL,
    PAYMENT_DENIED,
    CANCELLED_BY_USER,
    DENIED_BY_RESTUARANT,
    DENIED_BY_RIDER,
    ACCEPTED_BY_RIDER,
    DELIVERED
}
