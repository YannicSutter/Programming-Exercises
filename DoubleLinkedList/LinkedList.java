public class LinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    

    public LinkedList()
    {

    }

    public Node<T> insertNode(T data)
    {
        Node<T> node = new Node<>(data);

        if (head == null)
        {
            head = node;
            tail = node;
        }
        else
        {
            tail.setNextNode(node);
            node.setPreviousNode(tail);
            tail = node;
        }
        size++;
        return tail;
    }

    public void removeHead()
    {
        if (size == 1 || size == 0)
        {
            head = null;
            tail = null;
            size = 0;
        }
        else
        {
            head = head.getNextNode();
            size--;
        }
    }

    public void removeTail()
    {
        if (size == 1 || size == 0)
        {
            head = null;
            tail = null;
            size = 0;
        }
        else
        {
            tail.getPreviousNode().setNextNode(null);
            tail = tail.getPreviousNode();
            size--;
        }
    }

    // GET / SET
    public Node<T> getHead()
    {
        return head;
    }

    public int size()
    {
        return size;
    }
}