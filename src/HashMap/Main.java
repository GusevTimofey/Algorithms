package HashMap;

public class Main {
    public static void main(String[] args) {
        HashMapClass hashMap = new HashMapClass();

        hashMap.addElement("0", "zero");
        hashMap.addElement("key", "one");
        hashMap.addElement(null, null);
        hashMap.addElement("idx", "two");
        System.out.println("Колличество элементов = " + hashMap.getNumberOfElements());
        hashMap.printHashMap();

        hashMap.removeElementByKey("0");
        hashMap.removeElementByKey("idx");
        System.out.println("Колличество элементов = " + hashMap.getNumberOfElements());
        hashMap.printHashMap();

        System.out.println("Значение ключа " + hashMap.getValue("0"));
        System.out.println("Уровень загруженности по умолчанию = " + hashMap.getLoadFactor());
        System.out.println("Текущий уровень загруженности = " + hashMap.getCurrentThreshold());
        System.out.println("Текущее колличество цепочек в массиве = " + hashMap.getNumOfListsInArray());

        hashMap.removeElementByKey(null);
        hashMap.removeAllElements();
        hashMap.printHashMap();

        HashMapClass<Integer,Integer> newHashMap = new HashMapClass();
        for (int i = 0; i < 1000; i++)
            newHashMap.addElement(i, i + 1);
        System.out.println("Колличество элементов = " + newHashMap.getNumberOfElements());
        System.out.println("Текущий уровень загруженности = " + newHashMap.getCurrentThreshold());
        for (int i = 0; i < 400; i++)
            newHashMap.removeElementByKey(i);
        System.out.println("Колличество элементов = " + newHashMap.getNumberOfElements());
        System.out.println("Текущий уровень загруженности = " + newHashMap.getCurrentThreshold());
        System.out.println("Текущее колличество цепочек в массиве = " + newHashMap.getNumOfListsInArray());

    }
}
