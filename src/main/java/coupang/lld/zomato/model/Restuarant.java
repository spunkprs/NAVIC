package coupang.lld.zomato.model;

import java.util.List;

public class Restuarant {
    private String id;
    private String name;
    private Location location;
    private RestuarantStatus restuarantStatus;
    private List<RestuarantFoodItem> restuarantFoodItemList;
    private List<CuisineCategory> servedCuisines;
    private RestuarantFeedbackMatrix restuarantFeedbackMatrix;
}
