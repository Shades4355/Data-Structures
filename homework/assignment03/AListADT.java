package homework.assignment03;

// Program Description:  The program will implement a structure similar to Java's ArrayList,  
// but using a Node based Doubly Linked List.
// File name: AListADT.java (Array List Abstract Data Type)
// File description: This is a Java Interface that provides the ADT to be used to implement the DLLAList class.
//      This interface uses generics.  
//      AList.java should implement this interface, using generics as well.
// Files in this program:  
//      To be written by student: AList.java
//      Provided by previous work:    DLL.java, DLLADT.java, DLLNode.java
//		Provided by professor: AListADT.java, AListTesterPerson.java, AListTesterInteger.java, Person.java
//      Note: DO NOT MODIFY the provided files 
// Revision History:
// Date:                   By:               Action:
// ---------------------------------------------------
// 3/1/17               mb          created
// 6/18/10 				jk			updated

// Constructor:  AList(): creates an empty list 
public interface  AListADT <E> extends DLLADT <E>{
    
// Returns the number of elements in this list.
public    int size();  
    
// Returns true if this list contains no elements.
public    boolean isEmpty(); 
    
// Appends the specified element to the end of this list. (at the tail end.)
// Returns: true
public    boolean add(E element);
    
// Inserts the specified element at the specified position in this list.  
public    void add(int index, E element) throws IndexOutOfBoundsException;
         
// Returns the element at the specified position in this list.
public    E get(int index) throws IndexOutOfBoundsException;
    
// Replaces the element at the specified position in this list with the specified element.
// Returns:  the element previously at the specified position (i.e. the old element that was replaced.)
public    E set(int index, E element) throws IndexOutOfBoundsException;

// Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
public    int indexOf(E element);

// Returns true if this list contains the specified element, false otherwise   
public    boolean contains(E element);   

// Removes the element at the specified position in this list.
// Returns:  the element that was removed from the list
public    E remove(int index) throws IndexOutOfBoundsException;
    
 // Removes the first occurrence of the specified element from this list, if it is present.   
 // Returns: true if this list contained the specified element, false otherwise
public    boolean remove(E e);
   //boolean	remove(Object o);
    
  // Returns a string containing all the elements of the list that matches the output format of the spec
public String toString() ; 

// AList.java must also contain the following "worker" method that is used by several of the methods above.
// Find non-sentinel node at 0 based position (i.e. first non-sentinel node is at index 0)
//  private DLLNode<E> findNode(int index) throws IndexOutOfBoundsException;
   
}