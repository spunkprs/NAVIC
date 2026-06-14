package jpmorgan.functionalprogrammingpractice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FPBoundTreeMapUsage {


    public static void main(String ar[]) {

    }

    /**
    Grouping making use of TreeMap but no reduce as such
     * */
    public Map<LocalDate, Set<String>> tradersByTradeDate(List<Transaction> transactions) {

        return Optional.ofNullable(transactions).
                orElseGet(Collections :: emptyList).
                stream().
                filter(tx -> tx.getTraderName() != null).
                filter(tx -> tx.getTradeDate() != null).
                collect(Collectors.groupingBy(Transaction::getTradeDate, TreeMap::new,
                        Collectors.mapping(Transaction::getTraderName, Collectors.toSet())));

    }

    /**
     Grouping making use of TreeMap with reduce as well
     * */

    public Map<LocalDate, BigDecimal> dailyExposure(List<Transaction> transactions) {
        return Optional.ofNullable(transactions).
                orElseGet(Collections :: emptyList).
                stream().
                filter(tx -> tx.getTraderName() != null).
                filter(tx -> tx.getAmount() != null).
                filter(tx -> tx.getTradeDate() != null).
                collect(Collectors.groupingBy(Transaction::getTradeDate, TreeMap::new,
                        Collectors.mapping(Transaction::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }


    class Transaction {
        private LocalDate tradeDate;
        private String traderName;
        private BigDecimal amount;

        public LocalDate getTradeDate() {
            return tradeDate;
        }

        public String getTraderName() {
            return traderName;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }
}
