package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;
import lambdasinaction.chap5.*;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {
    public static void main(String... args) {

        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        Trader raoul = new Trader("Raoul", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        /*List<Transaction> tr2011 = transactions.stream()
                                               .filter(transaction -> transaction.getYear() == 2011)
                                               .sorted(comparing(Transaction::getValue))
                                               .collect(toList());
        System.out.println(tr2011);
        
        // Query 2: What are all the unique cities where the traders work?
        List<String> cities = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        
        List<Trader> traders = 
            transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);
        
        
        // Query 4: Return a string of all traders’ names sorted alphabetically.
        
        String traderStr = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);*/
        
        // Query 5: Are there any trader based in Milan?

        boolean milanBased =
            transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                                            .getCity()
                                                            .equals("Milan")
                        );
        boolean milanBased1 =
                transactions.stream()
                        .allMatch(transaction -> transaction.getTrader()
                                        .getCity()
                                        .equals("Milan")
                                 );
        boolean milanBased2 =
                transactions.stream()
                        .noneMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);
        System.out.println(milanBased1);
        System.out.println(milanBased2);
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(trader -> trader.getCity().equals("Milan"))
                    .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);

        //求最小交易额度
        int value = transactions.stream().min(comparing(Transaction::getValue)).get().getValue();
        System.out.println("=====> : "+value);


        // Query 7: What's the highest value in all the transactions?
        /*int highestValue =
            transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);

        transactions.stream()
                .filter(d -> d.getYear() == 2012)
                .map(Transaction::getTrader)
                .findAny()
                .ifPresent(traderStr1 -> System.out.println("==? " + traderStr1.getName()));

        //找到这个列表的第一个数
        Optional<Transaction> first = Optional.of(transactions.stream()
                .findFirst()
                .orElse(new Transaction(new Trader("ddd", "ddd1"), 2023, 4000)));
        System.out.println( "transaction : "+first.get().getTrader().getName());
        //找到这个列表整除400的第一个数
        Optional<Integer> any = transactions.stream()
                .map(Transaction::getYear)
                .filter(x -> x==2012)
                .findAny();
        System.out.println("transaction  x % 400  : "+any.get().intValue());

        //统计数量
        Integer reduce = transactions.stream().map(x -> 1).reduce(0, (a, b) -> a + b);
        System.out.println("统计数"+reduce);*/
    }
}