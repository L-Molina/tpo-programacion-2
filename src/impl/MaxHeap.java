package impl;

public class MaxHeap extends Heap {

    public MaxHeap(int capacity) {
        super();
    }

    public void insert(int element) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = element;
        int current = size++;
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int extract() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int root = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return root;
    }

    public void heapify(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int largest = index;

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }
}
