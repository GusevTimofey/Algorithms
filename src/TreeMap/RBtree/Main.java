package TreeMap.RBtree;

public class Main {

    private static RBtree treeMapClass1 = new RBtree();

    public static void main(String[] args) {

        RBtree treeMapClass = new RBtree();
/*
        13 8 1 6 11 17 15 25 22 27
        true
        false
        15 8 1 6 11 25 17 22 27
        11
        true
*/
        treeMapClass.put(13, 1);
        treeMapClass.put(8, 11);
        treeMapClass.put(17, 1);
        treeMapClass.put(15, 1);
        treeMapClass.put(25, 1);
        treeMapClass.put(22, 1);
        treeMapClass.put(27, 1);
        treeMapClass.put(11, 1);
        treeMapClass.put(1, 1);
        treeMapClass.put(6, 1);
        treeMapClass.preOrder(treeMapClass.root);
        System.out.println();
        System.out.println(treeMapClass.containsKey(1));
        System.out.println(treeMapClass.isEmpty());
        treeMapClass.remove(13);
        treeMapClass.preOrder(treeMapClass.root);
        System.out.println();
        System.out.println(treeMapClass.get(8));
        treeMapClass.clear();
        treeMapClass.preOrder(treeMapClass.root);
        System.out.println(treeMapClass.isEmpty());


        System.out.println();
        System.out.println("Время доавбления 1_000_000 эл-ов:");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            treeMapClass1.put(i, i + 1);
        }
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println(timeConsumedMillis + " милисекунд(ы)");
        System.out.println(HeightTest(1_000_000));
        System.out.println(RBtree.getHight(treeMapClass1.root) + " <= " + 2 * (Math.log10(1_000_001) / Math.log10(2)));
        System.out.println("Коэффициент С равен: " + (RBtree.getHight(treeMapClass1.root) / ((Math.log10(1_000_001) / Math.log10(2)))));


        System.out.println("Время удаления 50_000 эл-ов:");
        long start1 = System.currentTimeMillis();
        for (int i = 100_000; i > 50_000; i--) {
            treeMapClass1.remove(i);
        }
        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis1 = finish1 - start1;
        System.out.println(timeConsumedMillis1 + " милисекунд(ы)");

        System.out.println(HeightTest(50_000));
        System.out.println(RBtree.getHight(treeMapClass1.root) + " <= " + 2 * (Math.log(50_001) / Math.log10(2)));

    }

    private static String HeightTest(int k) {
        return (double) RBtree.getHight(treeMapClass1.root) <= 2
                * (Math.log(k + 1) / Math.log10(2)) ? "passed" : "failed";
    }

}

