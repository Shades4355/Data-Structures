// File name:   DLL.java
// Written by:  Shades Meyers
// Description: A Doubly Linked List
// Challenges:  
// Time Spent:  5 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File copied from in-class work
//                      Modified for use with a Map


import java.util.Objects;
import java.util.function.Consumer;

public class DLL<E extends Comparable<E>, T> {
    // variables
    private int size;
    private DLLNode<E, T> header;
    private DLLNode<E, T> trailer;

    // Constructors
    DLL(DLLNode<E, T> node) {
        this.size = 1;
        this.header = node;
        this.trailer = node;
    }
    DLL() {
        this.size = 0;
        this.header = null;
        this.trailer = null;
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
    public void add(Pairs<E, T> element) {
        // calls addLast
        this.addLast(element);
    }
    public void addLast(Pairs<E, T> element) {
        DLLNode<E, T> node = new DLLNode<E, T>(element);

        if (this.header == null) { // if list is empty...
            this.header = node;
            this.trailer = node;
        } else { // if list has at least 1 node...
            this.trailer.setNextNode(node);
            node.setPrevNode(this.trailer);
            this.trailer = node;
        }
        this.size += 1;
    }
    public void addFirst(Pairs<E, T> element) {
        DLLNode<E, T> node = new DLLNode<E, T>(element);

        if (this.header == null) { // if list is empty...
            this.header = node;
            this.trailer = node;
        } else { // if list has at least 1 node...
            node.setNextNode(this.header);
            this.header.setPrevNode(node);
            this.header = node;
        }

        this.size += 1;
    }

    // Get
    public Pairs<E, T> get(int index) { // get element at given index
        DLLNode<E, T> currentNode = this.header;
        if (index < this.size && index >= 0) {
            for (int i = 1; i <= index; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            return null;
        }
        return currentNode.getElement();
    }
    public Pairs<E, T> first() { return this.header.getElement(); }
    public Pairs<E, T> last() { return this.trailer.getElement(); }

    // add between
    public void addBetween(Pairs<E, T> element, DLLNode<E, T> before, DLLNode<E, T> after) {
        DLLNode<E, T> newNode = new DLLNode<E, T>(element);
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        newNode.setPrevNode(before);
        newNode.setNextNode(after);
    }

    // removal
    public Pairs<E, T> removeFirst() {
        if (this.size > 0){ // if there is a node to remove...
            DLLNode<E, T> toBeRemoved = this.header;
            if (toBeRemoved.getNextNode() != null) { // if there's more than 1 node...
                this.header = toBeRemoved.getNextNode();
                size -= 1;
            } else { // if there's only 1 node...
                this.header = null;
                this.trailer = null;
                this.size = 0;
            }

            return toBeRemoved.getElement();
        } else { // if there's no nodes in list...
            return null;
        }
    }

    public Pairs<E, T> removeLast() {
        if (this.size > 0) { // If there's a node to remove...
            DLLNode<E, T> oldTrailer = this.trailer;
            if (oldTrailer.getPrevNode() != null) { // if there's more than 1 node in list...
                DLLNode<E, T> node = this.trailer.getPrevNode();

                node.setNextNode(null);
                this.trailer = node;
                this.size -= 1;
            } else { // if there's only 1 node in list...
                this.header = null;
                this.trailer = null;
                this.size = 0;
            }
            return oldTrailer.getElement();
        } else { // if there's no nodes in list...
            return null;
        }
    }

    // remove a specific node
    public Pairs<E, T> remove(DLLNode<E, T> node) {
        if (this.isEmpty()) { // if the list is empty...
            return null;
        }

        // as long as there's 1 or more nodes in list...
        if (node.getPrevNode() != null && node.getNextNode() != null) {
            // if node has a before and after...
            node.getPrevNode().setNextNode(node.getNextNode());
            node.getNextNode().setPrevNode(node.getPrevNode());
        } else if (node.getPrevNode() == null && node.getNextNode() != null) {
            // if node is the Header...
            this.removeFirst();
        } else { // if node is the Trailer...
            this.removeLast();
        }

        return node.getElement();
    }

    // iterable
    // .forEac() accepts lambda expressions
    // ex: list.forEach((n) -> System.out.println(n));
    public void forEach(Consumer<? super Pairs<E, T>> action) throws ArrayIndexOutOfBoundsException {
        try{
            Objects.requireNonNull(action);
            DLLNode<E, T> currentNode = this.header;

            if (this.size > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode.getElement());
                    currentNode = currentNode.getNextNode();
                }
                action.accept(currentNode.getElement());
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }
} // end program
