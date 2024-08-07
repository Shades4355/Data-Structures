// File name:   Graph.java
// Written by:  Shades Meyers
// Description: A Graph template
// Challenges:  Pseudo-code was hard for me to write and read back, so I did
//                  some actual coding.
// Time Spent:  56 minutes
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-Aug-07  SM      File created
//                      Created rudimentary Node and Edge classes;
//                          this allowed me to write basic code without
//                          annoying red underlines


import java.util.ArrayList;

public class Graph<E, T> {
    // adjacency list style undirected Graph

    // variables
    private ArrayList<Node<E, T>> VertList = new ArrayList<Node<E, T>>();
    private ArrayList<Edge<T,E>> edgeList = new ArrayList<Edge<T,E>>();

    // Constructors
    Graph() {
    }
    Graph(Node<E, T> vert) {
        VertList.add(vert);
    }

    // Methods
    public int numVertices() { return VertList.size(); }
    public ArrayList<Node<E, T>> vertices() { return VertList; }
    public int numEdges() { return edgeList.size(); }
    public ArrayList<Edge<T, E>> edges() { return edgeList; }
    public Edge<T, E> getEdge(Node<E,T> vert1, Node<E, T> vert2) {
        for (Edge<T,E> e : vert1.getEdges()) {
            for (Edge<T,E> f : vert2.getEdges())
            if (e.equals(f)) {
                return e;
            }
        }
        return null;
    }
    public ArrayList<Node<E,T>> endVertices(Edge<T,E> edge) {
        ArrayList<Node<E, T>> endPoints = new ArrayList<Node<E,T>>();
        endPoints.add(edge.getVert1());
        endPoints.add(edge.getVert2());
        return endPoints;
    }
    public Node<E, T> opposite(Node<E,T> vert, Edge<T,E> edge) {
        return (edge.getVert1().equals(vert)) ? edge.getVert2() : edge.getVert1();
    }
    public void insertVertex(E element) {
        VertList.add(new Node<E, T>(element));
    }
    public boolean insertEdge(Node<E,T> vert1, Node<E,T> vert2, T element) {
        if (this.getEdge(vert1, vert2) == null) {
            Edge<T,E> newEdge = new Edge<T,E>(element);
            vert1.addEdge(newEdge);
            vert2.addEdge(newEdge);
            newEdge.setVert1(vert1);
            newEdge.setVert2(vert2);
            edgeList.add(newEdge);

            return true;
        }
        return false; 
    }  
    public void removeVertex(Node<E,T> vertex) {
        for (Edge<T,E> edge : vertex.getEdges()) {
            this.removeEdge(edge);
        }
        VertList.remove(vertex);
    }
    public boolean removeEdge(T element) {
        Edge<T,E> toBeRemoved = this.search(element);

        if (toBeRemoved != null) {
            this.removeEdge(toBeRemoved);
            return true;
        }
        return false;
    }

    // bonus helper methods
    public Edge<T,E> search(T element) {
        // set all edges to "Unvisited"
       this.preExplore();

        // look for an Edge with "element"
        for (Node<E, T> i : VertList) {
            for (Edge<T, E> e : i.getEdges()) {
                if (e.getLabel().equals("Unvisited")) {
                    e.setLabel("Discovered");
                    if (e.getElement().equals(element)) {
                        return e;
                    }
                }
            }
        }
        return null;
    }
    public void preExplore() {
        // set all edges to "Unvisited"
        for (Edge<T, E> e : this.edgeList) {
            e.setLabel("Unvisited");
        }
    }
    private void removeEdge(Edge<T, E> edge) {
        edge.getVert1().removeEdge(edge); // calls Node's removeEdge() method
        edge.setVert1(null);

        edge.getVert2().removeEdge(edge); // calls Node's removeEdge() method
        edge.setVert2(null);

        edgeList.remove(edge);
    }
}