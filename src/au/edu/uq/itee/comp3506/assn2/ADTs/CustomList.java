package au.edu.uq.itee.comp3506.assn2.ADTs;

import java.util.Comparator;

public interface CustomList<T> {

    int size();

    T get(int i) throws IndexOutOfBoundsException;

    int get(T t);

    void set(int i, T t) throws IndexOutOfBoundsException;

    void add(T t) throws IndexOutOfBoundsException;

    T remove(int i) throws IndexOutOfBoundsException;

    T remove(T t);

    void sort(Comparator<T> comparator);

    int search(T i, Comparator<T> comparator);
}
