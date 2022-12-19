// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static DirectedGraph graph = new DirectedGraph();

    public void graph() {

        // Take input using JFileChooser
        // Create Directed graph from the input file
        JFileChooser file = new JFileChooser(new File("."));
        int userFile = file.showOpenDialog(null);
        if (userFile == JFileChooser.APPROVE_OPTION) {
            try {
                // user input
                Scanner input = new Scanner(file.getSelectedFile());
                // Read the file line by line
                while (input.hasNextLine()) {
                    String edgeString = input.nextLine();
                    String[] edge = edgeString.split(" ");
                    // first node of the graph
                    if (graph.startingNode == null)
                        graph.startingNode = graph.getVertex(edge[0]);
                    // add edges to graph
                    // List nodes in order
                    for (int i = 1; i < edge.length; i++) {
                        graph.addEdge(edge[0], edge[i]);
                        // "edge[0] + "\t" + edge[i]"
                    }
                }
            // if file is not found
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // Initializing Main Class
        new Main().graph();
        // Depth First Search
        graph.depthFirstSearch();
        // Display Parenthesized List
        System.out.println(graph.parenthesizedList.toString());
        // Display Hierarchy
        System.out.println(graph.hierarchy.toString());
        // Display unreachable classes
        graph.displayUnreachableClasses();
    }

}

