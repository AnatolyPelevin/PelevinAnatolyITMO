package utils;

import java.util.Comparator;

public class ComparableComparator<T extends Comparable<? super T>> implements Comparator<T> {
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
