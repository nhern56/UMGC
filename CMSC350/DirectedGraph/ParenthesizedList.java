// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022

import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList implements DFSActions<Vertex> {

    // This class maintain a Queue to trace orders of the nodes and their dependent nodes with proper spacing format

    Queue<String> classList = new LinkedList<>();

    @Override
    public void processVertex(Vertex vertex) {
        classList.add(vertex.toString());
    }
    @Override
    public void descendV(Vertex vertex) {
        classList.add("(");
    }
    @Override
    public void ascendV(Vertex vertex) {
        classList.add(")");
    }
    @Override
    public void cycleDetected() {
        classList.add("*");
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("( ");
        while (classList.size() > 0) {
            String c = classList.peek();
            classList.remove();

            if (c.equals("(")) {
                if (")".equals(classList.peek())) {
                    classList.remove();
                    continue;
                } else if ("*".equals(classList.peek())) {
                    ans.append(classList.peek()).append(" ");
                    classList.remove();
                    classList.remove();
                    continue;
                }
            }
            ans.append(c).append(" ");
        }
        ans.append(")\n");
        return ans.toString();
    }

}

