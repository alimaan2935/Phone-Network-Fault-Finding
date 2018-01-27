package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;

import java.util.Comparator;

/**
 * Implementation of CustomList interface.
 * Stores any arbitrary elements into the list.
 *
 * @memoryEfficieny  O(n) (linear) where n is the number of elements stored in the list.
 * As the class uses primitive [] java array, its memory usage is equal to number of elements in the list.
 *
 * @author Ali Nawaz Maan
 *
 * @param <T>
 */
public class OwnList<T> implements CustomList<T> {

    // Initial capacity of the list
    protected int capacity = 100;
    // Size of the list
    protected int size = 0;
    // Array of elements stored
    protected T[] elements;

    /**
     * Initialize the list with default capacity
     * @runtimeEfficiency O(1) (Constant time) because initializing an array happens in constant time.
     */
    public OwnList() {
        elements = (T[]) new Object[capacity];
    }

    /**
     * Initialize the list with provided capacity
     * @runtimeEfficiency O(1) (Constant time) because initializing an array happens in constant time.
     * @param capacity of the list.
     */
    public OwnList(int capacity) {
        elements = (T[]) new Object[capacity];
        this.capacity = capacity;
    }


    /**
     * @runtimeEfficiency O(1) (Constant time) because it just returns the value of instance variable.
     * @return integer size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the element at index position
     * @runtimeEfficiency O(1) (Constant time) because initializing an array happens in constant time.
     * @param i index position of the element.
     * @return the element at the position i
     * @throws IndexOutOfBoundsException
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return elements[i];
    }

    /**
     * Returns the index of an item t.
     * @runtimeEfficiency O(n) (linear time) because every item is checked for equivalence.
     * @param t element which index is to be found.
     * @return the index of item t, -1 if item not found in the list
     */
    @Override
    public int get(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Replace an item at an index position
     * @runtimeEfficiency O(1) (Constant time) because accessing an item at an index position happens at constant time.
     * @param i index of the element to replace.
     * @param t item to be replaced.
     * @throws IndexOutOfBoundsException if index not in range
     */
    @Override
    public void set(int i, T t) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        elements[i] = t;
    }

    /**
     * Adds an item at the end of the list. Resizes the list if the lsit is full.
     * @runtimeEfficiency O(1) (Constant time) for adding the element at end of the list and
     * O(n) (Linear time) for resizing the list if it is full.
     * @param t item to be added to the list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(T t) throws IndexOutOfBoundsException {
        if (size == capacity) {
            resize();
        }
        elements[size] = t;
        size++;
    }

    /**
     * Checks if the index is in the range
     * @param i index
     * @param size size of the list
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    private void checkIndex(int i, int size) throws IndexOutOfBoundsException {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + i);
        }
    }

    /**
     * Creates a new list with double the capacity of current list and copies all elements to the new list.
     */
    private void resize() {
        T[] temp = (T[]) new Object[2*capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
        capacity *= 2;
    }

    /**
     * @param comparator for item comparison.
     */
    public void sort(Comparator<T> comparator) {

    }

    /**
     * @param i item to be searched.
     * @param comparator for item comparison.
     * @return
     */
    public int search(T i, Comparator<T> comparator) {
        return -1;
    }
}
