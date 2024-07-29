// File name: Node.java
// Written by: Shades Meyers
// Description: A Node a public Pairs as its element
// Challenges:
// Time Spent: 55 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-24 SM      File created
// 2024-July-25 SM      Added "next" method
// 2024-July-29 SM      Added another compareTo method
//                      Removed next() method - it wasn't being used
//                      Removed hasNext() - it wasn't being used


import java.util.ArrayList;

public class Node<E extends Comparable<E>, T> implements Comparable<Pairs<E, T>> {
    // Variables
    private Pairs<E, T> element;
    private Node<E, T> parent, leftChild, rightChild;

    // Constructors
    Node(Pairs<E, T> element, Node<E, T> parent, Node<E, T> leftChild, Node<E, T> rightChild) {
        this.element = element;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    Node(Pairs<E, T> element, Node<E, T> parent) {
        this.element = element;
        this.parent = parent;
        this.leftChild = new Node<E, T>(null, this, null, null);
        this.rightChild = new Node<E, T>(null, this, null, null);
    }

    // Methods
    // Accessors & Mutators
        // Parent
    public Node<E, T> getParent() {
        return this.parent;
    }
    public void setParent(Node<E, T> newParent) {
        this.parent = newParent;
    }
        // Children
    public Node<E, T> getLeftChild() {
        return this.leftChild;
    }
    public void setLeftChild(Node<E, T> newChild) {
        this.leftChild = newChild;
    }
    public Node<E, T> getRightChild() {
        return this.rightChild;
    }
    public void setRightChild(Node<E, T> newChild) {
        this.rightChild = newChild;
    }
    public ArrayList<Node<E, T>> getChildren() {
        ArrayList<Node<E, T>> childList = new ArrayList<Node<E, T>>();
        childList.add(this.leftChild);
        childList.add(this.rightChild);

        return childList;
    }
        // Element
    public Pairs<E, T> getElement() {
        return this.element;
    }
    public Pairs<E, T> setElement(Pairs<E, T> element) {
        Pairs<E, T> oldElement = this.element;
        this.element = element;

        return oldElement;
    }

    // Query Methods
    public boolean isRoot() {
        return this.parent == null;
    }
    public boolean isLeaf() {
        return this.element == null;
    }
    public E getKey() { return this.getElement().getKey(); }
    public T getValue() { return this.getElement().getValue(); }
    public void setValue(T val) { this.getElement().setValue(val); }

    // Overrides
    // Comparisons
    @Override
    public int compareTo(Pairs<E, T> element) {
        return this.getElement().compareTo(element);
    }
    public int compareTo(Node<E, T> node2) {
        return this.compareTo(node2.getElement());
    }
    public int compareTo(E key) {
        return this.getElement().compareTo(key);
    }


    // To String
    @Override
    public String toString() {
        return new String(new StringBuilder().append(this.element));
    }

} // end Node