// File name:   Edge.java
// Written by:  Shades Meyers
// Description: An Edge class for the Graph template
// Challenges:  
// Time Spent:  ~10 minutes
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-Aug-07  SM      File created


public class Edge<T, E> {
    // variables
    T element;
    Node<E, T> vert1;
    Node<E, T> vert2;
    String label;

    // Constructors
    Edge(T element) {
        this.vert1 = null;
        this.vert2 = null;
        this.element = element;
        this.label = "Unvisited";
    }
    Edge(Node<E, T> vert1, Node<E, T> vert2, T element) {
        this.vert1 = vert1;
        this.vert2 = vert2;
        this.element = element;
        this.label = "Unvisited";
    }

    // Methods
    public String getLabel() { return this.label; }
    public void setLabel(String newLabel) { this.label = newLabel; }
    public Node<E,T> getVert1() { return this.vert1; }
    public Node<E,T> getVert2() { return this.vert2; }
    public void setVert1(Node<E,T> vert1) { this.vert1 = vert1; }
    public void setVert2(Node<E,T> vert2) { this.vert2 = vert2; }
    public T getElement() { return this.element; }
}
