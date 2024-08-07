// File name:   Graph.java
// Written by:  Shades Meyers
// Description: A Graph template
// Challenges:  
// Time Spent:  5 minutes
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-Aug-07  SM      File created


import java.util.ArrayList;

public class Graph {
    // adjacency list style undirected Graph
    // variables
    private ArrayList<Node> VertList = new ArrayList<Node>();

    // Constructors
    Graph() {
    }
    Graph(Node vert) {
        VertList.add(vert);
    }

    // Methods
    public int numVertices() { return VertList.size(); }
    // vertices()
    // numEdges()
    // edges()
    // getEdge(vert1, vert2)
    // endVertices(edge)
    // opposite(vert, edge)
    // insertVertex(element)
    // insertEdge(vert1, vert2, element)
    // removeVertex(vertex)
    // removeEdge(element)
}