package org.example.Arrays;

import java.util.Comparator;

public interface CustomList<T extends Comparable<T>> extends  Iterable<T> {
    void add(int index,T element);
    void addAll(CustomList<? extends T> c);
    void clear();
    T get(int index);
    boolean isEmpty();
    void   remove(int index);
    void remove(Object o);
    int size();
    void sort(Comparator<? super  T> comparator);
}
