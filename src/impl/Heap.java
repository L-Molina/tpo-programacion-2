package impl;

import api.HeapTDA;

public abstract class Heap implements HeapTDA {
    int[] heap;
    int size;
    int capacity;

    public void inicializarHeap(int capacity) {
        capacity = 0;
        size = 0;
        heap = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

    public void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public abstract void insert(int element);
    public abstract int extract();
    public abstract void heapify(int index);
}
