public class Main {
    public static void main(String[] args) {
        // Create LinkedList with values
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        for (int i = 0; i < 20; i++)
        {
            linkedList.insertNode(i);
        }

        // print all data values
        Node<Integer> current = linkedList.getHead();
        while (current != null)
        {
            System.out.println("Node value; " + current.getData());
            current = current.getNextNode();
        }
        System.out.println("Size: " + linkedList.size());

        linkedList.removeHead();
        linkedList.removeHead();
        linkedList.removeHead();
        linkedList.removeHead();

        linkedList.removeTail();
        linkedList.removeTail();
        linkedList.removeTail();

        // print all data values
        current = linkedList.getHead();
        System.out.println("Size: " + linkedList.size());
        while (current != null)
        {
            System.out.println("Node value; " + current.getData());
            current = current.getNextNode();
        }
        System.out.println("Size: " + linkedList.size());


        LinkedList<Integer> linkedList2 = new LinkedList<>();
        System.out.println("Size: " + linkedList2.size());
        linkedList2.insertNode(3);
        
        current = linkedList2.getHead();
        while (current != null)
        {
            System.out.println("Node value; " + current.getData());
            current = current.getNextNode();
        }
        linkedList2.removeTail();
        System.out.println("Size: " + linkedList2.size());
    }
}
