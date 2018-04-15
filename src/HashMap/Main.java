package HashMap;

public class Main {
    public static void main(String[] args) {
        HashMapClass hashMap = new HashMapClass();
        hashMap.addElement("0", "zero");
        hashMap.addElement("key", "one");
        hashMap.addElement(null, null);
        hashMap.addElement("idx", "two");
        System.out.println(hashMap.getValue(null));
        System.out.println(hashMap.getValue("0"));
        System.out.println(hashMap.getLoadList());
        System.out.println(hashMap.getNumberOfElements());
        hashMap.printHashMap();
        hashMap.deleteElement("key", "one");
        System.out.println(hashMap.getNumberOfElements());
        hashMap.printHashMap();
        hashMap.deleteElement(null, null);
        System.out.println(hashMap.getNumberOfElements());
        hashMap.printHashMap();
        hashMap.deleteElement("idx", "two");
        hashMap.printHashMap();
        System.out.println(hashMap.getLoadFactor());
        hashMap.setLoadFactor(2.0);
        System.out.println(hashMap.getLoadList());
        hashMap.removeAllElements();
        hashMap.printHashMap();
    }
}
