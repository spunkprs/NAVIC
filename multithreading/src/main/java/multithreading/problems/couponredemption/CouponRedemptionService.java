package multithreading.problems.couponredemption;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CouponRedemptionService {

    private ConcurrentHashMap<String, Integer> userDiscountCredits = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> couponToUserMap = new ConcurrentHashMap<>();

    public String redeemCoupon(Coupon coupon, User user) {
        String userId = null;
        AtomicInteger state = new AtomicInteger(0);

        try {
            if ((userId = couponToUserMap.putIfAbsent(coupon.getCouponId(), user.getUserId())) == null) {
                state.incrementAndGet();
                userDiscountCredits.compute(user.getUserId(), (k, v) -> {
                    state.incrementAndGet();
                    if (v == null) {
                        return coupon.getCredit();
                    } else {
                        return v + coupon.getCredit();
                    }
                });
            } else {
                return "ALREADY_REDEEMED";
            }
            return "REDEEMED_SUCCESSFULLY";
        } catch(Exception e) {
            if (state.get() == 1) {
                couponToUserMap.remove(coupon.getCouponId());
            }
            return "REDEEMED_FAILURE";
        }
    }

    public ConcurrentHashMap<String, Integer> getUserDiscountCredits() {
        return userDiscountCredits;
    }

    public ConcurrentHashMap<String, String> getCouponToUserMap() {
        return couponToUserMap;
    }
}
