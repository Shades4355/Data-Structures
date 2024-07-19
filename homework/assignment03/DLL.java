package homework.assignment03;

// File name:   DLL.java
// Written by:  Shades Meyers
// Description: A Doubly Linked List
// Challenges:  
// Time Spent:  49 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      Copied DLL from in-class-04_v2 folder
//                      Copied and modified DLLNode form 
//                          in-class-04_v2
// 2024-July-19 SM      Con't modifications, based on Book's
//                          expectations
//                      Renamed size variable to sz to avoid
//                          confusion with size() method


import java.util.Objects;
import java.util.function.Consumer;

public class DLL<E> {
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

    // variables
    private int sz;
    private Node<E> header;
    private Node<E> trailer;

    // Constructors
    DLL(Node<E> node) {
        this.sz = 1;
        this.header = new Node<E>(null, null, node);
        this.trailer = new Node<E>(null, node, null);
        node.setPrev(this.header);
        node.setNext(this.trailer);
    }
    DLL() {
        this.sz = 0;
        this.header = new Node<E>(null, null, null);
        this.trailer = new Node<E>(null, this.header, null);
        this.header.setNext(this.trailer);
    }

    // Accessors & Mutators
    // Size
    public int size() {
        return this.sz;
    }

    // is empty?
    public boolean isEmpty() {
        return this.sz == 0;
    }

    // Insertions
    public void add(E element) {
        // calls addLast
        this.addLast(element);
    }
    public void addLast(E element) {
        if (this.header == null) { // if list is empty...
            this.addBetween(element, this.header, this.trailer);
        } else { // if list has at least 1 node...
            this.addBetween(element, this.trailer.getPrev(), this.trailer);
        }
    }
    public void addFirst(E element) {
        if (this.header == null) { // if list is empty...
            this.addBetween(element, this.header, this.trailer);
        } else { // if list has at least 1 node...
            this.addBetween(element, this.header, this.header.getNext());
        }
    }

    // Get
    public E get(int index) { // get element at given index
        Node<E> currentNode = this.header;
        if (index < this.sz && index >= 0) {
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.getNext();
            }
        } else {
            return null;
        }
        return currentNode.getElement();
    }
    public E first() { return this.header.getNext().getElement(); }
    public E last() { return this.trailer.getPrev().getElement(); }

    // add between
    private void addBetween(E element, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<E>(element, before, after);
        before.setNext(newNode);
        after.setPrev(newNode);

        this.sz++;
    }

    // removal
    public E removeFirst() {
        if (this.sz > 0) { // if there is a node to remove...
            Node<E> toBeRemoved = this.header.getNext();
            if (toBeRemoved.getNext() != this.trailer) { // if there's more than 1 node...
                this.header.setNext(toBeRemoved.getNext());
                toBeRemoved.getNext().setPrev(this.header);
                this.sz -= 1;
            } else { // if there's only 1 node...
                this.header.setNext(this.trailer);
                this.trailer.setPrev(this.header);
                this.sz = 0;
            }

            return toBeRemoved.getElement();
        } else { // if there's no nodes in list...
            return null;
        }
    }

    public E removeLast() {
        if (this.sz > 0) { // If there's a node to remove...
            Node<E> toBeRemoved = this.trailer.getPrev();
            if (toBeRemoved.getPrev() != this.header) { // if there's more than 1 node in list...
                toBeRemoved.getPrev().setNext(this.trailer);
                this.trailer.setPrev(toBeRemoved.getPrev());
                this.sz -= 1;
            } else { // if there's only 1 node in list...
                this.header.setNext(this.trailer);
                this.trailer.setPrev(this.header);
                this.sz = 0;
            }
            return toBeRemoved.getElement();
        } else { // if there's no nodes in list...
            return null;
        }
    }

    // remove a specific node
    public E remove(Node<E> node) {
        if (this.isEmpty()) { // if the list is empty...
            return null;
        } else if (node == this.header || node == this.trailer) {
            // if we're pointing at the Header or Trailer...
            return null;
        }

        // as long as there's 1 or more nodes in list...
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        return node.getElement();
    }

    // iterable
    // .forEac() accepts lambda expressions
    // ex: list.forEach((n) -> System.out.println(n));
    public void forEach(Consumer<? super E> action) throws ArrayIndexOutOfBoundsException {
        try{
            Objects.requireNonNull(action);
            Node<E> currentNode = this.header.getNext();

            if (this.sz > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode.getElement());
                    currentNode = currentNode.getNext();
                }
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }
} // end program
