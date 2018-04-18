package HashMap;

public class HashMapClass<K, V> {

    public class Node<K, V> {
        private Node next;
        private K key;
        private int hash;
        private V value;

        Node(Node next, K key, int hash, V value) {
            this.next = next;
            this.key = key;
            this.hash = hash;
            this.value = value;
        }
    }

    private Object[] hashMap;
    private double loadFactor = 2.0;
    private int threshold;
    private int size;
    private int capacity = 16;

    public HashMapClass() {
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    public HashMapClass(int capacity) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    public HashMapClass(int capacity, double loadFactor) {
        if (capacity > 0 && capacity <= (Integer.MAX_VALUE / 2))
            this.capacity = capacity;
        if (loadFactor > 0)
            this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        hashMap = new Object[capacity];
    }

    public V getValue(K key) {
        V value;
        if (key == null)
            value = doSearchNullKey();
        else
            value = doSearchNoNullKey(key);
        return value;
    }

    private V doSearchNullKey() {
        Node<K, V> node = (Node<K, V>) hashMap[0];
        if (hashMap[0] == null) {
            System.out.println("Элемент не найден");
            return null;
        }
        for (; node != null; node = node.next) {
            if (node.key == null)
                return node.value;
        }
        return null;
    }

    private V doSearchNoNullKey(K key) {
        V value = null;
        int hash = doHash(key);
        int index = searchPosition(hash, hashMap.length);
        if (hashMap[index] == null) {
            System.out.println("Элемент не найден");
            return null;
        }
        Node<K, V> node = (Node<K, V>) hashMap[index];
        for (; node != null; node = node.next) {
            if (node.key == key)
                value = node.value;
        }
        return value;
    }

    public void addElement(K key, V value) {
        if (key == null)
            putForNullKey(value);
        else {
            putForNoNullKey(key, value);
        }
        size++;
        hashMap = doResize(hashMap);
    }

    private void putForNoNullKey(K key, V value) {
        int hash = doHash(key);
        int index = searchPosition(hash, hashMap.length);
        if (hashMap[index] == null) {
            addNode(hash, key, value, index);
        } else {
            Node<K, V> node = (Node<K, V>) hashMap[index];
            boolean isSet = false;
            for (; node != null; node = node.next) {
                if (node.key == null)
                    continue;
                if (node.hash == hash && (node.key == key || node.key.equals(key))) {
                    node.value = value;
                    isSet = true;
                }
            }
            if (!isSet) {
                addNode(hash, key, value, index);
            }
        }
    }

    private void putForNullKey(V value) {
        boolean isSet = false;
        if (hashMap[0] == null)
            hashMap[0] = new Node<>(null, null, 0, value);
        else {
            Node<K, V> node = (Node<K, V>) hashMap[0];
            for (; node != null; node = node.next) {
                if (node.key == null) {
                    node.value = value;
                    isSet = true;
                }
            }
            if (!isSet)
                addNode(0, null, value, 0);
        }
    }

    private void addNode(int hash, K key, V value, int index) {
        Node<K, V> e = (Node<K, V>) hashMap[index];
        hashMap[index] = new Node<>(e, key, hash, value);
    }

    private int doHash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int searchPosition(int hash, int capacity) {
        return hash % (capacity - 1);
    }

    private Object[] doResize(Object[] hashMap) {
        Object[] newHashMap = hashMap;
        if (size >= threshold) {
            newHashMap = new Object[(hashMap.length * 2) + 1];
            threshold = (int) (newHashMap.length * loadFactor);
            for (int i = 0; i < hashMap.length; i++) {
                if (hashMap[i] == null)
                    continue;
                Node<K, V> node = (Node<K, V>) hashMap[i];
                for (; node != null; node = node.next) {
                    int hash = node.hash;
                    int index = searchPosition(hash, newHashMap.length);
                    Node<K, V> otherNode = (Node<K, V>) newHashMap[index];
                    newHashMap[index] = new Node<>(otherNode, node.key, node.hash, node.value);
                }
            }
        }
        return newHashMap;
    }

    public void removeElementByKey(K key) {
        if (key == null)
            removeNullKeyElement();
        else
            removeNoNullKeyElement(key);
        size--;
    }

    private void removeNoNullKeyElement(K key) {
        int hash = doHash(key);
        int index = searchPosition(hash, hashMap.length);
        if (hashMap[index] == null) {
            System.out.println("Элемент не найден, массив " +
                    "по найденному индексу изначально не содержит ни одного элемента");
            return;
        }
        Node<K, V> node = (Node<K, V>) hashMap[index];
        Node<K, V> otherNode = node;
        for (; node != null; otherNode = node, node = node.next) {
            if (key == node.key) {
                if (node == hashMap[index]) {
                    if (node.next == null)
                        hashMap[index] = null;
                    else
                        hashMap[index] = node.next;
                }
                if (node.next == null)
                    otherNode.next = null;
                else
                    otherNode.next = node.next;
            }
        }
    }

    private void removeNullKeyElement() {
        if (hashMap[0] == null) {
            System.out.println("Элемент не найден, массив" +
                    "по найденному индексу изначально не содержит ни одного элемента");
            return;
        }
        Node<K, V> node = (Node<K, V>) hashMap[0];
        Node<K, V> otherNode = node;
        if (node.next == null && node.key != null) {
            System.out.println("Элемент по заданному ключу не найден");
            return;
        }
        for (; node != null; otherNode = node, node = node.next) {
            if (node.key == null) {
                if (node == hashMap[0]) {
                    if (node.next == null)
                        hashMap[0] = null;
                    else
                        hashMap[0] = node.next;
                }
                if (node.next == null)
                    otherNode.next = null;
                else
                    otherNode.next = node.next;
            }
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
            Node<K, V> node = (Node<K, V>) hashMap[i];
            for (; node != null; node = node.next) {
                count++;
                System.out.println("Индекс массива: " + (i + 1) +
                        ", номер элемента в цепи: " + count + ". Ключ: '" + node.key + "', значение: '" + node.value + "'");
            }
        }
        System.out.println();
    }

    public double getCurrentThreshold() {
        return (size / (double) getNumOfListsInArray());
    }

    public int getNumOfListsInArray() {
        int k = 0;
        for (Object aHashMap : hashMap) {
            if (aHashMap != null)
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
