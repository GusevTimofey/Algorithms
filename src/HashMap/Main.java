package HashMap;

public class Main {
    public static void main(String[] args) {
        HashMapClass hashMap = new HashMapClass();
        hashMap.addElement("0", "zero");
        hashMap.addElement("key", "one");
        hashMap.addElement(null,null);
        hashMap.addElement("idx", "two");
        hashMap.printHashMap();
    }
}
