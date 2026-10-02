package designPatterns.paginationImplementation;

import java.util.*;
import java.util.stream.Collectors;

public class CoffeeApi {

    private List<CoffeeOrder> inMemoryCoffeeOrders;

    public CoffeeApi(List<CoffeeOrder> inMemoryCoffeeOrders) {
        this.inMemoryCoffeeOrders = inMemoryCoffeeOrders;
    }

    //GET call
    public Response fetchCoffeeOrders(boolean status, int pageNumber, int size) {

        //Handling edge case for Bad Request i.e 400
        if (pageNumber < 1 || size < 1) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("Bad Request", "Page number OR asked size shall be > 1");
            return new Response(400, responseMap);
        }

        if (pageNumber > 100 || size > 100) {
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("Bad Request", "Page number OR asked size shall be <= 100");
            return new Response(400, responseMap);
        }


        List<CoffeeOrder> result = inMemoryCoffeeOrders.stream().filter(coffeeOrder ->
                coffeeOrder.isCompletionStatus() == status).collect(Collectors.toList());

        if (!result.isEmpty()) {
            if (!status) {
                result.sort(Comparator.comparing(CoffeeOrder::getOrderCreationTime).thenComparing(CoffeeOrder::getCustomerName));
            } else {
                result.sort(Comparator.comparing(CoffeeOrder::getOrderCreationTime).thenComparing(CoffeeOrder::getCustomerName));
            }
            int startIndex = (pageNumber - 1) * size;
            int endIndex = Math.min(startIndex + size - 1, result.size() - 1);

            List<CoffeeOrder> resultantList = new ArrayList<>(result.subList(startIndex, endIndex + 1));
            return prepareResponse(resultantList);
        } else {
            return prepareResponse(new ArrayList<>());
        }
    }

    private Response prepareResponse(List<CoffeeOrder> result) {
        if (result.isEmpty()) {
            return new Response(200, new HashMap<>());
        }
        Map<String, Object> resultMap = new HashMap<>();
        for (CoffeeOrder order : result) {
            resultMap.put(order.getOrderId(), order);
        }
        return new Response(200, resultMap);
    }

}
