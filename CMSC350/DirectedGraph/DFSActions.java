// Nicolas Hernandez
// CMSC 350
// Project 4
// 05/10/2022

public interface DFSActions<V> {

        //four method signatures that correspond to the four
        // actions performed in the depth first search
        void processVertex(V vertex);
        void descendV(V vertex);
        void ascendV(V vertex);
        void cycleDetected();

}

