import java.util.Iterator;
import java.util.NoSuchElementException;

// use doubly linked list (or just circular list?)
public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int numItems;

    public Deque() {                         // construct an empty deque
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }               // is the deque empty?

    public int size() {
        return numItems;
    }                       // return the number of items on the deque

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Node newHead = new Node();
        newHead.item = item;

        if (head != null) {
            newHead.next = head;
            head.prev = newHead;
        }

        head = newHead;
        if (tail == null) { tail = head; }

        numItems++;

        assert check();
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Node newTail = new Node();
        newTail.item = item;

        if (tail != null) {
            newTail.prev = tail;
            tail.next = newTail;
        }

        tail = newTail;
        if (head == null) { head = tail; }

        numItems++;

        assert check();
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        Node tmp = head;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        numItems--;

        return tmp.item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (numItems == 0) {
            throw new NoSuchElementException();
        }

        Node tmp = tail;  // create temporary item to store the Item in the last node
        tail = tail.prev;  // set the second to last node to be the new last node

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        numItems--;

        return tmp.item;
    }

    public Iterator<Item> iterator() {
        return new LIterator();
    }       // return an iterator over items in order from front to end

    private class LIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item tmpItem = current.item; // save the data in the current node before moving current one step forwards
            current = current.next;
            return tmpItem;
        }
    }

    private boolean check() {
        return true;
    }

    public static void main(String[] args) {  // unit testing (optional)

    }

}