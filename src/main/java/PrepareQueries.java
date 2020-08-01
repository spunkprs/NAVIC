public class PrepareQueries {


    public static void main(String ar[]) {
        int startId = 542139842;
        int endId = 600546142;
        String str = "";
        int end = 0;
        while (startId <= endId) {
            end = startId + 500000;
            str = "insert into txrequest_old (mid, memberid, orderid, createdat, rawrequest, amount, origin, type, walletflow, useragent, newuserflag, walletbalance, devicename, usercreatedat) select tr.mid, tr.memberid, tr.orderid, tr.createdat, tr.rawrequest,tr.amount, tr.origin, tr.type, tr.walletflow, " +
                    "tr.useragent, tr.newuserflag, tr.walletbalance, tr.devicename, tr.usercreatedat from txrequest tr where id >=" + startId
                    + " and id<" + end;
            startId +=500000;
            System.out.println(str);
        }
    }
}
