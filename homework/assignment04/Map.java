// File name:   Map.java
// Written by:  Shades Meyers
// Description: A Linked List Skip List Map
// Challenges:  
// Time Spent:  1 h 34 minutes + ( - )
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File created
// 2024-Aug-01  SM      Started converting to Skip List


import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class Map <E extends Comparable<E>, T> {
    // Variables
    private int size;
    Node<E, T> start, header, trailer;

    // Constructors
        // TODO: add infinity to headers and trailers
    Map() {
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>(null, this.header, null);
        this.header.setNextNode(this.trailer);
        this.size = 0;
    }
    Map(Pairs<E, T> element) {
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>();
        Node<E, T> newNode = new Node<>(element, header, trailer);
        this.header.setNextNode(newNode);
        this.trailer.setPrevNode(newNode);
        this.size = 1;
    }
    Map(E key, T value) {
        Node<E, T> newNode = new Node<>(new Pairs<E,T>(key, value));
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>();
        this.header.setNextNode(newNode);
        this.trailer.setPrevNode(newNode);
        this.size = 1;
    }

    // Methods
    // Insertion Methods
    public boolean add(E key, T value) {
        Pairs<E, T> newElement = new Pairs<E, T>(key, value);

        return add(newElement);
    }
    public boolean add(Pairs<E, T> element) {
        Node<E, T> newNode = new Node<E, T>(element);

        return add(newNode);
    }
    public boolean add(Node<E, T> node) { // O(n)
        if ((int) node.getValue() <= 0) {
            this.remove(node.getKey());

            return false;
        }

        if (this.isEmpty()) {
            this.addBetween(node, this.header, this.trailer);
            this.size++;

            return true;
        }

        Node<E, T> childNode = this.header.getNextNode();
        while (childNode != this.trailer && childNode.compareTo(node) < 0) {
            childNode = childNode.next();
        }

        if (childNode != this.trailer && childNode.compareTo(node) == 0) {
            this.set(node.getKey(), node.getValue());

            return false;
        }
        this.addBetween(node, childNode.getPrevNode(), childNode);
        this.size++;

        return true;
    }
    public void addBetween(Pairs<E, T> element, Node<E, T> before, Node<E, T> after) {
        Node<E, T> newNode = new Node<E, T>(element);
        this.addBetween(newNode, before, after);
    }
    public void addBetween(Node<E, T> newNode, Node<E, T> before, Node<E, T> after) { // O(1)
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        newNode.setPrevNode(before);
        newNode.setNextNode(after);
    }

    // Removal Methods
    public Pairs<E, T> remove(Node<E, T> node) { // O(1)
        if (this.isEmpty()) { // if the list is empty...
            return null;
        }

        node.getPrevNode().setNextNode(node.getNextNode());
        node.getNextNode().setPrevNode(node.getPrevNode());
        
        this.size--;
        return node.getElement();
    }
    public Pairs<E, T> remove(E key) { // O(n)
        Node<E, T> node = this.search(key);
        
        if (node == null) {
            return null;
        }

        return remove(node);
    }

    public T set(E key, T value) { // O(n)
        Node<E, T> node = this.search(key);
        if (node == null) {
            return null;
        }

        T retVal = node.getValue();
        node.setValue(value);
        
        return retVal;
    }
    
    // Search Methods
    public boolean contains(E key) { return search(key) != null; }
    public Node<E, T> search(E key) { // TODO: make O(log n) ?
        Node<E, T> curNode = this.header;
        for (int i = 0; i < this.size; i++) {
            curNode = curNode.getNextNode();
            if (key.compareTo(curNode.getElement().getKey()) == 0) {
                return curNode;
            }
        }
        return null;
    }
    public Pairs<E, T> get(int index) { // O(n)
        Node<E, T> currentNode = this.header;
        if (index < this.size && index >= 0) {
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            return null;
        }
        return currentNode.getElement();
    }
    public int indexOf(Pairs<E, T> element) { // O(n)
        Node<E, T> curNode = this.header;
        for (int i = 0; i < this.size; i++) {
            curNode = curNode.getNextNode();
            if (curNode.compareTo(element) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Query Methods
    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    // Iterable Methods
    // iterator()
    public ArrayList<Pairs<E, T>> iterable() { // O(n)
        ArrayList<Pairs<E, T>> retList = new ArrayList<>();

        this.forEach((n) -> retList.add(n));

        return retList;
    }
    public void forEach(Consumer<? super Pairs<E, T>> action) throws ArrayIndexOutOfBoundsException {
        // .forEac() accepts lambda expressions
        // ex: list.forEach((n) -> System.out.println(n));
        try{
            Objects.requireNonNull(action);
            Node<E, T> currentNode = this.header.getNextNode();

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

    // To String Method
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        Node<E, T> curNode = this.header.getNextNode();
        string.append("{");
        while (curNode.hasNext()) {
            string.append(curNode);
            if (curNode.getNextNode() != this.trailer) {
                string.append(", ");
            }
            curNode = curNode.getNextNode();
        }
        string.append("}");

        return new String(string);
    }
}
