package PriorityQueue;

import java.util.Arrays;

public class PriorityQueue<E extends Comparable<E>> {

    private int size;
    private E[] array;

    PriorityQueue() {
        int DEFAULT_INITIAL_CAPACITY = 11;
        array = (E[]) new Comparable[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    public void addElement(int priority, E e) {
        if (e == null)
            throw new NullPointerException();
        if (size >= array.length - 1)
            array = Arrays.copyOf(array, array.length * 2);

        int index = size;
        array[index] = (E) new QueueObject<>(priority, e);

        while (hasParent(index) && parent(index).compareTo(array[index]) < 0) {
            E tmp = array[index];
            array[index] = array[parentIndex(index)];
            array[parentIndex(index)] = tmp;
            index = parentIndex(index);
        }
        size++;
    }

    public void removeElement() {
        array[0] = array[size - 1];
        array[size - 1] = null;
        size--;
        int index = 0;
        while (hasLeftChild(index)) {
            int largestChild = leftIndex(index);
            if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) < 0)
                largestChild = rightIndex(index);
            if (array[index].compareTo(array[largestChild]) < 0) {
                E tmp = array[index];
                array[index] = array[largestChild];
                array[largestChild] = tmp;
            } else
                break;
            index = largestChild;
        }
    }

    public E getMax() {
        QueueObject anArray = (QueueObject) array[0];
        return (E) anArray.e.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean hasParent(int i) {
        return i >= 1;
    }

    private int leftIndex(int i) {
        return i * 2 + 1;
    }

    private int rightIndex(int i) {
        return i * 2 + 2;
    }

    private boolean hasLeftChild(int i) {
        return leftIndex(i) < size;
    }

    private boolean hasRightChild(int i) {
        return rightIndex(i) < size;
    }

    private E parent(int i) {
        return array[parentIndex(i)];
    }

    private int parentIndex(int i) {
        return i / 2;
    }

    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            QueueObject anArray = (QueueObject) array[i];
            if (anArray == null)
                continue;
            else
                System.out.print(anArray.e.toString() + " ");
        }
        System.out.println();
    }

    public class QueueObject<E> implements Comparable<QueueObject> {
        private int priority;
        E e;

        QueueObject(int priority, E e) {
            this.priority = priority;
            this.e = e;
        }

        @Override
        public int compareTo(QueueObject queueObject) {
            if (priority != queueObject.priority) return priority < queueObject.priority ? -1 : 1;
            return 0;
        }
    }
}


