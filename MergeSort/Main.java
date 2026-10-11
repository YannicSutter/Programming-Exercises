import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public static void main(String[] args) {
    // Edge cases
    runTest("Empty list", new ArrayList<Integer>());
    runTest("1 element", Arrays.asList(5));
    runTest("2 elements, sorted", Arrays.asList(1, 2));
    runTest("2 elements, reversed", Arrays.asList(2, 1));

    // Odd sizes (these test the lone-sublist case)
    runTest("3 elements", Arrays.asList(3, 1, 2));
    runTest("5 elements", Arrays.asList(5, 4, 3, 2, 1));
    runTest("7 elements", Arrays.asList(7, 3, 5, 1, 6, 2, 4));

    // Even sizes
    runTest("4 elements", Arrays.asList(4, 3, 2, 1));
    runTest("8 elements", Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1));

    // Special content
    runTest("Already sorted", Arrays.asList(1, 2, 3, 4, 5, 6));
    runTest("Reversed", Arrays.asList(6, 5, 4, 3, 2, 1));
    runTest("All equal", Arrays.asList(2, 2, 2, 2, 2));
    runTest("Duplicates", Arrays.asList(3, 1, 3, 2, 1, 2));
    runTest("Negative numbers", Arrays.asList(0, -5, 3, -1, 10, -20));

    // Strings (checks that the generic <T extends Comparable<T>> works)
    runTest("Strings", Arrays.asList("pear", "apple", "fig", "banana", "cherry"));

    // Random lists of many different sizes
    Random random = new Random(42);
    for (int size = 0; size <= 50; size++) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randomList.add(random.nextInt(100));
        }
        runTest("Random, size " + size, randomList);
    }
}

private static <T extends Comparable<T>> void runTest(String name, List<T> input) {
    List<T> expected = new ArrayList<>(input);
    Collections.sort(expected);

    List<T> actual;
    try {
        actual = MergeSortUtil.mergeSort(new ArrayList<>(input));
    } catch (Exception e) {
        System.out.println("FAIL  " + name + " -> threw " + e);
        return;
    }

    // Your code returns null for an empty list, so treat that as a special case
    if (input.isEmpty()) {
        System.out.println((actual == null ? "OK    " : "CHECK ") + name
                + " (returned " + actual + ")");
        return;
    }

    if (expected.equals(actual)) {
        System.out.println("OK    " + name);
    } else {
        System.out.println("FAIL  " + name);
        System.out.println("      input:    " + input);
        System.out.println("      expected: " + expected);
        System.out.println("      actual:   " + actual);
    }
}
