package PriorityQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Очередь с String: ");
        PriorityQueue<String> classQueue1 = (PriorityQueue<String>) new PriorityQueue<>(); //
        System.out.println("-----------------------------------------------------");
        classQueue1.addElement(3, "3 - 1");
        classQueue1.addElement(2, "2 - 2");
        classQueue1.addElement(10, "10 - 3");
        classQueue1.addElement(6, "6 - 4");
        classQueue1.addElement(8, "8 - 5");
        classQueue1.addElement(1, "1 - 6");
        System.out.println("Очередь после добавления элементов: ");
        classQueue1.printArray();
        System.out.println("-----------------------------------------------------");
        System.out.println("Максимальный элемент в очереди: \n" + classQueue1.getMax());
        System.out.println("-----------------------------------------------------");
        classQueue1.removeElement();
        classQueue1.removeElement();
        classQueue1.removeElement();
        System.out.println("Очередь после удаления элементов: ");
        classQueue1.printArray();
        System.out.println("-----------------------------------------------------");
        System.out.println("Пуста ли очередь? \n" + classQueue1.isEmpty());
        System.out.println("-----------------------------------------------------");
        System.out.println("Максимальный элемент в очереди: \n" + classQueue1.getMax());
        System.out.println("-----------------------------------------------------");
        classQueue1.removeElement();
        classQueue1.removeElement();
        classQueue1.removeElement();
        System.out.println("Пуста ли очередь? \n" + classQueue1.isEmpty());
        System.out.println("-----------------------------------------------------");
    }
}
