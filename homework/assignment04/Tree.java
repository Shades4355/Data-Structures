// File name:   Map.java
// Written by:  Shades Meyers
// Description: A list based Map
// Challenges:  Removal logic makes my head spin
// Time Spent:  4 h 10 minutes + (18:15 - )
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-24 SM      File created
// 2024-July-25 SM      Con't work on removal logic


import java.util.ArrayList;

public class Tree<E, T> {
    // Variables
    private Node<E, T> root;
    private int size;

    // Constructors
    Tree(Pairs<E, T> element) {
        this.root = new Node<E, T>(element, null);
        this.size = 1;
    }
    Tree() {
        this.root = null;
        this.size = 0;
    }

    // Methods
    // Insertions
    private void addBetween(Node<E, T> node, Node<E, T> parent, Node<E, T> child) {
        Pairs<E, T> nodeElement = node.getElement();
        Pairs<E, T> parentElement = parent.getElement();
        Pairs<E, T> childElement = child.getElement();
        node.setParent(parent);
        child.setParent(node);
        
        // if nodeElement is greater than childElement, child becomes a leftChild of node
        if (nodeElement.compareTo(childElement) == 1) {
            node.setLeftChild(child);
        } else {
            node.setRightChild(child);
        }

        // if parentElement is greater than nodeElement, node becomes a leftChild of parent
        if (parentElement.compareTo(nodeElement) == 1) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }
    }
    public boolean add(Pairs<E, T> element) {
        Node<E, T> newNode = new Node<>(element, null);
        if (this.root == null ) {
            this.root = newNode;
            this.size++;
            return true;
        } else {
            ArrayList<Node<E, T>> nodesAround = findBetween(newNode, this.getRoot());

            if (nodesAround != null) {
                this.addBetween(newNode, nodesAround.get(0), nodesAround.get(1))
                this.size ++;
                return true;
            }
        }
        return false;
    }

    // Find Parent and Child nodes for a given new node
    private ArrayList<Node<E, T>> findBetween(Node<E, T> node, Node<E, T> curNode) { // O(log n)
        ArrayList<Node<E, T>> retList = new ArrayList<Node<E, T>>();
        Pairs<E, T> nodeElement = node.getElement();
        Pairs<E, T> curElement = curNode.getElement();
        Node<E, T> leftChild = curNode.getLeftChild();
        Node<E, T> rightChild = curNode.getRightChild();

        // recursive call here
        if (nodeElement.compareTo(curElement) == 0) {
            // if curNode == node, change curNode's value to node's value
            this.setNode(curNode, node);
            return null;
        } else if (nodeElement.compareTo(curElement) == -1) {
            // If node is less than curNode...
            if (nodeElement.compareTo(leftChild.getElement()) == 1) {
                // And node is greater than leftChild...
                retList.add(curNode);
                retList.add(leftChild);

                return retList;
            } else { // If node is less than leftChild, advance down the left path
                return findBetween(node, curNode.getLeftChild());
            }
        } else {
            // If node is greater than curNode
            if (nodeElement.compareTo(rightChild.getElement()) == -1) {
                // If node is less than rightChild
                retList.add(curNode);
                retList.add(rightChild);

                return retList;
            } else {
                return findBetween(node, curNode.getRightChild());
            }
        }
    } // end findBetween

    // Set Method
    public T setNode(Node<E, T> oldNode, Node<E, T> newNode) {
        T retVal = oldNode.getElement().getValue();

        oldNode.setValue(newNode.getValue());

        return retVal;
    }
    
    // Removal
    public Pairs<E, T> remove(int index) { // should run O(h)
        return this.remove(this.get(index));
    }
    public Pairs<E, T> remove(Node<E, T> node) {
        Pairs<E, T> nodeElement = node.getElement();
        Node<E, T> parent = node.getParent();
        Node<E, T> oldLeftChild = node.getLeftChild();
        Node<E, T> oldRightChild = node.getRightChild();

        if (nodeElement.compareTo(parent.getElement()) == 1) {
            // If node is a rightChild of parent...
            if (oldLeftChild.isLeaf() && oldRightChild.isLeaf()) {
                // If node has only leaves...
                parent.setRightChild(new Node<E, T>(null, parent));

            } else if (!oldLeftChild.isLeaf()) {
                // If node has a leftChild,
                // oldLeftChild takes the place of node...
                Node<E, T> curNode = node;
                while (!curNode.getLeftChild().isLeaf()) {
                    curNode = curNode.getLeftChild();
                }
                // move curNode to node's position
                // set curNode's parent to parent
                curNode.setParent(parent);
                if (!curNode.getRightChild().isLeaf()) {
                    if (curNode != oldLeftChild) {
                        // test for scenario where curNode is oldLeftNode
                        curNode.setLeftChild(oldLeftChild);
                        oldLeftChild.setParent(curNode);
                    } else {
                        curNode.setLeftChild(new Node<E, T>(null, curNode, null, null));
                    }
                    
                    curNode.setRightChild(oldRightChild);
                    oldRightChild.setParent(curNode);
                } else {
                    // find curNode's right child's new parent
                    Node<E, T> receiver = curNode.getParent().getRightChild();
                    while (!receiver.getLeftChild().isLeaf()) {
                        receiver = receiver.getLeftChild();
                    }
                    Node<E, T> moving = curNode.getRightChild();
                    receiver.setLeftChild(moving);
                    moving.setParent(receiver);
                }
            } else { // If node has no leftChild
                Node<E, T> receiver = parent.getRightChild();
                while (!receiver.getLeftChild().isLeaf()) {
                    receiver = receiver.getLeftChild();
                }
                oldRightChild.setParent(receiver);
                receiver.setLeftChild(oldRightChild);
            } 
        } else if (nodeElement.compareTo(parent.getElement()) == -1) { // mirror of when node is a rightChild of parent
            // If node is a leftChild of parent...
            if (oldLeftChild.isLeaf() && oldRightChild.isLeaf()) {
                // If node has only leaves...
                parent.setLeftChild(new Node<E, T>(null, parent));
            } else if (!oldRightChild.isLeaf()) {
                // If node has a rightChild...
                Node<E, T> curNode = node;
                while(!curNode.getRightChild().isLeaf()) {
                    curNode = curNode.getRightChild();
                }
                curNode.setParent(parent);
                if (!curNode.getLeftChild().isLeaf()) {
                    if (curNode != oldRightChild) {
                        curNode.setRightChild(oldRightChild);
                        oldRightChild.setParent(curNode);
                    } else {
                        curNode.setRightChild(new Node<E, T>(null, curNode, null, null));
                    }

                    curNode.setLeftChild(oldLeftChild);
                    oldLeftChild.setParent(curNode);
                } else {
                    Node<E, T> receiver = curNode.getParent().getLeftChild();
                    while(!receiver.getRightChild().isLeaf()) {
                        receiver = receiver.getRightChild();
                    }
                    Node<E, T> moving = curNode.getLeftChild();
                    receiver.setRightChild(moving);
                    moving.setParent(receiver);
                }
            } else {
                Node<E, T> receiver = parent.getLeftChild();
                while (!receiver.getRightChild().isLeaf()) {
                    receiver = receiver.getRightChild();
                }
                oldLeftChild.setParent(receiver);
                receiver.setRightChild(oldLeftChild);
            }
        } else { // node == root
            // treat as rightChild deletion, but moved node becomes root
            // If node is a rightChild of parent...
            if (oldLeftChild.isLeaf() && oldRightChild.isLeaf()) {
                // If root has only leaves...
                this.root = null;
            } else if (!oldLeftChild.isLeaf()) {
                // If root has a leftChild...
                Node<E, T> curNode = node;
                while (!curNode.getLeftChild().isLeaf()) {
                    curNode = curNode.getLeftChild();
                }
                curNode.setParent(null);
                if (!curNode.getRightChild().isLeaf()) {
                    if (curNode != oldLeftChild) {
                        curNode.setLeftChild(oldLeftChild);
                        oldLeftChild.setParent(curNode);
                    } else {
                        curNode.setLeftChild(new Node<E, T>(null, curNode, null, null));
                    }
                    curNode.setRightChild(oldRightChild);
                    oldRightChild.setParent(curNode);
                } else {
                    Node<E, T> receiver = curNode.getParent().getRightChild();
                    while (!receiver.getLeftChild().isLeaf()) {
                        receiver = receiver.getLeftChild();
                    }
                    Node<E, T> moving = curNode.getRightChild();
                    receiver. setLeftChild(moving);
                    moving.setParent(receiver);
                }
            } else {
                // If root has no leftChild
                this.root = oldRightChild;
                oldRightChild.setParent(null);
            }
        }

        // Garbage collection help
        node.setParent(null);
        node.setElement(null);
        node.setLeftChild(null);
        node.setRightChild(null);

        this.size--;

        return nodeElement;
    }

    // Query Methods
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }
    private Node<E, T> getRoot() { return this.root; }
    private Node<E, T> get(int index) {
        ArrayList<Node<E, T>> flatTree = iterable(root);

        return flatTree.get(index);
    }
    public int indexOf(Node<E, T> node) {
        ArrayList<Node<E, T>> flatTree = iterable(root);

        return flatTree.indexOf(node);
    }
    
    // Iteration
    private ArrayList<Pairs<E, T>> iterator() {
        ArrayList<Pairs<E, T>> retArr = new ArrayList<Pairs<E, T>>();
        for (Node<E, T> node : iterable(this.root)) {
            retArr.add(node.getElement());
        }
        return retArr;
    }
    private ArrayList<Node<E, T>> iterable(Node<E, T> node) { // in-order traversal
        Node<E, T> leftChild = node.getLeftChild();
        Node<E, T> rightChild = node.getRightChild();
        ArrayList<Node<E, T>> nodeList = new ArrayList<Node<E, T>>();

        if (!leftChild.isLeaf()) {
            for (Node<E, T> element : iterable(leftChild)) {
                nodeList.add(element);
            }
        }

        nodeList.add(node);

        if (!rightChild.isLeaf()) {
            for (Node<E, T> element : iterable(rightChild)) {
                nodeList.add(element);
            }
        }

        return nodeList;
    }

    // To String Method
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        ArrayList<Pairs<E, T>> list = iterator();

        string.append("{");
        for (Pairs<E, T> node : list) {
            string.append(node);
            if (list.indexOf(node) > list.size()) {
                string.append(", ");
            }
        }
        string.append("}");

        return new String(string);
    }
} // end Map