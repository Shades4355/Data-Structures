// File name:   AList.java
// Written by:  Shades Meyers
// Description: A generic ArrayList implementation
// Challenges:  add(index, element) failing for non-obvious
//                  reasons. Something along the action chain 
//                  is returning a null value when it shouldn't
// Time Spent:  2 h 13 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      File created
// 2024-July-19 SM      Added comments and methods from AListADT
//                      Began filling out methods
//                      Added a modified forEach method for use
//                          in the toString method
//                      First run of integer test file


import java.util.Objects;
import java.util.function.Consumer;

public class AList<E> implements AListADT<E> {
    // private Node class
    private static class Node<E> {
        // variables
        private E element;
        private Node<E> nextNode;
        private Node<E> prevNode;

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

        // Next
        public Node<E> getNext() {
            return this.nextNode;
        }
        public void setNext(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        // Previous
        public Node<E> getPrev() {
            return this.prevNode;
        }
        public void setPrev(Node<E> prevNode) {
            this.prevNode = prevNode;
        }

        // has next
        public boolean hasNext() {
            return this.getNext() != null;
        }
    } // end Node private class
    
    // Variables
    private int sz;
    private Node<E> header;
    private Node<E> trailer;

    // Constructor
    AList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
        this.sz = 0;
    }
    AList(E element) {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, null, null);
        Node<E> node = new Node<E>(element, header, trailer);
        header.setNext(node);
        trailer.setPrev(node);
        this.sz = 1;
    }

    // Methods
    // Return size of list, excluding sentinels 
    public int size() { return this.sz; }

    // Returns true if this list contains no elements.
    public boolean isEmpty() { return this.sz == 0; }

    // Insertion methods
    // Appends the specified element to the end of this list. (at the tail end.)
    // Returns: true
    public boolean add(E element) {
        this.addLast(element);

        return true;
    }
    // Appends the specified element to the beginning of this list.
    public void addFirst(E element) {
        this.addBetween(element, header, header.getNext());
    }
    // Appends the specified element to the end of this list. 
    public void addLast(E element) {
        this.addBetween(element, trailer.getPrev(), trailer);
    }
    // Add a new node of 'element' between two known nodes
    private void addBetween(E element, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<E>(element, before, after);
        before.setNext(newNode);
        after.setPrev(newNode);

        this.sz++;
    }
    // Inserts the specified element at the specified position in this list.
    public void add(int index, E element) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        this.addBetween(element, node.getPrev(), node);
    }

    // Peek methods
    // Returns the element at the specified position in this list.
    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        return node.getElement();
    }
    // Returns the first element in list.
    public E first() {
        return this.header.getNext().getElement();
    }
    // Return the last element in list.
    public E last() {
        return this.trailer.getPrev().getElement();
    }

    // Replaces the element at the specified position in this list with the
    // specified element.
    // Returns: the element previously at the specified position (i.e. the old
    // element that was replaced.)
    public E set(int index, E element) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        E oldElement = node.getElement();
        node.setElement(element);
        return oldElement;
    }

    // Returns the index of the first occurrence of the specified element in this
    // list, or -1 if this list does not contain the element.
    public int indexOf(E element) {
        Node<E> currentNode = this.header;
        for (int i = 0; i < this.sz; i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getElement() == element) {
                return i;
            }
        }
        return -1;
    }

    // Returns true if this list contains the specified element, false otherwise
    public boolean contains(E element) {
        Node<E> currentNode = this.header;
        for (int i = 0; i < this.sz; i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getElement() == element) {
                return true;
            }
        }
        return false;
    }

    // Removal methods
    // Removes the element at the specified position in this list.
    // Returns: the element that was removed from the list
    public E remove(int index) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        return this.remove(node);
    }
    // Removes the specified node
    private E remove(Node<E> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setNext(null);
        node.setPrev(null);
        this.sz--;

        return node.getElement();
    }
    // Removes the first occurrence of the specified element from this list, if it
    // is present.
    // Returns: true if this list contained the specified element, false otherwise
    public boolean remove(E element) {
        Node<E> currentNode = this.header;
        for (int i = 0; i < this.sz; i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getElement() == element) {
                remove(currentNode);
                this.sz--;
                return true;
            }
        }
        return false;
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

    // Returns a string containing all the elements of the list that matches the
    // output format of the spec
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("The AList:{");
        if (this.sz > 0) {
            this.forEach((n) -> {
                string.append(n.getElement());
                if (n.getNext() != this.trailer) {
                    string.append(",");
                }
            });
        }
        string.append("}");

        return new String(string);
    }

    // AList.java must also contain the following "worker" method that is used by
    // several of the methods above.
    // Find non-sentinel node at 0 based position (i.e. first non-sentinel node is
    // at index 0)
    private Node<E> findNode(int index) throws IndexOutOfBoundsException{
        if (validateIndex(index)) {
            Node<E> currentNode = this.header;
            for (int i = 0; i >= index; i++) {
                currentNode = currentNode.getNext();
            }

            return currentNode;
        }
        return null;
    }

    // Validates that a given index is within the bounds of the list
    // Returns: true or throws an exception
    private boolean validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < this.sz) {
            return true;
        }
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    // iterable
    // forEach accepts lambda expressions
    // ex: list.forEach((n) -> System.out.println(n));
    public void forEach(Consumer<? super Node<E>> action) throws ArrayIndexOutOfBoundsException {
        try{
            Objects.requireNonNull(action);
            Node<E> currentNode = this.header.getNext();

            if (this.sz > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode);
                    currentNode = currentNode.getNext();
                }
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }
}
