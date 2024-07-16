
public class SLLNode {
    // variables
    private String element;
    private SLLNode nextNode;

    // Constructor
    SLLNode(String element) {
        this.element = element;
    }

    // Accessors & Mutators
    public String getElement() { return this.element; }
    public void setElement(String element) { this.element = element; }

    public SLLNode getNextNode() { return this.nextNode; }
    public void setNextNode(SLLNode nextNode) { this.nextNode = nextNode; }

    @Override
    public String toString() {
        return this.getElement();
    }

} // end class
