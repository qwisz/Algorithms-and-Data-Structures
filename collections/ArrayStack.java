package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<Item> implements IStack<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(Item item) {
        /* TODO: implement it */
        grow();
        elementData[size++] = item;
    }

    @Override
    public Item pop() {
        /* TODO: implement it */
        Item tmp = elementData[--size];
        shrink();
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
        /**
         * TODO: implement it
         * Если массив заполнился,
         * то увеличить его размер в полтора раз
         */
        if (size == elementData.length)
            changeCapacity((int)(elementData.length * 1.5));
    }

    private void shrink() {
        /**
         * TODO: implement it
         * Если количество элементов в четыре раза меньше,
         * то уменьшить его размер в два раза
         */
        if ((elementData.length / size) >= 4) {
            if (elementData.length / 4 < DEFAULT_CAPACITY)
                changeCapacity(DEFAULT_CAPACITY);
            else
                changeCapacity(elementData.length / 4);
        }
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }

    }

}
