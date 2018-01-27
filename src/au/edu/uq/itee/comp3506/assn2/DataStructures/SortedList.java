package au.edu.uq.itee.comp3506.assn2.DataStructures;

import java.util.Comparator;

/**
 * Extension of "OwnList". Specializes in sorting and performing binary search on the items.
 *
 * @memoryEfficieny  O(n) (linear) where n is the number of elements stored in the list.
 * As the class uses primitive [] java array, its memory usage is equal to number of elements in the list.
 *
 * @author Ali Nawaz Maan
 *
 * @param <T>
 */
public class SortedList<T> extends OwnList<T> {

    /**
     * Initialize the list with default capacity
     * @runtimeEfficiency O(1) (Constant time) because initializing an array happens in constant time.
     */
    public SortedList() {
        super();
    }
    /**
     * Initialize the list with provided capacity
     * @runtimeEfficiency O(1) (Constant time) because initializing an array happens in constant time.
     */
    public SortedList(int capacity) {
        super(capacity);
    }

    /**
     * Sort the list with given comparator.
     * @runtimeEfficiency O(nlogn) because each partitioning takes O(n) and each partitioning splits the array into 2 which is O(logn).
     * @param comparator for item comparison.
     */
    @Override
    public void sort(Comparator<T> comparator) {
        quickSort(elements, comparator, 0, size-1);
    }

    /**
     * Implementation of quick sort algorithm
     * (Inspired by the book "Data Structures & Algorithms in Java". See readme file for complete reference)
     * @param elements array of items
     * @param comparator for item comparison
     * @param a index a
     * @param b index b
     */
    private void quickSort(T[] elements, Comparator<T> comparator, int a, int b) {

        if (a>=b) return;

        int left = a;
        int right = b-1;
        T pivot = elements[b];
        T temp;
        while (left<=right) {
            while (left<=right && comparator.compare(elements[left], pivot) < 0) {
                left++;
            }
            while (left<=right && comparator.compare(elements[right], pivot) > 0) {
                right--;
            }

            if (left<= right) {
                temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }

        temp = elements[left];
        elements[left] = elements[b];
        elements[b] = temp;

        quickSort(elements, comparator, a, left-1);
        quickSort(elements, comparator, left+1, b);

    }

    /**
     * Performs binary search on a sorted list recursively.
     * @runtimeEfficiency O(logn) (Logarithmic time) because the search space halves on every recursive call.
     * @param target object to be searched
     * @param comparator for item comparison.
     * @return the index of the found item, -1 if item not in the list
     */
    @Override
    public int search(T target, Comparator<T> comparator) {
        return binarySearch(elements, target, 0, size-1, comparator);
    }

    /**
     * Binary search implementation
     * (Inspired by the book "Data Structures & Algorithms in Java". See readme file for complete reference)
     * @param elements array of elements
     * @param target item to be searched
     * @param low low index
     * @param high high index
     * @param comparator for item comparison
     * @return the index of searched item
     */
    private int binarySearch(T[] elements, T target, int low, int high, Comparator<T> comparator) {
        if (low>high) {
            return -1;
        }else {
            int mid = (low+high)/2;

            if (target.equals(elements[mid])) {
                return mid;
            } else if (comparator.compare(target, elements[mid]) < 0) {
                return binarySearch(elements, target, low, mid-1, comparator);
            }else {
                return binarySearch(elements, target, mid+1, high, comparator);
            }

        }
    }
}
