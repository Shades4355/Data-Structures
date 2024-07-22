

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.Objects;

public class Tree<E> {
    private class Node<E> {
        
        // Variables
        Node<E> parent;
        ArrayList<Node<E>> children;
        E element;

        // Constructors
        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<Node<E>>();
        }

        // Methods
        // Accessors & Mutators
        // Element
        public void setElement(E element) {
            this.element = element;
        }
        public E getElement() {
            return this.element;
        }
        // Parent
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
        public Node<E> getParent() {
            return this.parent;
        }
        // Children
        public ArrayList<Node<E>> getChildren() {
            return this.children;
        }
        // Has next
        public boolean hasNext() {
            // Returns true if node has children
            // Returns true if node has siblings
            // Returns false otherwise
            if (this.isInternal()) {
                return true;
            } else {
                ArrayList<Node<E>> siblings = this.getParent().getChildren();
                return siblings.indexOf(this) < siblings.size() -1;
            }
        }

        // Query Methods
        public boolean isRoot() {
            return parent == null;
        }
        public boolean isInternal() {
            return children.size() > 0;
        }
        public boolean isExternal() {
            return children.size() == 0;
        }

        // To String
        @Override
        public String toString() {
            return new String(new StringBuilder().append(this.getElement()));
        }
    }

    // Variables
    Node<E> root;
    ArrayList<Node<E>> data = new ArrayList<Node<E>>();
    int size;

    // Constructors
    Tree(E element) {
        this.root = new Node<E>(element, null);
        this.data.add(root);
        this.size = 1;
    }
    Tree() {
        this.size = 0;
        this.root = null;
    }

    // Generic Methods
    // Size
    public int size() { return this.size;}
    // Is Empty
    public boolean isEmpty() { return this.size == 0; }
    // Iterator
    private ArrayList<Node<E>> iterator() {
        ArrayList<Node<E>> list = new ArrayList<Node<E>>();
        if (root.getChildren().size() > 0) { // if node has children...
            list.add(nextNode(root)); // returns children, then self
        } else { // has no children; return self
            list.add(root);
        }
        return list;
    }
    private Node<E> nextNode(Node<E> curNode) {
        if (curNode.getChildren().size() > 0) { // if node has children...
            for (Node<E> child : curNode.getChildren()) {
                nextNode(child);
            }
            return curNode;
        } else { // if node is external...
            return curNode;
        }
        
    }
    private Node<E> next(Node<E> node) {
        ArrayList<Node<E>> list = this.iterator();

        if (list.indexOf(node) > list.size() - 1) {
            return list.get(list.indexOf(node) + 1); 
        } else {
            return null;
        }
    }
    public void forEach(Consumer <? super E> action) {
        try {
            Objects.requireNonNull(action);
            Node<E> currentNode = this.root;

            if (this.size > 0) {
                while (currentNode.hasNext()) {
                    action.accept(currentNode.getElement());
                    currentNode = this.next(currentNode);
                }
                action.accept(currentNode.getElement());
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }
    // Positions
    public ArrayList<Object> positions() {
        // not sure what you're looking for here, so I enumerated the nodes?
        ArrayList<Node<E>> list = this.iterator();
        ArrayList<Object> pos = new ArrayList<Object>();
        int n = 0;
        for (Node<E> node : list) {
            pos.add(new Object() {int position = n; Node<E> curNode = node;});
        } 
        return pos;
    }

    // Accessor Methods
    // Root
    public E root() { return this.root.getElement(); }
    // Parent
    public Node<E> parent(Node<E> node) { return node.getParent(); }
    // Children
    public ArrayList<Node<E>> children(Node<E> node) {
        return node.getChildren();
    }
    // Number of Children
    public int numChildren(Node<E> node) { return node.getChildren().size(); }

    // Query methods
    // Is Internal
    public boolean isInternal(Node<E> node) { return node.isInternal(); }
    // Is External
    public boolean isExternal(Node<E> node) { return node.isExternal(); }
    // Is Root
    public boolean isRoot(Node<E> node) { return node.isRoot(); }
}
