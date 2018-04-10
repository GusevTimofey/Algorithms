package TreeMap.RBtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeMapTests<K, V> {

    @Test
    void add_addThreeElements_True() {
        RBtree<K, V> treeMapClass = treeMapClassFabric();
        assertEquals(treeMapClass.size, 3);
        assertEquals(treeMapClass.size, 4);
    }

    @Test
    void removeElementAt_Default_True() {
        RBtree<K, V> treeMapClass = treeMapClassFabric();
        treeMapClass.remove(8);
        assertEquals(treeMapClass.size, 2);
        assertEquals(treeMapClass.size, 4);

    }

    @Test
    void removeAll_Remove_True() {
        RBtree<K, V> treeMapClass = treeMapClassFabric();
        treeMapClass.clear();
        assertEquals(treeMapClass.size, 0);
        assertEquals(treeMapClass.size, 4);
    }

    @Test
    void value_EqualsValue_True() {
        RBtree<K, V> treeMapClass = treeMapClassFabric();
        treeMapClass.get(8);
        assertEquals(treeMapClass.get(8), 5);
        assertEquals(treeMapClass.get(13), 5);
    }

    private RBtree<K, V> treeMapClassFabric() {
        RBtree treeMapClass = new RBtree();
        treeMapClass.put(8, 5);
        treeMapClass.put(13, 7);
        treeMapClass.put(1, 9);
        return treeMapClass;
    }

    @Test
    void bigTest_insert() {
        RBtree treeMapClass1 = new RBtree();
        for (int i = 0; i < 100_000; i++) {
            treeMapClass1.put(i, i + 1);
        }

    }
}

