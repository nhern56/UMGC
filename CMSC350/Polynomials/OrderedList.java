//Nicolas Hernandez
//Project 2
//CMSC 350

import java.util.List;

public class OrderedList {

    // true or false if list is sorted
    public static <T extends Comparable<? super T> > boolean checkSorted(List<T> list) {
        boolean isSorted = true;
        for (int i = list.size()-1; i > 0 ; i--) {
            T current = list.get(i);
            if (!checkSorted(list, current)) { isSorted = false; }
        }
        return isSorted;
    }

    //index list
    private static <T extends Comparable<? super T> > boolean checkSorted(List<T> list, T current) {
        T curValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) { return curValue.compareTo(nextValue) >= 0; }

        return true;
    }

}