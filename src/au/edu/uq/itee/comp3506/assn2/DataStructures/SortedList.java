package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;

import java.util.Comparator;

public class SortedList<T> extends OwnList<T> {

    public SortedList() {
        super();
    }
    public SortedList(int capacity) {
        super(capacity);
    }

    @Override
    public void sort(Comparator<T> comparator) {
        quickSort(elements, comparator, 0, size-1);
    }

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

    @Override
    public int search(T target, Comparator<T> comparator) {
        return binarySearch(elements, target, 0, size-1, comparator);
    }

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
