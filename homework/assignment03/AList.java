// File name:   AList.java
// Written by:  Shades Meyers
// Description: A generic ArrayList implementation
// Challenges:  add(index, element) failing for non-obvious
//                  reasons. Something along the action chain 
//                  is returning a null value when it shouldn't
//                  (solution: Had >= instead of <= in a for loop; oops)
// Time Spent:  3 h 26 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-18 SM      File created
// 2024-July-19 SM      Added comments and methods from AListADT
//                      Began filling out methods
//                      Added a modified forEach method for use
//                          in the toString method
//                      First run of Integer test file
//                      Passed Integer test after some adjustments
//                      First run of Person test file - passes
// 2024-July-23 SM      Moved several Methods into new DLL file
//                      Extended DLL<E>
//                      Changed several instances of "this" to "super"
//                          for readability


import java.util.Objects;
import java.util.function.Consumer;

public class AList<E> extends DLL<E> implements AListADT<E> {

    // Constructors
    AList() {
        super();
    }
    AList(E element) {
        super(element);
    }

    // Methods
    // Insertion methods
    // Appends the specified element to the end of this list. (at the tail end.)
    // Returns: true
    public boolean add(E element) {
        super.addLast(element);

        return true;
    }
    // Inserts the specified element at the specified position in this list.
    public void add(int index, E element) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        super.addBetween(element, node.getPrev(), node);
    }

    // Peek methods
    // Returns the element at the specified position in this list.
    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> node = findNode(index);
        return node.getElement();
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
        Node<E> currentNode = super.header;
        for (int i = 0; i < super.size(); i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getElement() == element) {
                return i;
            }
        }
        return -1;
    }

    // Returns true if this list contains the specified element, false otherwise
    public boolean contains(E element) {
        Node<E> currentNode = super.header;
        for (int i = 0; i < super.size(); i++) {
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
        return super.remove(node);
    }
    
    // Removes the first occurrence of the specified element from this list, if it
    // is present.
    // Returns: true if this list contained the specified element, false otherwise
    public boolean remove(E element) {
        Node<E> currentNode = super.header;
        for (int i = 0; i < super.size(); i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getElement() == element) {
                super.remove(currentNode);
                return true;
            }
        }
        return false;
    }
    
    // Returns a string containing all the elements of the list that matches the
    // output format of the spec
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("The AList:{");
        if (super.size() > 0) {
            this.forEach((n) -> {
                string.append(n.getElement());
                if (n.getNext() != super.trailer) {
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
            Node<E> currentNode = super.header;
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
        return null;
    }

    // Validates that a given index is within the bounds of the list
    // Returns: true or throws an exception
    private boolean validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < super.size()) {
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
            Node<E> currentNode = super.header.getNext();

            if (super.size() > 0) {
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
