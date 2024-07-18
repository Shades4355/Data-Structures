
public class Node<E>{
    //Declare the element and "next"
    private E element;
    private Node<E> next;
    private Node<E> previous;

    //Constructor
    public Node(E e, Node<E> n, Node<E> p){
        element = e;
        next = n;
        previous = p;
    }
    
    //Getter
    public E getElement(){return element;}
    public E getElementProperly(Node<E> n){return element;}
    public Node<E> getNext() {return next;}
    public Node<E> getPrev() {return previous;}
 
    
    //Setter
    public void setNext(Node<E> n){
        next = n;
    }
    public void setPrev(Node<E> p){
        previous = p;
    }
 }