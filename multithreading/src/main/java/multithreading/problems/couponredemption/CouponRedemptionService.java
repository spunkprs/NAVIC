package multithreading.problems.couponredemption;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CouponRedemptionService {

    private int maxCouponsRedemptionsPerUser;

    public CouponRedemptionService(int maxCouponsRedemptionsPerUser) {
        this.maxCouponsRedemptionsPerUser = maxCouponsRedemptionsPerUser;
    }

    private ConcurrentHashMap<String, Integer> userDiscountCredits = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> couponCountPerUser = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> couponToUserMap = new ConcurrentHashMap<>();

    public String redeemCoupon(Coupon coupon, User user) {
        String userId = null;
        AtomicInteger state = new AtomicInteger(0);

        try {
            if ((userId = couponToUserMap.putIfAbsent(coupon.getCouponId(), user.getUserId())) == null) {
                state.incrementAndGet();
                couponCountPerUser.compute(user.getUserId(), (k, v) -> {
                    if (v == null) {
                        userDiscountCredits.compute(user.getUserId(), (k1, v1) -> {
                            if (v1 == null) {
                                return coupon.getCredit();
                            } else {
                                return v1 + coupon.getCredit();
                            }
                        });
                        return 1;
                    } else {
                        if (v < maxCouponsRedemptionsPerUser) {
                            userDiscountCredits.compute(user.getUserId(), (k1, v1) -> {
                                if (v1 == null) {
                                    return coupon.getCredit();
                                } else {
                                    return v1 + coupon.getCredit();
                                }
                            });
                            return v + 1;
                        } else {
                            throw new RuntimeException("Max Coupons Availed Already !!");
                        }
                    }
                });
            } else {
                return "ALREADY_REDEEMED";
            }
            return "REDEEMED_SUCCESSFULLY";
        } catch(Exception e) {
            e.printStackTrace();
            if (state.get() == 1) {
                couponToUserMap.remove(coupon.getCouponId());
            } else if (state.get() == 2) {
                couponCountPerUser.computeIfPresent(user.getUserId(), (k, v) -> {
                    couponToUserMap.remove(coupon.getCouponId());
                    return v - 1;
                });
            }
            return "REDEMPTION_FAILURE";
        }
    }

    public ConcurrentHashMap<String, Integer> getUserDiscountCredits() {
        return userDiscountCredits;
    }

    public ConcurrentHashMap<String, Integer> getCouponCountPerUser() {
        return couponCountPerUser;
    }

    public ConcurrentHashMap<String, String> getCouponToUserMap() {
        return couponToUserMap;
    }
}
