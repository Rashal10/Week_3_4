import java.util.ArrayList;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSorting {

    // ✅ Bubble Sort (by fee)
    static void bubbleSort(ArrayList<Transaction> list) {
        int n = list.size();
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("\nBubble Sort Result (by fee):");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // ✅ Insertion Sort (fee + timestamp)
    static void insertionSort(ArrayList<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result (fee + timestamp):");
        printList(list);
    }

    // ✅ Outlier Detection
    static void findOutliers(ArrayList<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (>50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // Utility print
    static void printList(ArrayList<Transaction> list) {
        for (Transaction t : list) {
            System.out.print(t + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        printList(transactions);

        // Clone list for different sorts
        ArrayList<Transaction> bubbleList = new ArrayList<>(transactions);
        ArrayList<Transaction> insertionList = new ArrayList<>(transactions);

        // ✅ Bubble Sort
        bubbleSort(bubbleList);

        // ✅ Insertion Sort
        insertionSort(insertionList);

        // ✅ Outliers
        findOutliers(transactions);

        System.out.println("\nProcessing completed...");
    }
}