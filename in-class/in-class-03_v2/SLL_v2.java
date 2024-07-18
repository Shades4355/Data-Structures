
public class SLL_v2<E> {
    // variables
    private int size;
    private SLLNode<E> header;

    // Constructors
    SLL_v2(SLLNode<E> header) {
        this.size = 1;
        this.header = header;
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
    public SLLNode<E> get(int num) throws ArrayIndexOutOfBoundsException {
        SLLNode<E> currentNode = this.header;
        if (num <= this.size) {
            for (int i = 1; i < num; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

        return currentNode;
    }
    // get first
    public SLLNode<E> first() {
        return this.header;
    }
    // get last
    public SLLNode<E> last() {
        SLLNode<E> node = this.header;

        while (node.getNextNode() != null) {
            node = node.getNextNode();
        }
        return node;
    }
    // removal
    public void removeFirst() throws Exception {
        if (this.header.getNextNode() != null) {
            this.header = this.header.getNextNode();
            size -= 1;
        } else {
            throw new Exception("Single Linked List must contain at least one entry");
        }
    }
    public void removeLast() throws Exception {
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
            throw new Exception("Single Linked List must contain at least one entry");
        }
    }
    public void remove() throws Exception {
        this.removeLast();
    }

} // end program
