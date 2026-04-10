class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class ClientRiskRanking {

    // ✅ Bubble Sort (Ascending)
    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    System.out.println("Swapped: " + arr[j].name + " <-> " + arr[j + 1].name);
                }
            }
        }

        System.out.println("\nBubble Sort (Ascending):");
        printArray(arr);
        System.out.println("Total Swaps: " + swaps);
    }

    // ✅ Insertion Sort (Descending + Balance)
    static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending):");
        printArray(arr);
    }

    // ✅ Top 10 Highest Risk
    static void printTopClients(Client[] arr) {
        System.out.println("\nTop High-Risk Clients:");

        for (int i = 0; i < 10 && i < arr.length; i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    // Utility method
    static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.print(c + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000)
        };

        System.out.println("Original Data:");
        printArray(clients);

        // Copy arrays
        Client[] bubbleArr = clients.clone();
        Client[] insertionArr = clients.clone();

        // ✅ Bubble Sort
        bubbleSort(bubbleArr);

        // ✅ Insertion Sort
        insertionSort(insertionArr);

        // ✅ Top Clients (after DESC sort)
        printTopClients(insertionArr);

        System.out.println("\nProcessing completed...");
    }
}