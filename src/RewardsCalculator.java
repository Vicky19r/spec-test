import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
 *
 * A customer receives 2 points for every dollar spent over $100 in each transaction,
 * plus 1 point for every dollar spent over $50 in each transaction
 *
 * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
 *
 * $160 = 2x60 + 1X50 = 170
 * $357.60 = 50x1 + 257x2 = 564
 * $876.34 = 50x1 + 776x2 = 1602
 *
 * Given a record of every transaction during a three-month period, calculate the reward points earned for each
 * customer per month and total.
 *
 * ·         Use (Whatever they feel they’re strongest in, insert that here: Spring, Spring Boot, Java 8, React, JavaScript, etc.)
 *
 * ·         Make up a data set to best demonstrate your solution
 *
 * ·         Check solution into GitHub
 *
 *
 */
public class RewardsCalculator {

    public static void main(String[] args){

        List<Transaction> testData = getTestData1();


        // I can also do the whole calculation in one pass filling the customer maps and month maps while traversing
        // and then calculate totals for each customer, may be i can explain this in the onsite interview
        // requires more focused coding in one pass.
        //
        // but for now as i do not have some good time to focus i did it by grouping the transactions by customers first
        // and then grouping the transactions mapped to each customer by month
        // and then passing through transactions mapped to each month to each customer and calculating the rewards
        // and summing the totals for each month
        Map<Long, List<Transaction>> groupByCustomerMap = new LinkedHashMap<>();

        testData
                .stream()
                .filter(transaction -> transaction != null)
                .forEach(transaction -> {

                    if(groupByCustomerMap.containsKey(transaction.getCustomerId())){

                        groupByCustomerMap.get(transaction.getCustomerId()).add(transaction);

                    }else{

                        ArrayList<Transaction> transactions = new ArrayList<>();
                        transactions.add(transaction);
                        groupByCustomerMap.put(transaction.getCustomerId(), transactions);
                    }
                });

        Map<Long, Map<String, Long>> result = new LinkedHashMap<>();

        groupByCustomerMap.entrySet().stream().forEach(longListEntry -> {

            Map<String, Long> rewards = calculateRewards(longListEntry.getValue());
            result.put(longListEntry.getKey(), rewards);
        });


        result.entrySet().forEach(longMapEntry -> {

            System.out.println("Customer: " + longMapEntry.getKey());

            longMapEntry.getValue().entrySet().stream().forEach(stringLongEntry -> {

                if(!stringLongEntry.getKey().equalsIgnoreCase("total")) {
                    System.out.println("\t" + stringLongEntry.getKey() + ": " + stringLongEntry.getValue());
                }
            });

            System.out.println("\t" + "Total: " + longMapEntry.getValue().get("Total"));
        });

    }

    private static List<Transaction> getTestData1() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction(1, 1, 12000,
                LocalDateTime.of(2019, Month.JUNE, 29, 19, 30, 40));

        Transaction transaction2 = new Transaction(1, 1, 67890,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction10 = new Transaction(1, 1, 92345,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction3 = new Transaction(1, 1, 35760,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction6 = new Transaction(1, 1, 15760,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction7 = new Transaction(1, 1, 43760,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction4 = new Transaction(1, 1, 87634,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction8 = new Transaction(1, 1, 35689,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction9 = new Transaction(1, 1, 48723,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));


        Transaction transaction11 = new Transaction(1, 2, 52600,
                LocalDateTime.of(2019, Month.JUNE, 29, 19, 30, 40));

        Transaction transaction12 = new Transaction(1, 2, 34690,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction13 = new Transaction(1, 2, 87445,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction14 = new Transaction(1, 2, 102060,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction15 = new Transaction(1, 2, 789960,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction16 = new Transaction(1, 2, 85260,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction17 = new Transaction(1, 2, 65334,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction18 = new Transaction(1, 2, 15289,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction19 = new Transaction(1, 2, 65923,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));



        Transaction transaction21 = new Transaction(1, 3, 36900,
                LocalDateTime.of(2019, Month.JUNE, 29, 19, 30, 40));

        Transaction transaction22 = new Transaction(1, 3, 25690,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction23 = new Transaction(1, 3, 17845,
                LocalDateTime.of(2019, Month.JUNE, 28, 19, 30, 40));

        Transaction transaction24 = new Transaction(1, 3, 65460,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction25 = new Transaction(1, 3, 85660,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction26 = new Transaction(1, 3, 56460,
                LocalDateTime.of(2019, Month.JULY, 29, 19, 30, 40));

        Transaction transaction27 = new Transaction(1, 3, 89734,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction28 = new Transaction(1, 3, 35689,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));

        Transaction transaction29 = new Transaction(1, 3, 10023,
                LocalDateTime.of(2019, Month.AUGUST, 29, 19, 30, 40));




        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction6);
        transactions.add(transaction7);
        transactions.add(transaction8);
        transactions.add(transaction9);
        transactions.add(transaction10);


        transactions.add(transaction11);
        transactions.add(transaction12);
        transactions.add(transaction13);
        transactions.add(transaction14);
        transactions.add(transaction15);
        transactions.add(transaction16);
        transactions.add(transaction17);
        transactions.add(transaction18);

        transactions.add(transaction21);
        transactions.add(transaction22);
        transactions.add(transaction23);
        transactions.add(transaction24);
        transactions.add(transaction25);
        transactions.add(transaction26);
        transactions.add(transaction27);
        transactions.add(transaction28);

        return transactions;
    }

    private static Map<String, Long> calculateRewards(List<Transaction> transactionList){

        Collections.sort(transactionList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {

                return o1.getTransactionDateTime().compareTo(o2.getTransactionDateTime());
            }
        });

        Map<String, Long> result = new LinkedHashMap<>();

        Map<String, List<Transaction>> groupByMonthMap = new LinkedHashMap<>();

        transactionList
                    .stream()
                    .filter(transaction -> transaction != null
                            && transaction.getTransactionMonth() != null)
                    .forEach(transaction -> {

                        if(groupByMonthMap.containsKey(transaction.getTransactionMonth())){

                            groupByMonthMap.get(transaction.getTransactionMonth()).add(transaction);

                        }else{

                            ArrayList<Transaction> transactions = new ArrayList<>();
                            transactions.add(transaction);
                            groupByMonthMap.put(transaction.getTransactionMonth(), transactions);
                        }
                    });

        int[] i = new int[1];
        groupByMonthMap.entrySet().stream().forEach((stringListEntry -> {

            if(i[0] < 3) {
                long[] monthRewardsSum = new long[1];

                stringListEntry.getValue().stream().forEach(transaction -> {

                    long rewards = 0;
                    if (transaction.getPurchaseAmount() > 5000 && transaction.getPurchaseAmount() < 10000) {

                        rewards = ((10000 - transaction.getPurchaseAmount())/100) * 1;

                    } else if (transaction.getPurchaseAmount() > 10000) {

                        rewards = 50 + (((transaction.getPurchaseAmount() - 10000)/100) * 2);
                    }

                    monthRewardsSum[0] = monthRewardsSum[0] + rewards;
                });

                result.put(stringListEntry.getKey(), monthRewardsSum[0]);
            }
        }));

        long[] totalRewards =  new long[1];

        result.entrySet().stream().forEach(stringLongEntry -> {

            totalRewards[0] = totalRewards[0] + stringLongEntry.getValue();
        });

        result.put("Total", totalRewards[0]);

        return result;
    }
}
