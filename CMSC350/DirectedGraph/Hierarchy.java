// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy implements DFSActions<Vertex> {

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

        int size = 0;

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
            if(c.equals("("))
                size++;
            else if(c.equals(")"))
                --size;
            if(c.equals("(") || c.equals(")"))
                continue;
            if(!c.equals("*"))
                ans.append("\n");
            ans.append("\t".repeat(Math.max(0, size)));
            ans.append(c).append(" ");
        }
        ans.append("\n");
        return ans.toString();
    }

}
