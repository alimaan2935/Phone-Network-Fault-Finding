package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;

import java.util.Comparator;

public class OwnList<T> implements CustomList<T> {

    protected static int capacity = 1000;
    protected int size = 0;
    protected T[] elements;

    public OwnList() {
        elements = (T[]) new Object[capacity];
    }

    public OwnList(int capacity) {
        elements = (T[]) new Object[capacity];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    @Override
    public int get(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void set(int i, T t) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        elements[i] = t;
    }

    @Override
    public void add(T t) throws IndexOutOfBoundsException {
        if (size == capacity) {
            resize();
        }
        elements[size] = t;
        size++;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public T remove(T t) {
        return null;
    }

    private void checkIndex(int i, int size) throws IndexOutOfBoundsException {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + i);
        }
    }

    private void resize() {
        T[] temp = (T[]) new Object[2*capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    public void sort(Comparator<T> comparator) {

    }

    public int search(T i, Comparator<T> comparator) {
        return -1;
    }
}
