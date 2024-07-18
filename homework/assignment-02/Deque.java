// File name:   Deque.java
// Written by:  Shades Meyers
// Description: A Double-Ended Queue, implemented
//                  with a Double Linked List.
// Challenges:  None
// Time Spent:  30 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-17 SM      File created & outlined needed Methods
// 2024-July-18 SM      Added all Methods


public class Deque<E> {
    // variables
    private DoublyLL<E> data;

    // Constructors
    Deque() {
        data = new DoublyLL<E>();
    }
    Deque( E element ) {
        // new node with element
        data = new DoublyLL<E>();
        // add node to DLL list
        data.addLast(element);
    }

    // Methods
    // get size
    public int size() { return data.getSize(); }

    // check if list is empty
    public boolean isEmpty() { return data.isEmpty(); }

    // peek at list
    public E first() { return data.first(); }
    public E last() { return data.last(); }

    // add elements to list
    public void add(E element) { data.addLast(element); }
    public void addFirst(E element) { data.addFirst(element); }
    public void addLast(E element) { data.addLast(element); }

    // remove elements from list
    public E removeFirst() { return data.removeFirst(); }
    public E removeLast() { return data.removeLast(); }

} // end program
