// File name:   Map.java
// Written by:  Shades Meyers
// Description: A Linked Skip List Map
// Challenges:  
// Time Spent:  5 h 15 minutes

//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-31 SM      File created
// 2024-Aug-01  SM      Converted to a Skip List



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
        
        this.height = 0;
        this.addNewList();
        
        Node<E, T> newNode = new Node<>(element, header, trailer);
        this.size = 0;
        this.add(newNode); // add() increments size to 1
    }
    Map(E key, T value) {
        this.header = new Node<E, T>();
        this.trailer = new Node<E, T>();
        this.header.setNextNode(this.trailer);
        this.trailer.setPrevNode(this.header);
        
        this.height = 0;
        this.addNewList();
        
        this.size = 0;
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
        Node<E, T> parentNode = search(node.getKey()); // O(log n)

        if (parentNode.getElement() != null && parentNode.compareTo(node) == 0) {
            if ((int) node.getValue() <= 0) {
                this.remove(node.getKey());

                return false;
            }

            this.set(parentNode, node.getValue());
          
            return false;
        }

        if (this.isEmpty()) {
            this.addBetween(node, this.header, this.trailer, 0);

            this.size++;

            return true;
        }

        this.addBetween(node, parentNode, parentNode.next(), 0);
        this.size++;

        return true;
    }
    public void addAbove(Node<E, T> node, int newHeight) {
        if (newHeight == this.height) { // make sure no tower enters the top level
            this.addNewList();
        }

        Node<E, T> newNode = new Node<E, T>(new Pairs<E, T>(node.getKey(), null));
        newNode.setBelowNode(node);
        node.setAboveNode(newNode);

        Node<E, T> newBefore = node.getPrevNode();
        while (newBefore.getElement() != null && newBefore.getAboveNode() == null) {
            // newBefore.getElement() == null means we've reached the left sentinel
            newBefore = newBefore.getPrevNode();
        }

        newBefore = newBefore.getAboveNode();

        if (newBefore == null) {
            throw new ArrayIndexOutOfBoundsException("Node is missing");
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

        if (node.getAboveNode() != null) {
            this.removeAbove(node.getAboveNode());
            node.setAboveNode(null);
        }

        // Garbage collection help
        node.setPrevNode(null);
        node.setNextNode(null);

        return node.getElement();
    }
    public Pairs<E, T> remove(E key) { // O(log n)
        if (this.isEmpty()) { // if the list is empty...
            return null;
        }

        Node<E, T> node = this.search(key);

        if (node.compareTo(key) == 0) { // If node is found; remove
            return remove(node);
        }

        return null; // return null is node is not found
    }
    public void removeAbove(Node<E, T> node) { // O(h)
        node.getPrevNode().setNextNode(node.next());
        node.next().setPrevNode(node.getPrevNode());
        
        node.setBelowNode(null);
        node.setPrevNode(null);
        node.setElement(null);
        node.setElement(null);
        
        if (node.getAboveNode() != null) {
            this.removeAbove(node.getAboveNode());
        }
        
        node.setAboveNode(null);
    }

    public T set(E key, T value) { // O(log n)
        Node<E, T> node = this.search(key);

        if (node.compareTo(key) == 0) {
            T retVal = node.getValue();
            node.setValue(value);

            return retVal;
        }

        return null;
    }
    public T set(Node<E, T> node, T value) { // O(1)
        T retVal = node.getValue();
        node.setValue(value);

        return retVal;
    }

    // Search Methods
    public boolean contains(E key) { // O(log n)
        Node<E, T> found = search(key);

        return found != null
        && found.getElement() != null
        && found.compareTo(key) == 0; 
    } 
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
            Node<E, T> currentNode = this.header.next();

            if (this.size > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode.getElement());
                    currentNode = currentNode.next();
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
        Node<E, T> curNode = this.header.next();
        string.append("{");
        while (curNode.hasNext()) {
            string.append(curNode);
            if (curNode.next() != this.trailer) {
                string.append(", ");
            }
            curNode = curNode.next();
        }
        string.append("}");

        return new String(string);
    }
}
