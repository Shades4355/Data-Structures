// File name:   OldNode.java
// Written by:  Shades Meyers
// Description: A OldNode with Pairs as its element
// Challenges:  None
// Time Spent:  55 minutes
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

public class OldNode<E extends Comparable<E>, T> implements Comparable<Pairs<E, T>> {
    // Variables
    private Pairs<E, T> element;
    private OldNode<E, T> parent, leftChild, rightChild;

    // Constructors
    OldNode(Pairs<E, T> element, OldNode<E, T> parent, OldNode<E, T> leftChild, OldNode<E, T> rightChild) {
        this.element = element;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    OldNode(Pairs<E, T> element, OldNode<E, T> parent) {
        this.element = element;
        this.parent = parent;
        this.leftChild = new OldNode<E, T>(null, this, null, null);
        this.rightChild = new OldNode<E, T>(null, this, null, null);
    }

    // Methods
    // Accessors & Mutators
        // Parent
    public OldNode<E, T> getParent() {
        return this.parent;
    }
    public void setParent(OldNode<E, T> newParent) {
        this.parent = newParent;
    }
        // Children
    public OldNode<E, T> getLeftChild() {
        return this.leftChild;
    }
    public void setLeftChild(OldNode<E, T> newChild) {
        this.leftChild = newChild;
    }
    public OldNode<E, T> getRightChild() {
        return this.rightChild;
    }
    public void setRightChild(OldNode<E, T> newChild) {
        this.rightChild = newChild;
    }
    public ArrayList<OldNode<E, T>> getChildren() {
        ArrayList<OldNode<E, T>> childList = new ArrayList<OldNode<E, T>>();
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
    public int compareTo(OldNode<E, T> OldNode2) {
        return this.compareTo(OldNode2.getElement());
    }
    public int compareTo(E key) {
        return this.getElement().compareTo(key);
    }


    // To String
    @Override
    public String toString() {
        return new String(new StringBuilder().append(this.element));
    }

} // end OldNode