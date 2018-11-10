import java.util.Iterator;
import java.util.NoSuchElementException;

// use doubly linked list (or just circular list?)
public class DequeOld<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int numItems;

    public DequeOld() {                         // construct an empty deque
        numItems = 0;
        head = null;
        tail = null;
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean isEmpty() {
        return numItems == 0;
    }               // is the deque empty?

    public int size() {
        return numItems;
    }                       // return the number of items on the deque

    public void addFirst(Item item) {          // add the item to the front
        Node tmp = new Node(item, head, null);   // create a new node with the name tmp then
        // set its next node reference = the head node

        if (head != null) {
            head.prev = tmp;
        }    // if there is a head node(aka the linkedlist is not empty) then set
        // that head node's previous reference to point to the newly created tmp
        // node

        head = tmp;                             // set the newly created tmp node to become the new head node

        if (tail == null) {
            tail = tmp;
        }          // if there is no tail node(the list was empty) then also make
        // the new node be the tail node
        numItems++;  // increment numItems to keep track of the number of items in the list

    }

    public void addLast(Item item) {          // add the item to the end
        Node tmp = new Node(item, null, tail);
        if (tail != null) {
            tail.next = tmp;
        }
        tail = tmp;
        if (head == null) {
            head = tmp;
        }
        numItems++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (numItems == 0) {
            throw new NoSuchElementException();
        } // exception if attempting to remove from empty Deque
        Node tmp = head;  // create temporary Item to store the Item of head node
        head = head.next;  // set the second item in the list to be the new head
        head.prev = null;  // set the previous reference of that node to null(so it no longer points to the old head)
        numItems--;

        return tmp.item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (numItems == 0) {
            throw new NoSuchElementException();
        }
        Node tmp = tail;  // create temporary item to store the Item in the last node
        tail = tail.prev;  // set the second to last node to be the new last node
        tail.next = null;
        numItems--;

        return tmp.item;
    }

    public Iterator<Item> iterator() {
        return new LIterator();
    }       // return an iterator over items in order from front to end

    private class LIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current.next != null;
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

    public static void main(String[] args) {  // unit testing (optional)

    }

}