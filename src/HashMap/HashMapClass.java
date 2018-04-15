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
    private int loadListNumber;

    HashMapClass() {
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
        loadListNumber = getLoadListNumber();
    }

    HashMapClass(int capacity) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
        loadListNumber = getLoadListNumber();
    }

    HashMapClass(int capacity, double loadFactor) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        if (loadFactor > 0)
            this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
        loadListNumber = getLoadListNumber();
    }

    public E getValue(E key) {
        E value = null;
        E e = null;
        if (key == null) {
            e = doSearchNullKey();
        } else {
            int hash = doHash(key, value);
            int index = searchPosition(hash, hashMap.length);

            if (hashMap[index] == null) {
                System.out.println("no such element");
                return null;
            }
            Node<E> node = (Node<E>) hashMap[index];
            while (node.next != null) {
                if (node.key == key)
                    e = node.value;
                node = node.next;
            }

            if(node.key == key)
                e = node.value;
        }
        return e;
    }

    private E doSearchNullKey() {
        Node<E> node = (Node<E>) hashMap[0];

        if (hashMap[0] == null) {
            System.out.println("no such element");
            return null;
        }
        while (node.next != null) {
            if (node.key == null)
                return node.value;
            node = node.next;
        }
        if (node.key == null)
            return node.value;

        return null;
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
        }
        size++;
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
        int h = key.hashCode();
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
        else {
            int hash = doHash(key, value);
            int index = searchPosition(hash, hashMap.length);

            if (hashMap[index] == null) {
                System.out.println("no such element");
                return;
            }
            boolean isDel = false;
            Node<E> node = (Node<E>) hashMap[index];

            if (node.next == null && (node.key == key && node.value == value)) {
                hashMap[index] = null;
                size--;
                return;
            }
            while (node.next != null) {
                if (node.key == key && node.value == value) {
                    if (node.next.next == null) {
                        hashMap[index] = node.next;
                    } else {
                        node.next = node.next.next;
                    }
                    isDel = true;
                }
                node = node.next;
            }
            if (node.key == key && node.value == value) {
                Node<E> otherNode = (Node<E>) hashMap[index];
                while (otherNode.next.next != null)
                    otherNode = otherNode.next;
                otherNode.next = null;
                isDel = true;
            }
            if (!isDel)
                System.out.println("no such element");
        }
        size--;
    }

    private void removeNullKeyElement(E key, E value) {
        if (hashMap[0] == null) {
            System.out.println("no such null element in HashMap");
            return;
        }
        Node<E> node = (Node<E>) hashMap[0];

        if (node.next == null && (node.key == key && node.value == value)) {
            hashMap[0] = null;
            return;
        }

        while (node.next != null) {
            if (node.key == null) {
                if (node.next.next == null)
                    hashMap[0] = node.next;
                else {
                    node.next = node.next.next;
                }
            }
            node = node.next;
        }
        if (node.key == null) {
            Node<E> otherNode = (Node<E>) hashMap[0];
            while (otherNode.next.next != null)
                otherNode = otherNode.next;
            otherNode.next = null;
        }
    }

    public int getNumberOfElements() {
        return size;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
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

    public double getLoadList() {

        double loadList = size / getLoadListNumber();
        return loadList;
    }

    public int getLoadListNumber() {
        int k = 0;
        for (int i = 0; i < hashMap.length; i++) {
            if (hashMap[i] != null)
                k++;
        }
        return k;
    }

    public void removeAllElements() {
        for (int i = 0; i < hashMap.length; i++) {
            if (hashMap[i] != null)
                hashMap[i] = null;
        }
        size = 0;
    }
}
