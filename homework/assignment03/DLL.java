// File name:   DLL.java
// Written by:  Shades Meyers
// Description: A Doubly Linked List based on given DLLADT
// Challenges:  
// Time Spent:  5 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-23 SM      File created
//                      Moved Node from AList; made it protected
//                      Moved DLL Methods from AList


public class DLL<E> { // does not "implement" DLLADT, because then DLL can't be "extended"
                      // in AList due to 2 implementations of DLLADT
    // protected Node class
    protected static class Node<E> {
        // variables
        protected E element;
        protected Node<E> nextNode;
        protected Node<E> prevNode;

        // Constructor
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prevNode = prev;
            this.nextNode = next;
        }

        // Accessors & Mutators
        // Elements
        public E getElement() throws IllegalStateException {
            if (nextNode == null) {
                throw new IllegalStateException("Position no longer valid");
            }
            return this.element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        // Return the next node
        public Node<E> getNext() {
            return this.nextNode;
        }
        public void setNext(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
        // Return the previous node
        public Node<E> getPrev() {
            return this.prevNode;
        }
        public void setPrev(Node<E> prevNode) {
            this.prevNode = prevNode;
        }
        // Returns true if the node in question has a next node
        public boolean hasNext() {
            return this.getNext() != null;
        }
        // Implementation of a toString() method
        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            string.append(this.getElement());

            return new String(string);
        }
    } // end Node private class

    // Variables
    private int sz;
    protected Node<E> header;
    protected Node<E> trailer;

    // Constructors
    DLL() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
        this.sz = 0;
    }
    DLL(E element) {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, null, null);
        Node<E> node = new Node<E>(element, header, trailer);
        header.setNext(node);
        trailer.setPrev(node);
        this.sz = 1;
    }

    // Methods
    // Query Methods
    // Return size of list, excluding sentinels
    public int size() {
        return this.sz;
    }
    // Returns true if this list contains no elements.
    public boolean isEmpty() {
        return this.sz == 0;
    }
    
    // Insertion Methods
    // Appends the specified element to the beginning of this list.
    public void addFirst(E element) {
        this.addBetween(element, header, header.getNext());
    }
    // Appends the specified element to the end of this list.
    public void addLast(E element) {
        this.addBetween(element, trailer.getPrev(), trailer);
    }
    // Add a new node of 'element' between two known nodes
    protected void addBetween(E element, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<E>(element, before, after);
        before.setNext(newNode);
        after.setPrev(newNode);

        this.sz++;
    }

    // Removal Methods
    // Removes a specified node
    // Returns: the element of the removed node
    protected E remove(Node<E> node) {
        E nodeElement = node.getElement();
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
        node.setNext(null);
        node.setPrev(null);
        this.sz--;

        return nodeElement;
    }
    // Removes first node in the list
    // Returns: the removed node's element
    public E removeFirst() {
        if (this.sz > 0) {
            return remove(this.header.getNext());
        }
        return null;
    }

    // Removes the last node in the list
    // Returns: the removed node's element
    public E removeLast() {
        if (this.sz > 0) {
            return remove(this.trailer.getPrev());
        }
        return null;
    }

    // Peek Methods
    // Returns the first element in list.
    public E first() {
        return this.header.getNext().getElement();
    }
    // Return the last element in list.
    public E last() {
        return this.trailer.getPrev().getElement();
    }
}
