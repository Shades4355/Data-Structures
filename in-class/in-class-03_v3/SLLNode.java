
public class SLLNode<E> {
    // variables
    private E element;
    private SLLNode<E> nextNode;

    // Constructor
    SLLNode(E element) {
        this.element = element;
    }

    // Accessors & Mutators
    public E getElement() { return this.element; }
    public void setElement(E element) { this.element = element; }

    public SLLNode<E> getNextNode() { return this.nextNode; }
    public void setNextNode(SLLNode<E> nextNode) { this.nextNode = nextNode; }

} // end class
