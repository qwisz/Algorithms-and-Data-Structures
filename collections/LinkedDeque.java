package seminar1.collections;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedDeque<Item> implements IDeque<Item> {

    private LinkedList<Item> deq;
    private int size;

    public LinkedDeque(){
        deq = new LinkedList<Item>();
    }

    @Override
    public void pushFront(Item item) {
        deq.addFirst(item);
        size++;
    }

    @Override
    public void pushBack(Item item) {
        deq.addLast(item);
        size++;
    }

    @Override
    public Item popFront() {
        return deq.getFirst();
    }

    @Override
    public Item popBack() {
        return deq.getLast();
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
        return deq.iterator();
    }
}
