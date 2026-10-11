public class Main {
    public static void main(String[] args) {
        // ---- Case 1: normal insert + print ----
        System.out.println("=== Test 1: Insert 20 elements ===");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            linkedList.insertNode(i);
        }
        printList(linkedList);
        System.out.println("Size: " + linkedList.size());

        // ---- Case 2: removing from both ends, still non-empty ----
        System.out.println("\n=== Test 2: Remove 4 from head, 3 from tail ===");
        linkedList.removeHead();
        linkedList.removeHead();
        linkedList.removeHead();
        linkedList.removeHead();
        linkedList.removeTail();
        linkedList.removeTail();
        linkedList.removeTail();
        printList(linkedList);
        System.out.println("Size: " + linkedList.size());

        // ---- Case 3: empty list ----
        System.out.println("\n=== Test 3: Empty list ===");
        LinkedList<Integer> emptyList = new LinkedList<>();
        System.out.println("Size: " + emptyList.size());
        System.out.println("Head is null: " + (emptyList.getHead() == null));
        printList(emptyList); // should print nothing

        // ---- Case 4: single element ----
        System.out.println("\n=== Test 4: Single element ===");
        LinkedList<Integer> singleList = new LinkedList<>();
        singleList.insertNode(3);
        printList(singleList);
        System.out.println("Size before removeTail: " + singleList.size());
        singleList.removeTail();
        System.out.println("Size after removeTail: " + singleList.size());
        System.out.println("Head is null after removing only element: " + (singleList.getHead() == null));

        // ---- Case 5: single element, removeHead instead ----
        System.out.println("\n=== Test 5: Single element, removeHead ===");
        LinkedList<Integer> singleList2 = new LinkedList<>();
        singleList2.insertNode(7);
        singleList2.removeHead();
        System.out.println("Size after removeHead: " + singleList2.size());
        System.out.println("Head is null: " + (singleList2.getHead() == null));

        // ---- Case 6: removing from an already-empty list ----
        System.out.println("\n=== Test 6: removeHead/removeTail on empty list ===");
        LinkedList<Integer> alreadyEmpty = new LinkedList<>();
        try {
            alreadyEmpty.removeHead();
            System.out.println("removeHead on empty list did not throw. Size: " + alreadyEmpty.size());
        } catch (Exception e) {
            System.out.println("removeHead on empty list threw: " + e.getClass().getSimpleName());
        }
        try {
            alreadyEmpty.removeTail();
            System.out.println("removeTail on empty list did not throw. Size: " + alreadyEmpty.size());
        } catch (Exception e) {
            System.out.println("removeTail on empty list threw: " + e.getClass().getSimpleName());
        }

        // ---- Case 7: removing everything down to empty ----
        System.out.println("\n=== Test 7: Remove all elements one by one ===");
        LinkedList<Integer> drainList = new LinkedList<>();
        for (int i = 0; i < 5; i++) drainList.insertNode(i);
        while (drainList.size() > 0) {
            drainList.removeHead();
        }
        System.out.println("Size after draining: " + drainList.size());
        System.out.println("Head is null: " + (drainList.getHead() == null));
    }

private static void printList(LinkedList<Integer> list) {
    Node<Integer> current = list.getHead();
    while (current != null) {
        System.out.println("Node value: " + current.getData());
        current = current.getNextNode();
    }
}
}
