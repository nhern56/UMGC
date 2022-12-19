// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022

import java.util.ArrayList;

public class DirectedGraph extends Graph<Vertex> {

    public void addEdge(String Edge1, String Edge2) {

        // Check if th source node already has some connected edges
        ArrayList<Vertex> edgeList = adjacencyList.get(getVertex(Edge1));
        // if not in the Adjacency list create new Vertex and initialize
        if (edgeList == null) {
            edgeList = new ArrayList<>();
        }
        // add an edge to source to destination
        edgeList.add(getVertex(Edge2));

        // update the adjacency list
        adjacencyList.put(getVertex(Edge1), edgeList);

    }

    public Vertex getVertex(String Edge1) {

        // assigns a new vertex to the sourceEdge
        if (!vertices.containsKey(Edge1)) {
            vertices.put(Edge1, new Vertex(Edge1));
        }// returns the corresponding vertex
        return vertices.get(Edge1);
    }

}


