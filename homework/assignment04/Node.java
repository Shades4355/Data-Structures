// File name: Node.java
// Written by: Shades Meyers
// Description: A Node a public Pairs as its element
// Challenges:
// Time Spent: 35 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-24 SM      File created
// 2024-July-25 SM      Added "next" method


import java.util.ArrayList;

public class Node<E, T> {
    // Variables
    private Pairs<E, T> element;
    private Node<E, T> parent, leftChild, rightChild;

    // Constructors
    Node(Pairs<E, T> element, Node<E, T> parent, Node<E, T> leftChild, Node<E, T> rightChild) {
        this.element = element;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    Node(Pairs<E, T> element, Node<E, T> parent) {
        this.element = element;
        this.parent = parent;
        this.leftChild = new Node<E, T>(null, this, null, null);
        this.rightChild = new Node<E, T>(null, this, null, null);
    }

    // Methods
    // Accessors & Mutators
    // Parent
    public Node<E, T> getParent() {
        return this.parent;
    }
    public void setParent(Node<E, T> newParent) {
        this.parent = newParent;
    }

    // Children
    public Node<E, T> getLeftChild() {
        return this.leftChild;
    }
    public void setLeftChild(Node<E, T> newChild) {
        this.leftChild = newChild;
    }
    public Node<E, T> getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(Node<E, T> newChild) {
        this.rightChild = newChild;
    }
    public ArrayList<Node<E, T>> getChildren() {
        ArrayList<Node<E, T>> childList = new ArrayList<Node<E, T>>();
        childList.add(this.leftChild);
        childList.add(this.rightChild);

        return childList;
    }

    // Element
    public Pairs<E, T> getElement() {
        return this.element;
    }

    public Pairs<E, T> setElement(Pairs<E, T> element) {
        Pairs<E, T> oldElement = this.element;
        this.element = element;

        return oldElement;
    }

    // Query Methods
    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }

    // Returns true if there is another node in in-order traversal sequence
    public boolean hasNext() {
        if (!this.isLeaf()) {
            boolean hasRightChild = !this.getRightChild().isLeaf(); // left child comes
            // before the parent, so we only need to check for the
            // presence of the right child

            // If node is a left child, then Parent is next
            boolean isLeft = false;
            if (!this.isRoot()) {
                ArrayList<Node<E, T>> siblings = this.getParent().getChildren();

                // check if self is left sibling
                isLeft = siblings.indexOf(this) == 0;
            }

            // Is parent a left node?
            boolean isParentLeft = false;
            if (!this.isRoot()) {
                Node<E, T> parent = this.getParent();
                isParentLeft = parent.getParent().getChildren().indexOf(parent) == 0;
            }

            return hasRightChild || isLeft || isParentLeft;
        } else {
            return false;
        }
    }
    public Node<E, T> next() {
        if (!this.getRightChild().isLeaf()) {
            return this.getRightChild();
        }
        
        if (!this.isRoot()) {
            if (this.getParent().getChildren().indexOf(this) == 0) {
                return this.getParent();
            }

            Node<E, T> parent = this.getParent();
            if (parent.getParent().getChildren().indexOf(parent) == 0) {
                return parent.getParent();
            }
        } 
        
        return null;
    }

    public E getKey() {
        return this.element.getKey();
    }

    // Overrides
    // To String
    @Override
    public String toString() {
        return new String(new StringBuilder().append(this.element));
    }

    // Equals
    public boolean equals(Node<E, T> val2) {
        Node<E, T> val1 = this;

        return val1.getElement() == val2.getElement();
    }

} // end Node