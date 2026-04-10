import java.util.Arrays;

public class RiskThresholdLookup {

    // Linear Search (unsorted)
    static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        System.out.println("\nLinear Search:");
        if (found) {
            System.out.println("Threshold found.");
        } else {
            System.out.println("Threshold NOT found.");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    // Binary Search (Floor & Ceiling + insertion point)
    static void binarySearch(int[] arr, int target) {

        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        int floor = -1;
        int ceil = -1;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                floor = ceil = arr[mid];
                break;
            }
            else if (arr[mid] < target) {
                floor = arr[mid];   // possible floor
                low = mid + 1;
            }
            else {
                ceil = arr[mid];    // possible ceiling
                high = mid - 1;
            }
        }

        // insertion point = low
        int insertionPoint = low;

        System.out.println("\nBinary Search:");
        System.out.println("Floor: " + (floor != -1 ? floor : "None"));
        System.out.println("Ceiling: " + (ceil != -1 ? ceil : "None"));
        System.out.println("Insertion Index: " + insertionPoint);
        System.out.println("Comparisons: " + comparisons);
    }

    public static void main(String[] args) {

        int[] risks = {50, 10, 100, 25};

        System.out.println("Original Risk Bands:");
        System.out.println(Arrays.toString(risks));

        // Linear Search (unsorted)
        linearSearch(risks, 30);

        // Sort for Binary Search
        Arrays.sort(risks);

        System.out.println("\nSorted Risk Bands:");
        System.out.println(Arrays.toString(risks));

        // Binary Search (floor + ceiling)
        binarySearch(risks, 30);

        System.out.println("\nProcessing completed...");
    }
}