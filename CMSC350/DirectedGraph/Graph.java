// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022


import java.util.*;
import java.util.ArrayList;

public class Graph<V> {

    // Starting node of the graph
    public V startingNode = null;
    // Map all the vertex to a corresponding Vertex
    Map<String, V> vertices = new HashMap<>();
    // Adjacency list of vertices
    Map<V, ArrayList<V>> adjacencyList = new HashMap<>();
    // Track if a node/vertex is visited
    Set<V> visited = new HashSet<>();
    ParenthesizedList hierarchy = new ParenthesizedList();
    Hierarchy parenthesizedList = new Hierarchy();
    // keep track if the graph contain a circle
    boolean cycle;
    //
    Set<V> discovered = new HashSet<>();

    public void depthFirstSearch() {

        // defining cycle flag as false
            cycle = false;
        // Starting DFS from the first node of the input data
            dfs(startingNode);
    }

    private void dfs(V node) {

        // if the node is already visited a cycle has been detected
        if (discovered.contains(node)) {
            cycle = true;
            // DFS Actions CycleDetected
            hierarchy.cycleDetected();
            parenthesizedList.cycleDetected();
            return;
        }

        // DFS Actions Process Vertex
        hierarchy.processVertex((Vertex) node);
        parenthesizedList.processVertex((Vertex) node);
        // DFS Actions DescendV
        hierarchy.descendV((Vertex) node);
        parenthesizedList.descendV((Vertex) node);
        // add the node to discovered
        discovered.add(node);
        // mark the node as visited
        visited.add(node);
        // discover all child
        ArrayList<V> list = adjacencyList.get(node);
        if (list != null) {
            for (V u : list)
                dfs(u);
        }
        // DFSActions AscendV
        hierarchy.ascendV((Vertex) node);
        parenthesizedList.ascendV((Vertex) node);
        // remove discovered node from discovered
        discovered.remove(node);
    }

    public void displayUnreachableClasses(){

        // Loop all over the adjacency list
        // for each entry check if there is any unreachable classes
        for (Map.Entry<V, ArrayList<V>> entry : adjacencyList.entrySet()) {
            // if one print it and mark it as visited
            if(entry.getValue().size()>0){
                // check the node
                if(!visited.contains(entry.getKey())){
                    System.out.println(entry.getKey() + " is unreachable");
                    visited.add(entry.getKey());
                }
                // check adjacent nodes
                for (V vertex : entry.getValue()){
                    if(!visited.contains(vertex)){
                        System.out.println(vertex + " is unreachable");
                        visited.add(vertex);
                    }
                }
            }
        }
    }

}

