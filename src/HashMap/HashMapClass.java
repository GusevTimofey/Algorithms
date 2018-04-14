package HashMap;

public class HashMapClass<E> {

    public class Node<E> {
        private Node next;
        private E key;
        private int hash;
        private E value;

        Node(Node next, E key, int hash, E value) {
            this.next = next;
            this.key = key;
            this.hash = hash;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public E getKey() {
            return key;
        }

        public void setKey(E key) {
            this.key = key;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private Object[] hashMap;
    private double loadFactor = 0.75;
    private int threshold;
    private int size;
    private int capacity = 16;

    HashMapClass() {
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    HashMapClass(int capacity) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    HashMapClass(int capacity, double loadFactor) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        if (loadFactor > 0)
            this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    public void addElement(E key, E value) {
        if (key == null) {
            putForNullKey(value);
        }
    }

    private void putForNullKey(E value) {
        Node<E> otherNode = (Node<E>) hashMap[0];
        boolean isSet = false;
        while (otherNode.next != null) {
            if (otherNode.key == null) {
                otherNode.value = value;
                isSet = true;
            }
            otherNode = otherNode.next;
        }
        if (!isSet) {
            addNode(0, null, value, 0);
        }
    }

    private void addNode(int hash, E key, E value, int index) {
        hashMap[index] = new Node<>(null, key, hash, value);
    }
}
