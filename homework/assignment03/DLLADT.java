package homework.assignment03;

// File name:   DLLADT.java
//              (Doubly Linked List Abstract Data Type)
// Written by:  Shades Meyers
// Description: A Doubly Linked List interface
// Challenges:  
// Time Spent:  6 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      File created
// 2024-July-19 SM      Changed add() return type to boolean


public interface DLLADT<E> {
    public int size();
    public boolean isEmpty();

    // insertion methods
    public boolean add(E element);
    public void addFirst(E element);
    public void addLast(E element);

    // removal methods
    public E removeFirst();
    public E removeLast();

    // peek
    public E first();
    public E last();
    public E get(int index);
}
