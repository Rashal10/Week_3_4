import java.util.Arrays;

public class AccountSearch {

    // Linear Search (First & Last)
    static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("\nLinear Search:");
        if (first != -1) {
            System.out.println("First Occurrence: " + first);
            System.out.println("Last Occurrence: " + last);
        } else {
            System.out.println("Not Found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // Binary Search (Find one occurrence)
    static int binarySearch(String[] arr, String target, int[] comparisons) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            comparisons[0]++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // Count occurrences using expansion
    static int countOccurrences(String[] arr, int index, String target) {
        if (index == -1) return 0;

        int count = 1;

        // left side
        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            count++;
            i--;
        }

        // right side
        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        System.out.println(Arrays.toString(logs));

        // Linear Search (unsorted)
        linearSearch(logs, "accB");

        // Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // Binary Search
        int[] comparisons = {0};
        int index = binarySearch(logs, "accB", comparisons);

        int count = countOccurrences(logs, index, "accB");

        System.out.println("\nBinary Search:");
        if (index != -1) {
            System.out.println("Found at index: " + index);
            System.out.println("Total occurrences: " + count);
        } else {
            System.out.println("Not Found");
        }

        System.out.println("Comparisons: " + comparisons[0]);

        System.out.println("\nProcessing completed...");
    }
}