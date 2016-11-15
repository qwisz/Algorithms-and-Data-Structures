package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        if (size == 0) {
            tail = new Node<Item>(item);
            head = tail;
        } else
            tail = new Node<Item>(item, tail);

        size++;
    }

    @Override
    public Item dequeue() {
        if (size == 0)
            return null;

        Node<Item> tmp = tail;

        while (tmp.next != head && tmp != head)
            tmp = tmp.next;

        Item item = head.item;
        tmp.next = null;
        head = tmp;
        size--;
        return item;
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
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

        Node<Item> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Node<Item> tmp = tail;
            while (tmp.next != current && tmp != current)
                tmp = tmp.next;
            Item item = current.item;
            if (current == tmp)
                current = null;
            else
                current = tmp;
            return item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}