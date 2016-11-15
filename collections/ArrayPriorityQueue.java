package seminar1.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private static final int DEFAULT_CAPACITY = 16;

    private Key[] elemData;
    private Comparator<Key> comparator;
    private int size;
    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        this.elemData = (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = new Comparator<Key>() {
            @Override
            public int compare(Key o1, Key o2) {
                return o1.hashCode() > o2.hashCode() ? 1 : (o1.hashCode() == o2.hashCode() ? 0 : -1);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<Key> comparator) {
        this.elemData = (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        grow();
        size++;
        elemData[size-1] = key;
        siftUp(size-1);
    }

    @Override
    public Key peek() {
        return elemData[0];
    }

    @Override
    public Key extractMin() {
        Key min = elemData[0];
        elemData[0] = elemData[size-1];
        size--;
        siftDown(0);
        shrink();
        return min;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp(int ind) {
        while ( greater(ind, (ind - 1) / 2) ) {
            swap(ind, (ind - 1) / 2);
            ind = (ind - 1) / 2;
        }
    }

    private void siftDown(int ind) {
        while (2 * ind + 1 < size) {
            int left = 2 * ind + 1;
            int right = 2 * ind + 2;
            int j = left;
            if (right < size && greater(right, left)) {
                j = right;
            }
            if ( greater(ind, j) ) {
                break;
            }
            swap(ind, j);
            ind = j;
        }
    }

    private void swap(int x, int y) {
        Key tmp = elemData[x];
        elemData[x] = elemData[y];
        elemData[y] = tmp;
    }

    private void grow() {
        if (size == elemData.length) {
            changeCapacity(elemData.length << 1);
        }
    }

    private void shrink() {
        if (size <= elemData.length >> 2)
            if (elemData.length >> 2 < DEFAULT_CAPACITY)
                changeCapacity(DEFAULT_CAPACITY);
            else
                changeCapacity(elemData.length >> 2);
    }

    private void changeCapacity(int newCapacity) {
        elemData = Arrays.copyOf(elemData, newCapacity);
    }

    private boolean greater(int i, int j) {
        return comparator == null
                ? elemData[i].compareTo(elemData[j]) < 0
                : comparator.compare(elemData[i], elemData[j]) < 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ArrayPriorityQueueIterator();
    }

    private class ArrayPriorityQueueIterator implements Iterator<Key> {

        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition != size;
        }

        @Override
        public Key next() {
            return elemData[currentPosition++];
        }

    }
}