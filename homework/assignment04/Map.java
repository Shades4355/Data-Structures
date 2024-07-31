// File name:   Map.java
// Written by:  Shades Meyers
// Description: A Linked List Map
// Challenges:  
// Time Spent:  24 minutes + ( - )
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File created


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
    }

    // Methods
}
