package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elemData;
    private int startPointer;
    private int endPointer;
    private int size;

    @SuppressWarnings("unchecked")
    public CyclicArrayDeque(){
        elemData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void pushFront(Item item) {
        grow();
        if (--startPointer == -1)
            startPointer = elemData.length-1;
        elemData[startPointer] = item;
        size++;

    }

    @Override
    public void pushBack(Item item) {
        grow();
        elemData[endPointer++] = item;
        size++;
        if (endPointer == elemData.length)
            endPointer = 0;
    }

    @Override
    public Item popFront() {
        if (size == 0)
            return null;
        shrink();
        Item tmp = elemData[startPointer];
        elemData[startPointer++] = null;
        size--;
        if (startPointer == elemData.length)
            startPointer = 0;
        return tmp;
    }

    @Override
    public Item popBack() {
        if (size == 0)
            return null;

        shrink();
        if (--endPointer == -1)
            endPointer = elemData.length-1;
        Item tmp = elemData[endPointer];
        elemData[endPointer] = null;
        size--;

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
        if (size == elemData.length) {
            changeCapacity(elemData.length + (elemData.length >> 1));
        }
    }

    private void shrink() {
        if (size <= elemData.length >> 2)
            if (elemData.length >> 2 < DEFAULT_CAPACITY)
                changeCapacity(DEFAULT_CAPACITY);
            else
                changeCapacity(elemData.length >> 2);
    }

    @SuppressWarnings("unchecked")
    private void changeCapacity(int newCapacity){
        int i = 0;
        Item[] newElementData = (Item[]) new Object[newCapacity];;
        while (i != size){
            newElementData[i] = elemData[startPointer++];
            if (startPointer == elemData.length)
                startPointer = 0;
            i++;
        }
        elemData = newElementData;
        startPointer = 0;
        endPointer = size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayDequeIterator();
    }

    private class CyclicArrayDequeIterator implements Iterator<Item> {

        private int currentPosition = startPointer;

        @Override
        public boolean hasNext() {
            return currentPosition != endPointer;
        }

        @Override
        public Item next() {
            Item item = elemData[currentPosition];
            currentPosition++;
            if (currentPosition == elemData.length)
                currentPosition = 0;
            return item;
        }

    }
}
