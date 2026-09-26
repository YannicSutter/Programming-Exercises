public class Node<T>
{
    private T data;
    private Node<T> nextNode;
    private Node<T> previousNode;

    public Node(T data)
    {
        this.data = data;
    }

    // GETTERS / SETTERS
    public T getData()
    {
        return data;
    }

    public Node<T> getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(Node<T> node)
    {
        nextNode = node;
    }

    public Node<T> getPreviousNode()
    {
        return previousNode;
    }

    public void setPreviousNode(Node<T> node)
    {
        previousNode = node;
    }
}