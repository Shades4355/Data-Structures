
public class DoublyLL<E> {
    Node<E> head = new Node<> (null, null, null);
    Node<E> tail = new Node<> (null, null, null);
    
    private int size = 0;
    
    public int getSize(){
        return size;
    }
    
    public DoublyLL() {
        head.setNext(tail);
        tail.setPrev(head);
    }
    
    public Node<E> header(){
        return head;
    }
    
    public Node<E> trailer(){
        return tail;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public E first(){
        if(isEmpty()){
         return null;
        }
        return head.getNext().getElement();   
    }
    
    public E last(){
        if(isEmpty()){
        return null;
        }
        return tail.getPrev().getElement();    
    }


//This part is a bit confusing but I had built the node constructor to have three parameters of element, next and previous.
//I then unknowingly built addFirst with what should have been the logic for in between. I then figured eh, i already wrote it and it works so might as well roll with it. 
    public void addFirst(E e){
        addBetween(e, head, head.getNext());
    }
    
    public void addLast (E e){
        addBetween(e, tail.getPrev(), tail);
    }
    
    public E removeFirst(){
        if(isEmpty()){
            return null;
        }
        return remove(head.getNext());
    }
    
    public E removeLast(){
        if(isEmpty()){
            return null;
        }
        return remove(tail.getPrev());
    }
    
    public void addBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> n = new Node<>(e, successor, predecessor);
        predecessor.setNext(n);
        successor.setPrev(n);
        size++;
        }
    
    public E remove(Node<E> n){
        Node<E> predecessor = n.getPrev();
        Node<E> successor = n.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return n.getElement();
        }    
}
    
    


