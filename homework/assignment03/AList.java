package homework.assignment03;

// File name:   AList.java
// Written by:  Shades Meyers
// Description: A generic ArrayList implementation
// Challenges:  
// Time Spent:  35 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      File created
// 2024-July-19 SM      Added comments and methods from AListADT
//                      Began filling out methods


public class AList<E> implements AListADT<E> {
    // private Node class
    private static class Node<E> implements Position<E> {
        // variables
        private E element;
        private Node<E> nextNode;
        private Node<E> prevNode;

        // Constructor
        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prevNode = prev;
            this.nextNode = next;
        }

        // Accessors & Mutators
        @Override // implement Position interface's getElement()
        public E getElement() throws IllegalStateException {
            if (nextNode == null) {
                throw new IllegalStateException("Position no longer valid");
            }
            return this.element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return this.nextNode;
        }

        public void setNext(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

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

    } // end Node class
    
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
    // return size of list, excluding sentinels 
    public int size() { return this.sz; };

    // Returns true if this list contains no elements.
    public boolean isEmpty() { return this.sz == 0; };

    // insertion methods
    // Appends the specified element to the end of this list. (at the tail end.)
    // Returns: true
    public boolean add(E element){
        this.addBetween(element, trailer.getPrev(), trailer);

        return true;
    };
    // Appends the specified element to the beginning of this list.
    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    };
    // Appends the specified element to the end of this list. 
    public void addLast(E element){
        add(element);
    };
    // add between
    private void addBetween(E element, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<E>(element, before, after);
        before.setNext(newNode);
        after.setPrev(newNode);

        this.sz++;
    }
    // Inserts the specified element at the specified position in this list.
    public void add(int index, E element) throws IndexOutOfBoundsException;

    // Returns the element at the specified position in this list.
    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> currentNode = this.header;
        if (index < this.sz && index >= 0) {
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.getNext();
            }

            return currentNode.getElement();
        } else {
            throw new IndexOutOfBoundsException();
        }
    };

    // Replaces the element at the specified position in this list with the
    // specified element.
    // Returns: the element previously at the specified position (i.e. the old
    // element that was replaced.)
    public E set(int index, E element) throws IndexOutOfBoundsException {
        Node<E> currentNode = this.header;
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.getNext();
        }
        E oldElement = currentNode.getElement();
        currentNode.setElement(element);

        return oldElement;
    };

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
    };

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
    };

    // Removes the element at the specified position in this list.
    // Returns: the element that was removed from the list
    public E remove(int index) throws IndexOutOfBoundsException;

    // Removes the first occurrence of the specified element from this list, if it
    // is present.
    // Returns: true if this list contained the specified element, false otherwise
    public boolean remove(E e);
    // boolean remove(Object o);

    // Returns a string containing all the elements of the list that matches the
    // output format of the spec
    public String toString();

    // AList.java must also contain the following "worker" method that is used by
    // several of the methods above.
    // Find non-sentinel node at 0 based position (i.e. first non-sentinel node is
    // at index 0)
    // private DLLNode<E> findNode(int index) throws IndexOutOfBoundsException;
}
