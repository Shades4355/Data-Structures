// File name:   DLLNode.java
// Written by:  Shades Meyers
// Description: A node for a DLL
// Challenges:  
// Time Spent:  3 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File copied from in-class work
//                      Modified to work with a Map


public class DLLNode<E extends Comparable<E>, T> {
    // variables
    private Pairs <E, T> element;
    private DLLNode<E, T> nextNode;
    private DLLNode<E, T> prevNode;

    // Constructor
    DLLNode() {
        this.element = null;
        this.nextNode = null;
        this.prevNode = null;
    }
    DLLNode(Pairs<E, T> element) {
        this.element = element;
        this.nextNode = null;
        this.prevNode = null;
    }
    DLLNode(Pairs<E, T> element, DLLNode<E, T> prevNode, DLLNode<E, T> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    // Accessors & Mutators
    public Pairs<E, T> getElement() { return this.element; }
    public void setElement(Pairs<E, T> element) { this.element = element; }

    public DLLNode<E, T> getNextNode() { return this.nextNode; }
    public void setNextNode(DLLNode<E, T> nextNode) { this.nextNode = nextNode; }

    public DLLNode<E, T> getPrevNode() { return this.prevNode; }
    public void setPrevNode(DLLNode<E, T> prevNode) {
        this.prevNode = prevNode;
    }

    // has next
    public boolean hasNext() { return this.getNextNode() != null; }

} // end class
