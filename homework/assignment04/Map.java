// File name:   Map.java
// Written by:  Shades Meyers
// Description: A Linked List Map
// Challenges:  
// Time Spent:  27 minutes + ( - )
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File created


import java.util.Objects;
import java.util.function.Consumer;

public class Map <E extends Comparable<E>, T> {
    // Variables
    private int size;
    DLLNode<E, T> header, trailer;

    // Constructors
    Map() {
        this.header = new DLLNode<E, T>();
        this.trailer = new DLLNode<E, T>();
        this.size = 0;
    }
    Map(Pairs<E, T> element) {
        this.header = new DLLNode<E, T>();
        this.trailer = new DLLNode<E, T>();
        DLLNode<E, T> newNode = new DLLNode<>(element, header, trailer);
        this.header.setNextNode(newNode);
        this.trailer.setPrevNode(newNode);
        this.size = 1;
    }Map(E key, T value) {
        DLLNode<E, T> newNode = new DLLNode<>(new Pairs<E,T>(key, value));

        // TODO: finish
    }

    // Methods
    // Insertion Methods
    // add()
    public void addBetween(Pairs<E, T> element, DLLNode<E, T> before, DLLNode<E, T> after) {
        DLLNode<E, T> newNode = new DLLNode<E, T>(element);
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        newNode.setPrevNode(before);
        newNode.setNextNode(after);
    }

    // Removal Methods
    // remove()
    // set()
    
    // Search Methods
    // contains()
    // search()
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
    // indexOf()

    // Query Methods
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    // Iterable Methods
    // iterator()
    // iterable()
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

    // To String Method
    // toString()
}
