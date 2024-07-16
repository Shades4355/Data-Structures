
public class DLLNode<E> {
    // variables
    private E element;
    private DLLNode<E> nextNode;
    private DLLNode<E> prevNode;

    // Constructor
    DLLNode(E element) {
        this.element = element;
    }

    // Accessors & Mutators
    public E getElement() { return this.element; }
    public void setElement(E element) { this.element = element; }

    public DLLNode<E> getNextNode() { return this.nextNode; }
    public void setNextNode(DLLNode<E> nextNode) { this.nextNode = nextNode; }

    public DLLNode<E> getPrevNode() { return this.prevNode; }
    public void setPrevNode(DLLNode<E> prevNode) {
        this.prevNode = prevNode;
    }

    // has next
    public boolean hasNext() { return this.getNextNode() != null; }

} // end class
