package BinarySearchTree;

public class Tree {
    Node root;

    public Tree() {
        this.root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            recursiveInsert(root, value);
        }
    }

    private void recursiveInsert(Node currentNode, int value) {
        if (value == currentNode.value) {
            return;
        } else if (value < currentNode.value) {
            if (currentNode.leftChild != null) {
                recursiveInsert(currentNode.leftChild, value);
            } else {
                currentNode.leftChild = new Node(value);
            }
        } else {
            if (currentNode.rightChild != null) {
                recursiveInsert(currentNode.rightChild, value);
            } else {
                currentNode.rightChild = new Node(value);
            }
        }
    }

    public void delete(int value) {
        if (!search(value)) {
            return;
        } else {
            root = recursiveDelete(root, value);
        }
    }

    private Node recursiveDelete(Node currentNode, int value) {
        if (value < currentNode.value) {
            currentNode.leftChild = recursiveDelete(currentNode.leftChild, value);
        } else if (value > currentNode.value) {
            currentNode.rightChild = recursiveDelete(currentNode.rightChild, value);
        } else {
            if (currentNode.leftChild == null && currentNode.rightChild == null) {
                return null;
            } else if (currentNode.leftChild == null) {
                return currentNode.rightChild;
            } else if (currentNode.rightChild == null) {
                return currentNode.leftChild;
            } else {
                int successorValue = smallestValueInSubtree(currentNode.rightChild);
                currentNode.value = successorValue;
                currentNode.rightChild = recursiveDelete(currentNode.rightChild, successorValue);
            }
        }
        return currentNode;
    }

    private int smallestValueInSubtree(Node node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node.value;
    }

    public boolean search(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (value == currentNode.value) {
                return true;
            }
            if (value < currentNode.value) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
        return false;
    }
}