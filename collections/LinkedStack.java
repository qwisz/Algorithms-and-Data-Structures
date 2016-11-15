package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size = 0;

    @Override
    public void push(Item item) {
        /* TODO: implement it */
        head = new Node<Item>(item, head);
        size++;
    }

    @Override
    public Item pop() {
        /* TODO: implement it */
        if (size == 0)
            return null;
        Item tmp = head.item;
        head = head.next;
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

    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {

        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            /* TODO: implement it */
            return currentPosition !=0;
        }

        @Override
        public Item next() {
            /* TODO: implement it */
            Item t = head.item;
            head = head.next;
            currentPosition--;
            return t;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
