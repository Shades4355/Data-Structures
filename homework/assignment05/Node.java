// File name:   Node.java
// Written by:  Shades Meyers
// Description: A Node class for the Graph template
// Challenges:  
// Time Spent:  ~5 minutes
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-Aug-07  SM      File created

import java.util.ArrayList;

public class Node<E, T> {
    // variables
    E element;
    ArrayList<Edge<T, E>> edges = new ArrayList<Edge<T, E>>();

    // Constructors
    Node(E element) {
        this.element = element;
    }

    // Methods
    public ArrayList<Edge<T, E>> getEdges() { return this.edges; }
    public void addEdge( Edge<T,E> newEdge) { this.edges.add(newEdge); }
    public void removeEdge(Edge<T,E> edge) { this.edges.remove(edge); }
}
