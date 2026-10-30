package BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Main {

    static int failures = 0;

    public static void main(String[] args) {
        // ---- Empty tree ----
        Tree empty = new Tree();
        check("Empty: search", !empty.search(5));
        empty.delete(5); // must not crash
        check("Empty: delete keeps it empty", empty.root == null);

        // ---- Insert and search ----
        Tree t = build(8, 3, 10, 1, 6, 14, 4);
        check("Insert: sorted in-order", inOrder(t).equals(Arrays.asList(1, 3, 4, 6, 8, 10, 14)));
        check("Search: existing", t.search(6));
        check("Search: smaller than all", !t.search(0));
        check("Search: larger than all", !t.search(99));
        check("Search: missing in the middle", !t.search(7));

        // ---- Delete: leaf ----
        t = build(8, 3, 10, 1, 6, 14, 4);
        t.delete(1);
        check("Delete leaf", inOrder(t).equals(Arrays.asList(3, 4, 6, 8, 10, 14)));

        // ---- Delete: one child ----
        t = build(8, 3, 10, 1);
        t.delete(3);
        check("Delete one child (values)", inOrder(t).equals(Arrays.asList(1, 8, 10)));
        check("Delete one child (1 took its place)", t.root.leftChild.value == 1);

        // ---- Delete: two children ----
        t = build(8, 3, 10, 1, 6, 14, 4);
        t.delete(3);
        check("Delete two children (values)", inOrder(t).equals(Arrays.asList(1, 4, 6, 8, 10, 14)));
        check("Delete two children (successor 4 moved up)", t.root.leftChild.value == 4);

        t = build(5, 3, 8, 9);
        t.delete(5);
        check("Delete root, successor is right child", inOrder(t).equals(Arrays.asList(3, 8, 9)));
        check("  root is now 8", t.root.value == 8);

        t = build(10, 5, 20, 15, 30, 17);
        t.delete(10);
        check("Delete root, successor has a right child", inOrder(t).equals(Arrays.asList(5, 15, 17, 20, 30)));
        check("  root is now 15", t.root.value == 15);

        // ---- Delete: root cases ----
        t = build(5);
        t.delete(5);
        check("Delete only node", t.root == null && !t.search(5));

        t = build(5, 8);
        t.delete(5);
        check("Delete root with only right child", t.root.value == 8 && inOrder(t).equals(Arrays.asList(8)));

        t = build(5, 2);
        t.delete(5);
        check("Delete root with only left child", t.root.value == 2 && inOrder(t).equals(Arrays.asList(2)));

        // ---- Delete: missing value ----
        t = build(8, 3, 10);
        t.delete(7);
        check("Delete missing value changes nothing", inOrder(t).equals(Arrays.asList(3, 8, 10)));

        // ---- Duplicates ----
        t = build(5, 3, 5, 3, 8, 8);
        check("Duplicates ignored", inOrder(t).equals(Arrays.asList(3, 5, 8)));

        // ---- Delete everything, one by one ----
        t = build(8, 3, 10, 1, 6, 14, 4);
        for (int v : new int[] { 8, 1, 14, 3, 10, 4, 6 }) {
            t.delete(v);
        }
        check("Delete all values", t.root == null);

        // ---- Random test against Java's TreeSet ----
        Random random = new Random(42);
        for (int round = 0; round < 500; round++) {
            Tree tree = new Tree();
            TreeSet<Integer> reference = new TreeSet<>();
            boolean ok = true;
            for (int step = 0; step < 100 && ok; step++) {
                int value = random.nextInt(30);
                if (random.nextBoolean()) {
                    tree.insert(value);
                    reference.add(value);
                } else {
                    tree.delete(value);
                    reference.remove(value);
                }
                ok = inOrder(tree).equals(new ArrayList<>(reference))
                        && tree.search(value) == reference.contains(value);
            }
            if (!ok) {
                check("Random round " + round, false);
                break;
            }
        }
        check("Random test against TreeSet", failures == 0);

        System.out.println(failures == 0 ? "\nAll tests passed." : "\n" + failures + " test(s) failed.");
    }

    private static Tree build(int... values) {
        Tree tree = new Tree();
        for (int value : values) {
            tree.insert(value);
        }
        return tree;
    }

    private static List<Integer> inOrder(Tree tree) {
        List<Integer> result = new ArrayList<>();
        inOrder(tree.root, result);
        return result;
    }

    private static void inOrder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrder(node.leftChild, result);
        result.add(node.value);
        inOrder(node.rightChild, result);
    }

    private static void check(String name, boolean condition) {
        if (!condition) {
            failures++;
        }
        System.out.println((condition ? "OK    " : "FAIL  ") + name);
    }
}