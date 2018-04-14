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
    private double loadFactor = 2.0;
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
        if (key == null)
            putForNullKey(value);
        else {
            int hash = doHash(key, value);
            int index = searchPosition(hash, hashMap.length);

            if (hashMap[index] == null) {
                addNode(hash, key, value, index);
            } else {
                Node<E> otherNode = (Node<E>) hashMap[index];
                boolean isSet = false;
                while (otherNode.next != null) {
                    if (otherNode.hash == hash && (otherNode.key == key || otherNode.key.equals(key))) {
                        otherNode.value = value;
                        isSet = true;
                    }
                    otherNode = otherNode.next;
                }
                if (otherNode.hash == hash && (otherNode.key == key || otherNode.key.equals(key))) {
                    otherNode.value = value;
                    isSet = true;
                }

                if (!isSet) {
                    addNode(hash, key, value, index);
                }
            }
            size++;
        }


    }

    private void putForNullKey(E value) {
        boolean isSet = false;
        if (hashMap[0] == null)
            hashMap[0] = new Node<>(null, null, 0, value);
        else {
            Node<E> otherNode = (Node<E>) hashMap[0];

            while (otherNode.next != null) {
                if (otherNode.key == null) {
                    otherNode.value = value;
                    isSet = true;
                }
                otherNode = otherNode.next;
            }
            if (otherNode.key == null) {
                otherNode.value = value;
                isSet = true;
            }
            if (!isSet)
                addNode(0, null, value, 0);
        }
    }

    private void addNode(int hash, E key, E value, int index) {
        Node<E> e = (Node<E>) hashMap[index];
        hashMap[index] = new Node<>(e, key, hash, value);
    }

    private int doHash(E key, E value) {
        int h = key.hashCode() ^ value.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int searchPosition(int hash, int capacity) {
        return hash & (capacity - 1);
    }

    private Object[] doResize(Object[] hashMap) {
        Object[] newHashMap = null;
        if (size >= threshold) {
            newHashMap = new Object[hashMap.length * 2];

            for (int i = 0; i < hashMap.length; i++) {
                if (hashMap[i] == null)
                    continue;
                Node<E> node = (Node<E>) hashMap[i];
                while (node.next != null) {

                }
            }

        }
        return newHashMap;
    }

    public void deleteElement(E key, E value) {
        if (key == null)
            removeNullKeyElement(key, value);
        else{

        }
    }

    private void removeNullKeyElement(E key, E value) {
        if (hashMap[0] == null) {
            System.out.println("no such null element in HashMap");
            return;
        }
        Node<E> node = (Node<E>) hashMap[0];

        while (node.next != null) {
            if (node.key == null) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
        if(node.key == null){
            Node<E> node1 = (Node<E>) hashMap[0];
            while (node1.next != null){
                if(node1.next.next == null)
                    node1.next = null;
            }
        }
    }

    public void printHashMap() {
        for (int i = 0; i < hashMap.length; i++) {
            int count = 0;
            if (hashMap[i] == null)
                continue;
            Node<E> node = (Node<E>) hashMap[i];
            while (node.next != null) {
                count++;
                System.out.println((i + 1) + " " + count + " - " + node.key + " " + node.value);
                node = node.next;

            }
            System.out.println((i + 1) + " " + (count + 1) + " - " + node.key + " " + node.value);
        }
        System.out.println();
    }
}
