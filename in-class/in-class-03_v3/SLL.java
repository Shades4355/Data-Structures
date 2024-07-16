// File name:   SLL.java
// Written by:  Shades Meyers
// Description: A single linked list of SLLNodes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-16 SM      Updated based on Grade feedback


public class SLL<E> {
    // variables
    private int size;
    private SLLNode<E> header;

    // Constructors
    SLL(SLLNode<E> header) {
        this.size = 1;
        this.header = header;
    }
    SLL() {
        this.size = 0;
        this.header = null;
    }

    // Accessors & Mutators
    // Size
    public int size() { return this.size; }
    // is empty?
    public boolean isEmpty() { return this.header == null; }
    // Insertions
    public void insert(SLLNode<E> node) {
        SLLNode<E> currentNode = this.header;
        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        currentNode.setNextNode(node);
        this.size += 1;
    }
    public void insertLast(SLLNode<E> node) {
        this.insert(node);
    }
    public void insertFirst(SLLNode<E> node) {
        node.setNextNode(this.header);
        this.header = node;
        this.size += 1;
    }
    // Get
    public E get(int num) throws ArrayIndexOutOfBoundsException {
        SLLNode<E> currentNode = this.header;
        if (num <= this.size) {
            for (int i = 1; i < num; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

        return currentNode.getElement();
    }
    
    // get Head
    public SLLNode<E> getHeader() { return this.header; }

    // get first
    public E first() {
        return this.header.getElement();
    }
    // get last
    public E last() {
        SLLNode<E> node = this.header;

        while (node.getNextNode() != null) {
            node = node.getNextNode();
        }
        return node.getElement();
    }
    // removal
    public E removeFirst() throws Exception {
        SLLNode<E> removed = this.header;
        if (!this.isEmpty()) {
            this.header = this.header.getNextNode();
            size -= 1;
        } else {
            throw new Exception("Single Linked List is already empty");
        }
        return removed.getElement();
    }
    public E removeLast() throws Exception {
        SLLNode<E> node1 = this.header;
        SLLNode<E> node2 = null;

        while (node1.getNextNode() != null) {
            node2 = node1;
            node1 = node1.getNextNode();
        }
        if (node2 != null) {
            node2.setNextNode(null);
            size -= 1;
        } else {
            throw new Exception("Single Linked List already empty");
        }
        return node1.getElement();
    }
    public void remove() throws Exception {
        this.removeLast();
    }

} // end program
