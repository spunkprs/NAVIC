package multithreading.problems.couponredemption;

public class Coupon {

    private String couponId;
    private int credit;

    public Coupon(String couponId, int credit) {
        this.couponId = couponId;
        this.credit = credit;
    }

    public String getCouponId() {
        return couponId;
    }

    public int getCredit() {
        return credit;
    }
}
