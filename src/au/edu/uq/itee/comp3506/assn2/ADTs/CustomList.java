package au.edu.uq.itee.comp3506.assn2.ADTs;

import java.util.Comparator;

/**
 * Interface for CustomList.
 *
 * @author Ali Nawaz Maan
 *
 */

public interface CustomList<T> {

    /**
     * Returns the size of the list i.e. how many elements are currently there.
     * @return Size of the list
     */
    int size();

    /**
     * Returns an element from the list at ith position.
     * @param i index position of the element.
     * @return Element T at index position i.
     * @throws IndexOutOfBoundsException
     */
    T get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the index of element T.
     * @param t element which index is to be found.
     * @return Index of the given element.
     */
    int get(T t);

    /**
     * Set an element at a given index position.
     * @param i index of the element to replace.
     * @param t item to be replaced.
     * @throws IndexOutOfBoundsException
     */
    void set(int i, T t) throws IndexOutOfBoundsException;

    /**
     * Add and item into the list
     * @param t item to be added to the list
     * @throws IndexOutOfBoundsException
     */
    void add(T t) throws IndexOutOfBoundsException;

    /**
     * Sort the list using given comparator
     * @param comparator for item comparison.
     */
    void sort(Comparator<T> comparator);

    /**
     * Searches the list for an item.
     * @param i item to be searched.
     * @param comparator for item comparison.
     * @return index of found item.
     */
    int search(T i, Comparator<T> comparator);
}
