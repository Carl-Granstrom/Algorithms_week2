import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// implement with arraylist?

public class RandomizedQueueoOld<Item> implements Iterable<Item> {
    private Item[] a;  //resizing array of Item(s)
    private int n; //number of items on the queue

    /**
     * Constructor, initialize empty queue
     */
    public RandomizedQueueoOld() {                // construct an empty randomized queue
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() { return n == 0; }  // is the randomized queue empty?

    public int size() { return n; }  // return the number of items on the randomized queue

    private void resize(int capacity) {  // method to resize the array when needed
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];  // create a new array of size [capacity]

        for (int i = 0; i < n; i++) {  // iterate over all items in the queue(n items)
            temp[i] = a[i];  // copy the item at index i from the old array(a) to the new array(temp)
        }
        a = temp;  // replace the old array with the temporary one of size [capacity]
    }

    public void enqueue(Item item) {           // add the item
        if (item == null) { throw new IllegalArgumentException(); }
        if (n == a.length) { resize(n*2); }
        a[n++] = item;

    }

    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()) { throw new NoSuchElementException(); }
        int randIndex = StdRandom.uniform(n);
        Item temp = a[randIndex];
        a[randIndex] = a[n-1];
        a[n-1] = null;
        n--;
        if (n > 0 && n < (a.length / 4)) { resize(a.length / 2); }
        return temp;
    }

    public Item sample() {                    // return a random item (but do not remove it)
        if (isEmpty()) { throw new NoSuchElementException(); }
        return a[StdRandom.uniform(n)];
    }

    public Iterator<Item> iterator() { return new RandomQueueIterator(); }        // return an independent iterator over items in random order

    private class RandomQueueIterator implements Iterator<Item> {

        private int i;

        public RandomQueueIterator() { i = n-1; }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Knuth.shuffle(a);
            return a[i--];
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {}  // unit testing (optional)
}