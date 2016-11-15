package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int beginPointer;
    private int endPointer;
    private int size;

    @SuppressWarnings("unchecked")
    public CyclicArrayQueue(){
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void enqueue(Item item) {

        grow();
        elementData[endPointer++] = item;
        size++;
        if (endPointer == elementData.length)
            endPointer = 0;
    }

    @Override
    public Item dequeue() {

        if (size == 0)
            return null;

        shrink();
        Item tmp = elementData[beginPointer];
        elementData[beginPointer++] = null;
        size--;
        if (beginPointer == elementData.length)
            beginPointer = 0;
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;

    }

    private void grow() {
        if (size == elementData.length) {
            changeCapacity(elementData.length + (elementData.length >> 1));
        }
    }

    private void shrink() {
        if (size <= elementData.length >> 2)
            if (elementData.length >> 2 < DEFAULT_CAPACITY)
                changeCapacity(DEFAULT_CAPACITY);
            else
                changeCapacity(elementData.length >> 2);
    }

    @SuppressWarnings("unchecked")
    private void changeCapacity(int newCapacity){
        int i = 0;
        Item[] newElementData = (Item[]) new Object[newCapacity];;

        while (i != size){
            newElementData[i] = elementData[beginPointer++];
            if (beginPointer == elementData.length)
                beginPointer = 0;
            i++;
        }

        elementData = newElementData;
        beginPointer = 0;
        endPointer = size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayQueueIterator();
    }

    private class CyclicArrayQueueIterator implements Iterator<Item> {

        private int currentPosition = beginPointer;

        @Override
        public boolean hasNext() {
            return currentPosition != endPointer;
        }

        @Override
        public Item next() {
            Item item = elementData[currentPosition];
            currentPosition++;
            if (currentPosition == elementData.length)
                currentPosition = 0;
            return item;
        }

    }
}
