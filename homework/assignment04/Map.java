// File name:   Map.java
// Written by:  Shades Meyers
// Description: A Linked List Skip List Map
// Challenges:  
// Time Spent:  2 h 28 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File created
// 2024-Aug-01  SM      Started converting to Skip List


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

public class Map <E extends Comparable<E>, T> {
    // Variables
    private int size, height;
    private Node<E, T> start, end, header, trailer;

    // Constructors
    Map() {
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>(null, this.header, null);
        this.header.setNextNode(this.trailer);
        this.start = this.header;
        this.end = this.trailer;
        this.height = 0;
        this.addNewList();
        this.size = 0;
    }
    Map(Pairs<E, T> element) {
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>();
        this.start = this.header;
        this.end = this.trailer;

        this.header.setNextNode(trailer);
        this.trailer.setPrevNode(header);
        this.size = 0;
        this.height = 0;
        this.addNewList();
        Node<E, T> newNode = new Node<>(element, header, trailer);
        this.add(newNode);
    }
    Map(E key, T value) {
        Node<E, T> newNode = new Node<>(new Pairs<E,T>(key, value));
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>();
        this.header.setNextNode(newNode);
        this.trailer.setPrevNode(newNode);
        this.size = 1;
        this.height = 0;
        this.addNewList();
        this.add(key, value);
    }

    // Methods
    // Accessors & Mutators
    public Node<E, T> getStart() { return this.start; }

    // Insertion Methods
    public boolean add(E key, T value) {
        Pairs<E, T> newElement = new Pairs<E, T>(key, value);

        return add(newElement);
    }
    public boolean add(Pairs<E, T> element) {
        Node<E, T> newNode = new Node<E, T>(element);

        return add(newNode);
    }
    public boolean add(Node<E, T> node) { // O(log n)
        if ((int) node.getValue() <= 0) {
            this.remove(node.getKey());

            return false;
        }

        if (this.isEmpty()) {
            this.addBetween(node, this.header, this.trailer, 0);
            this.size++;

            return true;
        }

        Node<E, T> parentNode = search(node.getKey()); // O(log n)
        if (parentNode.getElement() != null && parentNode.compareTo(node) == 0) {
            this.set(parentNode, node.getValue());
            return false;
        }

        this.addBetween(node, parentNode, parentNode.next(), 0);
        this.size++;

        return true;
    }
    public void addAbove(Node<E, T> node, int newHeight) {
        while (newHeight >= this.height) { // make sure no tower enters the top level
            this.addNewList();
        }

        Node<E, T> newNode = new Node<>(new Pairs<>(node.getKey(), null));
        newNode.setBelowNode(node);
        node.setAboveNode(newNode);

        Node<E, T> newBefore = node.getPrevNode();
        while (newBefore.getElement() != null && newBefore.getAboveNode() == null) {
            // newBefore.getElement() == null means we've reached the Header
            newBefore = newBefore.getPrevNode();
        }
        
        this.addBetween(newNode, newBefore, newBefore.next(), newHeight);
    }
    public void addNewList() {
        Node<E, T> newStart = new Node<>(this.start.getElement(), null, null);
        Node<E, T> newEnd = new Node<>(this.end.getElement(), newStart, null);

        newStart.setNextNode(newEnd);
        newStart.setBelowNode(this.start);
        this.start.setAboveNode(newStart);
        this.start = newStart;

        newEnd.setBelowNode(this.end);
        this.end.setAboveNode(newEnd);
        this.end = newEnd;

        this.height++;
    }
    public void addBetween(Pairs<E, T> element, Node<E, T> before, Node<E, T> after, int curHeight) {
        Node<E, T> newNode = new Node<E, T>(element);
        this.addBetween(newNode, before, after, curHeight);
    }
    public void addBetween(Node<E, T> newNode, Node<E, T> before, Node<E, T> after, int curHeight) {
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        newNode.setPrevNode(before);
        newNode.setNextNode(after);

        boolean coin = new Random().nextBoolean();
        if (coin) {
            this.addAbove(newNode, curHeight + 1);
        }
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

    public T set(E key, T value) { // O(log n)
        Node<E, T> node = this.search(key);
        if (node == null) {
            return null;
        }

        T retVal = node.getValue();
        node.setValue(value);
        
        return retVal;
    }
    public T set(Node<E, T> node, T value) { // O(1)
        T retVal = node.getValue();
        node.setValue(value);

        return retVal;
    }
    
    // Search Methods
    public boolean contains(E key) { return search(key) != null; }
    public Node<E, T> search(E key) { // O(log n)
        Node<E, T> curNode = this.start;
        while (curNode.getBelowNode() != null) {
            curNode = curNode.getBelowNode();
            while (curNode.next().getElement() != null &&
                curNode.next().compareTo(key) <= 0) {
                    curNode = curNode.next();
                }
        }
        return curNode;
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
