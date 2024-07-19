package homework.assignment03;

// File name:   DLL.java
// Written by:  Shades Meyers
// Description: A Doubly Linked List
// Challenges:  
// Time Spent:  42 min + 
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      Copied DLL from in-class-04_v2 folder
//                      Copied and modified DLLNode form 
//                          in-class-04_v2


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

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public Node<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(Node<E> prevNode) {
            this.prevNode = prevNode;
        }

        // has next
        public boolean hasNext() {
            return this.getNextNode() != null;
        }

    } // end Node class

    // variables
    private int size;
    private Node<E> header;
    private Node<E> trailer;

    // Constructors
    DLL(Node<E> node) {
        this.size = 1;
        this.header = new Node<E>(null, null, node);
        this.trailer = new Node<E>(null, node, null);
        node.setPrevNode(this.header);
        node.setNextNode(this.trailer);
    }
    DLL() {
        this.size = 0;
        this.header = new Node<E>(null, null, null);
        this.trailer = new Node<E>(null, this.header, null);
        this.header.setNextNode(this.trailer);
    }

    // Accessors & Mutators
    // Size
    public int size() {
        return this.size;
    }

    // is empty?
    public boolean isEmpty() {
        return this.size == 0;
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
            this.addBetween(element, this.trailer.getPrevNode(), this.trailer);
        }
    }
    public void addFirst(E element) {
        if (this.header == null) { // if list is empty...
            this.addBetween(element, this.header, this.trailer);
        } else { // if list has at least 1 node...
            this.addBetween(element, this.header, this.header.getNextNode());
        }
    }

    // Get
    public E get(int index) { // get element at given index
        Node<E> currentNode = this.header;
        if (index < this.size && index >= 0) {
            for (int i = 1; i <= index; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            return null;
        }
        return currentNode.getElement();
    }
    public E first() { return this.header.getNextNode().getElement(); }
    public E last() { return this.trailer.getPrevNode().getElement(); }

    // add between
    public void addBetween(E element, Node<E> before, Node<E> after) {
        Node<E> newNode = new Node<E>(element, before, after);
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        this.size++;
    }

    // removal
    public E removeFirst() {
        if (this.size > 0) { // if there is a node to remove...
            Node<E> toBeRemoved = this.header.getNextNode();
            if (toBeRemoved.getNextNode() != this.trailer) { // if there's more than 1 node...
                this.header.setNextNode(toBeRemoved.getNextNode());
                toBeRemoved.getNextNode().setPrevNode(this.header);
                size -= 1;
            } else { // if there's only 1 node...
                this.header.setNextNode(this.trailer);
                this.trailer.setPrevNode(this.header);
                this.size = 0;
            }

            return toBeRemoved.getElement();
        } else { // if there's no nodes in list...
            return null;
        }
    }

    public E removeLast() {
        if (this.size > 0) { // If there's a node to remove...
            Node<E> toBeRemoved = this.trailer.getPrevNode();
            if (toBeRemoved.getPrevNode() != this.header) { // if there's more than 1 node in list...
                toBeRemoved.getPrevNode().setNextNode(this.trailer);
                this.trailer.setPrevNode(toBeRemoved.getPrevNode());
                this.size -= 1;
            } else { // if there's only 1 node in list...
                this.header.setNextNode(this.trailer);
                this.trailer.setPrevNode(this.header);
                this.size = 0;
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
        node.getPrevNode().setNextNode(node.getNextNode());
        node.getNextNode().setPrevNode(node.getPrevNode());

        return node.getElement();
    }

    // iterable
    // .forEac() accepts lambda expressions
    // ex: list.forEach((n) -> System.out.println(n));
    public void forEach(Consumer<? super E> action) throws ArrayIndexOutOfBoundsException {
        try{
            Objects.requireNonNull(action);
            Node<E> currentNode = this.header.getNextNode();

            if (this.size > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode.getElement());
                    currentNode = currentNode.getNextNode();
                }
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }
} // end program
