package au.edu.uq.itee.comp3506.assn2.DataStructures;

public class CustomHashTable<T> {


    protected static int capacity = 1000;
    protected int size;
    protected HashEntry[] table;

    public CustomHashTable () {
        table = (HashEntry[]) new Object[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }

        size = 0;
    }

    public T get(T key) {

        for (int i=0; i < capacity; i++) {
            HashEntry entry = table[i];
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void put(T key, T value) {

        HashEntry entry = new HashEntry(key, value);
        if (size < capacity) {
            table[size+1] = entry;
            size++;
        } else {
            resize();
            put(key, value);
        }
    }

    private void resize() {
        HashEntry[] temp = (HashEntry[]) new Object[2*capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = table[i];
        }
        table = temp;
    }


    private class HashEntry {
        private T key;
        private T value;

        public HashEntry(T key, T value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}
