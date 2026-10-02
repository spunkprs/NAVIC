package designPatterns.paginationImplementation;

import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String ar[]) {
        CoffeeOrder coffeeOrderOne = new CoffeeOrder("1111", "ABCD", System.nanoTime());
        CoffeeOrder coffeeOrderTwo = new CoffeeOrder("1211", "PQRS", System.nanoTime());
        CoffeeOrder coffeeOrderThree = new CoffeeOrder("1311", "MNOP", System.nanoTime());
        CoffeeOrder coffeeOrderFour = new CoffeeOrder("1411", "AAAA", System.nanoTime());
        CoffeeOrder coffeeOrderFive = new CoffeeOrder("1511", "ABOP", System.nanoTime());

        List<CoffeeOrder> orders = Arrays.asList(coffeeOrderOne, coffeeOrderTwo, coffeeOrderThree, coffeeOrderFour, coffeeOrderFive);

        CoffeeApi coffeeApi = new CoffeeApi(orders);

        printResponse(coffeeApi.fetchCoffeeOrders(false, 0, -1));
        printResponse(coffeeApi.fetchCoffeeOrders(false, 90, 101));

        Response responseForUnPreparedCoffeeOrders = coffeeApi.fetchCoffeeOrders(false, 2, 2);

        printResponse(responseForUnPreparedCoffeeOrders);

        coffeeOrderOne.setCompletionStatus(true);
        coffeeOrderOne.setCompletionTimeInEpochs(System.nanoTime());

        coffeeOrderTwo.setCompletionStatus(true);
        coffeeOrderTwo.setCompletionTimeInEpochs(System.nanoTime());

        coffeeOrderThree.setCompletionStatus(true);
        coffeeOrderThree.setCompletionTimeInEpochs(System.nanoTime());

        Response responseForPreparedCoffeeOrders = coffeeApi.fetchCoffeeOrders(true, 1, 2);

        printResponse(responseForPreparedCoffeeOrders);
    }

    private static void printResponse(Response response) {
        System.out.println("Response status is " + response.getStatusCode());

        System.out.println("Printing response body :: ");

        if (response.getStatusCode() == 200) {
            for (String orderId : response.getResponseBody().keySet()) {
                CoffeeOrder responseOrder = (CoffeeOrder) response.getResponseBody().get(orderId);
                System.out.println(responseOrder);
            }
        } else if (response.getStatusCode() == 400) {
            for (String key : response.getResponseBody().keySet()) {
                String errorResponse = (String)response.getResponseBody().get(key);
                System.out.println(errorResponse);
            }
        }
    }
}
