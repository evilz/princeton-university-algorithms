//import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {


    private class InnerNode<Item> {
        private InnerNode<Item> prev;
        private InnerNode<Item> next;
        private Item value;

        public InnerNode(Item value) {
            this.value = value;
        }

        private void cleanRef() {
            prev = null;
            next = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private InnerNode<Item> current;

        DequeIterator(Deque<Item> deque) {
            current = deque.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item returnVal = current.value;
            current = current.next;
            return returnVal;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private InnerNode<Item> first;
    private InnerNode<Item> last;
    private int count;

    public Deque() {
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        count++;
        InnerNode<Item> oldFirst = this.first;
        this.first = new InnerNode<>(item);
        this.first.next = oldFirst;

        if (oldFirst != null) {
            oldFirst.prev = this.first;
        }

        if (last == null) {
            last = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        count++;
        InnerNode<Item> newLast = new InnerNode<>(item);

        if (this.last != null) {
            newLast.prev = this.last;
            this.last.next = newLast;
        }

        this.last = newLast;

        if (first == null) {
            first = last;
        }
    }

    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();

        count--;
        InnerNode<Item> oldFirst = first;
        first = oldFirst.next;
        if (first != null) {
            first.prev = null;
        }
        oldFirst.cleanRef();

        if (count <= 1) {
            last = first;
        }

        return oldFirst.value;
    }

    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();

        count--;
        InnerNode<Item> oldLast = last;
        last = oldLast.prev;
        if (last != null) {
            last.next = null;
        }

        oldLast.cleanRef();

        if (count <= 1) {
            first = last;
        }

        return oldLast.value;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }

    public static void main(String[] args) {

    }

}