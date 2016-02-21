import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Vincent on 2/8/2016.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] innerArray;
    private int count;
    private int tail;

    public RandomizedQueue() {
        innerArray = new Object[1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        ensureCapacity();
        innerArray[tail++] = item;
        count++;
    }

    private void resizeAndCopy(int newSize) {
        Object[] copy = new Object[newSize];
        int j = 0;
        for (int i = 0; i < innerArray.length; ++i) {
            if (innerArray[i] != null) {
                copy[j] = innerArray[i];
                j++;
            }
        }
        tail = j;
        innerArray = copy;
    }

    private void ensureCapacity() {

        if (innerArray.length == tail) {
            resizeAndCopy(2 * innerArray.length);
        } else if (innerArray.length / 4 == count && count > 0) {
            resizeAndCopy(innerArray.length / 2);
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int index = getRandomIndex();
        Item value = (Item) innerArray[index];
        innerArray[index] = null;
        count--;
        tail--;

        return value;
    }           // remove and return a random item


    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        int index = getRandomIndex();
        return (Item) innerArray[index];
    }

    private int getRandomIndex() {
        Item item = null;
        int index = -1;
        while (item == null) {

            index = StdRandom.uniform(tail);
            item = (Item) innerArray[index];
        }
        return index;
    }


    public Iterator<Item> iterator() {
        return new RandomIterator(innerArray);
    }

    private class RandomIterator implements Iterator<Item> {

        private RandomizedQueue<Item> copy = new RandomizedQueue<>();

        public RandomIterator(Object[] items) {

            for (Object i : items) {
                if (i != null) {
                    copy.enqueue((Item) i);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Item next() {
            return copy.dequeue();
        }
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        rq.enqueue(1);
        rq.dequeue();

        rq.enqueue(2);
        rq.enqueue(3);
        rq.dequeue();

        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);

        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();

        for (Integer i : rq) {
            for (Integer j : rq) {

                StdOut.println(i);
                StdOut.println(j);
            }
        }
    }
}
