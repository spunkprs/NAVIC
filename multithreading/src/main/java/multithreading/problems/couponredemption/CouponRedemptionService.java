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
        AtomicInteger state = new AtomicInteger(0);
        String userId = couponToUserMap.putIfAbsent(coupon.getCouponId(), user.getUserId());
        state.incrementAndGet();
        if (userId != null) {
            return "ALREADY_REDEEMED";
        } else {
            try {
                couponCountPerUser.compute(user.getUserId(), (k, v) -> {
                    if (v == null) {
                        return 1;
                    } else if (v >= maxCouponsRedemptionsPerUser) {
                        throw new RuntimeException("Max Coupons Availed Already !!");
                    } else {
                        return v + 1;
                    }
                });

                state.incrementAndGet();

                userDiscountCredits.compute(user.getUserId(), (k, v) -> {
                   if (v == null) {
                       return coupon.getCredit();
                   } else {
                       return v + coupon.getCredit();
                   }
                });

                state.incrementAndGet();
                return "REDEEMED_SUCCESSFULLY";

            } catch (RuntimeException e) {
                if (state.get() == 1) {
                    couponToUserMap.remove(coupon.getCouponId(), user.getUserId());
                } else if (state.get() == 2) {
                    couponCountPerUser.computeIfPresent(user.getUserId(), (k, v) -> v - 1);
                    couponToUserMap.remove(coupon.getCouponId(), user.getUserId());
                } else if (state.get() == 3) {
                    userDiscountCredits.computeIfPresent(user.getUserId(), (k, v) -> v - coupon.getCredit());
                    couponCountPerUser.computeIfPresent(user.getUserId(), (k, v) -> v - 1);
                    couponToUserMap.remove(coupon.getCouponId(), user.getUserId());
                }
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
