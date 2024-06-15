package api;

public interface HeapTDA {
    void inicializarHeap(int capacity);
    abstract void insert(int element);
    abstract int extract();
    abstract void heapify(int index);
    int size();
    boolean isEmpty();
    int parent(int index);
    int leftChild(int index);
    int rightChild(int index);
    void swap(int index1, int index2);  
}
