

import java.util.Objects;
import java.util.function.Consumer;

public class DLL<E> {
    // variables
    private int size;
    private DLLNode<E> header;
    private DLLNode<E> trailer;

    // Constructors
    DLL(DLLNode<E> node) {
        this.size = 1;
        this.header = node;
        this.trailer = node;
    }
    DLL() {
        this.size = 0;
        this.header = null;
        this.trailer = null;
    }

    // Accessors & Mutators
    // Size
    public int size() {
        return this.size;
    }

    // is empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Insertions
    public void add(DLLNode<E> node) {
        if (this.header == null) {
            this.header = node;
        }

        DLLNode<E> currentNode = this.trailer;
        currentNode.setNextNode(node);
        this.trailer = node;
        this.size += 1;
    }
    public void addLast(DLLNode<E> node) {
        this.add(node);
    }
    public void addFirst(DLLNode<E> node) {
        node.setNextNode(this.header);
        this.header = node;

        if (this.trailer == null) {
            this.trailer = node;
        }

        this.size += 1;
    }

    // Get
    public E get(int index) {
        DLLNode<E> currentNode = this.header;
        if (index < this.size && index >= 0) {
            for (int i = 1; i <= index; i++) {
                currentNode = currentNode.getNextNode();
            }
        } else {
            return null;
        }
        return currentNode.getElement();
    }
    public E first() { return this.header.getElement(); }
    public E last() { return this.trailer.getElement(); }

    // add between
    public void addBetween(E element, DLLNode<E> before, DLLNode<E> after) {
        DLLNode<E> newNode = new DLLNode<E>(element);
        before.setNextNode(newNode);
        after.setPrevNode(newNode);

        newNode.setPrevNode(before);
        newNode.setNextNode(after);
    }

    // removal
    public E removeFirst() {
        DLLNode<E> toBeRemoved = this.header;
        if (toBeRemoved.getNextNode() != null) {
            this.header = toBeRemoved.getNextNode();
            size -= 1;
        } else {
            this.header = null;
            this.size -= 1;
        }

        return toBeRemoved.getElement();
    }

    public E removeLast() {
        DLLNode<E> oldTrailer = this.trailer;
        DLLNode<E> node = this.trailer.getPrevNode();

        node.setNextNode(null);
        this.trailer = node;
        this.size -= 1;

        return oldTrailer.getElement();
    }

    public E remove(DLLNode<E> node) {
        node.getPrevNode().setNextNode(node.getNextNode());
        node.getNextNode().setPrevNode(node.getPrevNode());

        return node.getElement();
    }

    // iterable
    public void forEach(Consumer<? super E> action) {
        try{
            Objects.requireNonNull(action);
            DLLNode<E> currentNode = this.header;

            if (this.size > 0) {
                while (currentNode.hasNext()) {
                    // ".accept()" not working with DLLNode class
                    // but works with ".getElement()"
                    action.accept(currentNode.getElement());
                    currentNode = currentNode.getNextNode();
                }
                action.accept(currentNode.getElement());
            } else {
                throw new ArrayIndexOutOfBoundsException("List is empty");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    // commented out due to below comment
    // public DLLNode<E>[] iterator2() {
    //     if (this.isEmpty()) {
    //         return null;
    //     } else {
    //         // Because of "new DLLNode[this.size]", compiler needs
    //         // to be run (on Mac, at least) as
    //         // javac Driver.java -Xlint:unchecked
    //         DLLNode<E>[] iterableList = new DLLNode[this.size];

    //         DLLNode<E> currentNode = this.header;
    //         iterableList[0] = this.header;

    //         for (int i = 1; i < this.size; i++) {
    //             currentNode = currentNode.getNextNode();
    //             iterableList[i] = currentNode;
    //         }

    //         return iterableList;
    //     }
    // }

    // public void forEach2(Consumer<? super E> action) {
    //     Objects.requireNonNull(action);
    //     DLLNode<E>[] iterableList = this.iterator2();

    //     if (this.size > 0) {
    //         for (int i = 0; i < this.size; i++) {
    //             action.accept(iterableList[i].getElement());
    //         }
    //     } else {
    //     throw new ArrayIndexOutOfBoundsException("List is empty");
    //     }
    // }

    // written with SI's help (90% SI; 10% me)
    public Object iterator() {
        return new Object() {
            DLLNode<E> currentDllNode = header;

            public boolean hasNext() { return currentDllNode != null; }

            public E next() {
                E data = currentDllNode.getElement();
                currentDllNode = currentDllNode.getNextNode();
                return data;
            }
        };
    }
} // end program
