// File name:   Node.java
// Written by:  Shades Meyers
// Description: A node for a Map
// Challenges:  
// Time Spent:  7 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File copied from in-class work
//                      Modified to work with a Map
//                      Renamed from DLLNode to Node


public class Node<E extends Comparable<E>, T> implements Comparable<Pairs<E, T>> {
    // variables
    private Pairs <E, T> element;
    private Node<E, T> nextNode;
    private Node<E, T> prevNode;

    // Constructor
    Node() {
        this.element = null;
        this.nextNode = null;
        this.prevNode = null;
    }
    Node(Pairs<E, T> element) {
        this.element = element;
        this.nextNode = null;
        this.prevNode = null;
    }
    Node(Pairs<E, T> element, Node<E, T> prevNode, Node<E, T> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    // Accessors & Mutators
    // Element
    public Pairs<E, T> getElement() { return this.element; }
    public void setElement(Pairs<E, T> element) { this.element = element; }
    // Next Node
    public Node<E, T> getNextNode() { return this.nextNode; }
    public void setNextNode(Node<E, T> nextNode) { this.nextNode = nextNode; }
    // Previous Node
    public Node<E, T> getPrevNode() { return this.prevNode; }
    public void setPrevNode(Node<E, T> prevNode) { this.prevNode = prevNode; }
    // Keys
    public E getKey() { return this.getElement().getKey(); }
    public void setKey(E newKey) { this.getElement().setKey(newKey); }
    // Values
    public T getValue() { return this.getElement().getValue(); }
    public void setValue(T newVal) { this.getElement().setValue(newVal); }
    
    // Iterable
    public boolean hasNext() { return this.getNextNode() != null; }
    public Node<E, T> next() { return this.nextNode; }

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

    // To String Method
    @Override
    public String toString() {
        return new String(new StringBuilder().append(this.element));
    }
} // end class