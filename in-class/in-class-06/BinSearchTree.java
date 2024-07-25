import java.util.ArrayList;

public class BinSearchTree<E> {
    // private Node
    private class BSTNode<E> implements Comparable{
        // Variables
        private E element;
        private BSTNode<E> parent, leftChild, rightChild;

        // Constructors
        BSTNode(E element, BSTNode<E> parent) {
            this.element = element;
            this.parent = parent;
            this.leftChild = new BSTNode<E>(null, this, null, null);
            this.rightChild = new BSTNode<E>(null, this, null, null);

        }
        BSTNode(E element, BSTNode<E> parent, BSTNode<E> leftChild, BSTNode<E> rightChild) {
            this.element = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        // Methods
        // Accessors & Mutators
        public BSTNode<E> getParent() { return this.parent; }
        public void setParent(BSTNode<E> node) { this.parent = node; }
        public BSTNode<E> getLeftChild() { return this.leftChild; }
        public void setLeftChild(BSTNode<E> node) { this.leftChild = node; }
        public BSTNode<E> getRightChild() { return this.rightChild; }
        public void setRightChild(BSTNode<E> node) { this.rightChild = node; }
        public E getElement() { return this.element; }
        public E setElement(E element) {
            E oldElement = this.getElement();
            this.element = element;
            return oldElement;
        }

        // Iterables
        public ArrayList<BSTNode<E>> children() {
            ArrayList<BSTNode<E>> retList = new ArrayList<BSTNode<E>>();
            
            if (this.leftChild != null) {
                retList.add(this.leftChild);
            }
            if (this.rightChild != null) {
                retList.add(this.rightChild);
            }
            return retList;
        }

        // Query Methods
        public boolean isLeaf() { return this.getLeftChild() == null && this.getRightChild() == null; }

        // Comparison
        @Override
        public int compareTo(Object element) {
            return this.getElement().toString().compareTo(element.toString());
        }
    }

    // Variables
    private int size;
    private BSTNode<E> root;

    // Constructors

    // Methods
    public int getSize() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }
    public BSTNode<E> root() { return this.root; }
    public BSTNode<E> parent(BSTNode<E> node) { return node.getParent(); }
    public ArrayList<BSTNode<E>> positions() { return iterable(root); }
    private ArrayList<BSTNode<E>> iterable(BSTNode<E> node) { // In Order Traversal (I hope)
        ArrayList<BSTNode<E>> retList = new ArrayList<BSTNode<E>>();

        if (!node.getLeftChild().isLeaf()) {
            for (BSTNode<E> leftNodes : iterable(node.getLeftChild()))
            retList.add(leftNodes);
        }

        retList.add(node);

        if (!node.getRightChild().isLeaf()) {
            for (BSTNode<E> rightNodes : iterable(node.getRightChild()))
                retList.add(rightNodes);
        }

        return retList;
    }
    public ArrayList<BSTNode<E>> children(BSTNode<E> node) { return node.children(); }
    public boolean isInternal(BSTNode<E> node) { return !node.isLeaf(); }
    public boolean isExternal(BSTNode<E> node) { return node.isLeaf(); }
    public boolean isRoot(BSTNode<E> node) { return node == this.root(); }
    public void insert(E element) {
        BSTNode<E> newNode = new BSTNode<E>(element, null);
        BSTNode<E> curNode = this.root;
        boolean failed = false;

        while (!curNode.isLeaf()) {
            if (curNode.compareTo(element) == 0) {
                failed = true;
                break;
            } else if (curNode.compareTo(element) == 1) {
                curNode = curNode.getLeftChild();
            } else {
                curNode = curNode.getRightChild();
            }
        }

        if (!failed) {
            BSTNode<E> parent = curNode.getParent();
            newNode.setParent(curNode);
            if (curNode == parent.getLeftChild()) {
                parent.setLeftChild(newNode);
            } else {
                parent.setRightChild(newNode);
            }
        }
    }
    public E delete(E element){ // out of time...
        BSTNode<E> curNode = this.root; 
        // find node
        while (curNode.getElement() != element) {
            if (curNode.isLeaf()) {
                return null;
            } else {
                if (curNode.compareTo(element) == 1) {
                    curNode = curNode.getLeftChild();
                } else {
                    curNode = curNode.getRightChild();
                }
            }
        }
        
        // find lowest child of node
        BSTNode<E> parent = curNode.getParent();
        if (parent.getLeftChild() == curNode) {
            BSTNode<E> bottomRChild = curNode.getRightChild();
            while (!bottomRChild.getRightChild().isLeaf()) {
                bottomRChild = bottomRChild.getRightChild();
            }
            if (bottomRChild.getLeftChild() == null) {
                bottomRChild.setLeftChild(curNode.getRightChild());
                bottomRChild.setParent(parent);
                parent.setLeftChild(bottomRChild);
            } else {
                // find left most child of curNode's parent's left child
                // attach left most child to curNode's sibling's left most child
            }
        }

        // replace curNode with lowest child

        return element;
    }
    public E search(E element) { // O(log n) implementation
        if (this.root == null) {
            return null;
        } else {
            if (search(element, this.root)) {
                return element;
            }
            return null;
        } 
    }
    private boolean search(E element, BSTNode<E> node) {
        if (element == node.getElement()) {
            return true;
        }

        if (!node.isLeaf()) {
            if (node.compareTo(element) == -1) {
                return search(element, node.getLeftChild());
            } else {
                return search(element, node.getRightChild());
            }
        }
        return false;
    }
    public BSTNode<E> left(BSTNode<E> node) { return node.getLeftChild(); }
    public BSTNode<E> right(BSTNode<E> node) { return node.getRightChild(); }
    public BSTNode<E> sibling(BSTNode<E> node) {
        BSTNode<E> parent = node.getParent();
        if (parent.getLeftChild() == node) {
            return parent.getRightChild();
        } else {
            return parent.getLeftChild();
        }
    }
}
