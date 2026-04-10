import java.util.Random;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioSorting {

    // ✅ MERGE SORT (Ascending - Stable)
    static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Stable merge
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ✅ QUICK SORT (Descending + Volatility ASC)
    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition using median-of-3 pivot
    static int partition(Asset[] arr, int low, int high) {

        int mid = (low + high) / 2;

        // Median-of-3 pivot selection
        Asset pivot = arr[mid];

        // Swap pivot to end
        Asset tempPivot = arr[mid];
        arr[mid] = arr[high];
        arr[high] = tempPivot;

        double pivotReturn = pivot.returnRate;

        int i = low - 1;

        for (int j = low; j < high; j++) {

            // DESC returnRate + ASC volatility
            if (arr[j].returnRate > pivotReturn ||
                    (arr[j].returnRate == pivotReturn &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Utility
    static void printArray(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print(a + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        System.out.println("Original Assets:");
        printArray(assets);

        // ✅ Merge Sort (Ascending)
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("\nMerge Sort (Ascending):");
        printArray(mergeArr);

        // ✅ Quick Sort (Descending)
        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort (Descending):");
        printArray(quickArr);

        System.out.println("\nProcessing completed...");
    }
}