import java.util.*;

public class EbayTechnicalAssessmentPractice {

    public static void main(String ar[]) {
        EbayTechnicalAssessmentPractice unit = new EbayTechnicalAssessmentPractice();

        Map<String, Integer> map = new HashMap<>();
        //prepare i/p data

        map.put("ABC", 10000);
        map.put("XYZ", 10000);
        map.put("PQR", 20000);
        map.put("MNP", 5000);
        map.put("PPP", 5000);
        map.put("RRR", 5000);

        List<String> resultantCustomers = unit.fetchCustomersWithMaxAmountWithdrawls(3, map);
        resultantCustomers.forEach(customer -> {
            System.out.println(customer);
        });

    }

    public List<String> fetchCustomersWithMaxAmountWithdrawls(int limit, Map<String, Integer> mapping) {
        List<String> resultantCustomers  = new ArrayList<>();
        TreeMap<Integer, List<String>> result = new TreeMap<>();
        for (String customer : mapping.keySet()) {
            int withdrawlAmount = mapping.get(customer);

            if (!result.containsKey(withdrawlAmount)) {
                List<String> customers = new ArrayList<>();
                customers.add(customer);
                result.put(withdrawlAmount, customers);
            } else {
                result.get(withdrawlAmount).add(customer);
            }
        }

        //Sort customers in ascending order of their name
        for (Integer amount : result.keySet()) {
            List<String> customers = result.get(amount);
            Collections.sort(customers);
        }

        int count = 0;
        boolean flag = true;

        for (Integer amount : result.descendingKeySet()) {
            if (flag) {
                List<String> customers = result.get(amount);
                for (String customer : customers) {
                    if (count < limit) {
                        resultantCustomers.add(customer);
                        count++;
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return resultantCustomers;
    }
}
