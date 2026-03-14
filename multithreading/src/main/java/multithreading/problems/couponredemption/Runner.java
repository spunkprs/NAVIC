package multithreading.problems.couponredemption;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static void main(String ar[]) {
        CouponRedemptionService couponRedemptionService = new CouponRedemptionService();

        Coupon couponOne = new Coupon("c1", 10);
        Coupon couponTwo = new Coupon("c2", 30);
        Coupon couponThree = new Coupon("c3", 20);



        User userOne = new User("ABC1");
        User userTwo = new User("ABC2");

        User userThree = new User("ABC3");
        User userFour = new User("ABC4");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.submit(() -> {
            couponRedemptionService.redeemCoupon(couponOne, userThree);
        });

        executorService.submit(() -> {
            couponRedemptionService.redeemCoupon(couponOne, userOne);
        });

        executorService.submit(() -> {
            couponRedemptionService.redeemCoupon(couponOne, userTwo);
        });

        try {
            Thread.sleep(500);
            executorService.submit(() -> {
                couponRedemptionService.redeemCoupon(couponTwo, userOne);
            });

            executorService.submit(() -> {
                couponRedemptionService.redeemCoupon(couponThree, userFour);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        ConcurrentHashMap<String, Integer> userToCouponPoints = couponRedemptionService.getUserDiscountCredits();
        for (String userId : userToCouponPoints.keySet()) {
            System.out.println("Coupon credits for userId " + userId + " is " + userToCouponPoints.get(userId));
        }
    }
}
