package jpmorgan.functionalprogrammingpractice;

import java.util.*;
import java.util.stream.Collectors;

/**
Constraints:-
a.) traders itself may be null.
b.) Ignore traders with null desks.
c.) Ignore null transaction lists.
d.) Ignore transactions with null instruments.
e.) Avoid explicit loops.
f.) Try to avoid intermediate maps.
g.) Use flatMapping() as part of the solution.
 * */

public class FPBoundFlatMapUsage {

    public static void main(String ar[]) {

    }

    class Trader {
        private String desk;
        private List<Transaction> transactions;

        public String getDesk() {
            return desk;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }
    }

    class Transaction {
        private String instrument;

        public String getInstrument() {
            return instrument;
        }
    }

    public List<Integer> simpleFlattenedExample(List<List<Integer>> numbers) {
        return numbers.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public Map<String, Set<String>> instrumentsByDesk(List<Trader> traders) {
        /**
        //Approach 1, making use of flatMapping but it's available from Java 9 onwards hence can't use it in Java 8
         return Optional.ofNullable(traders)
         .orElseGet(Collections::emptyList)
         .stream()
         .filter(trader -> trader.getDesk() != null)
         .filter(trader -> trader.getTransactions() != null)
         .collect(Collectors.groupingBy(
         Trader::getDesk,
         Collectors.flatMapping(
         trader -> trader.getTransactions()
         .stream()
         .filter(Objects::nonNull)
         .map(Transaction::getInstrument)
         .filter(Objects::nonNull),
         Collectors.toSet()
         )
         ));
         * */


        //Approach 2, suitable for Java 8
        return Optional.ofNullable(traders)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(trader -> trader.getDesk() != null)
                .filter(trader -> trader.getTransactions() != null)
                .flatMap(trader ->
                        trader.getTransactions().stream()
                                .filter(Objects::nonNull)
                                .filter(tx -> tx.getInstrument() != null)
                                .map(tx -> new AbstractMap.SimpleEntry<>(
                                        trader.getDesk(),
                                        tx.getInstrument()
                                ))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(
                                Map.Entry::getValue,
                                Collectors.toSet()
                        )
                ));
    }
}
