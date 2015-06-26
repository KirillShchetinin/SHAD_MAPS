/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prioritymap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Кирилл
 * @param <Key>
 */
public class Heap<Key> implements Iterable<Key> {
    private Key[] heap;                   
    private int size;                       
    private Comparator<Key> comparator;  

    public Heap() {
        heap = (Key[]) new Object[1];
        size = 0;
    }
    public Heap(int initCapacity) {
        heap = (Key[]) new Object[initCapacity + 1];
        size = 0;
    }

    public Heap(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        heap = (Key[]) new Object[initCapacity + 1];
        size = 0;
    }

    public Heap(Comparator<Key> comparator) {
        this.comparator = comparator;
        heap = (Key[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Key min() {
        // under existing comparator
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
        return heap[1];
    }

    private void resize(int capacity) { 
        Key[] temp = (Key[]) new Object[capacity + 1];
        if (size > capacity) size = capacity;
        System.arraycopy(temp, 1, heap, 1, size);
        //for (int i = 1; i <= size; i++) temp[i] = heap[i];
        heap = temp;
    }

    public void clear() {
        size = 0;
    }
    
    public void insert(Key x) {
        if (size + 1 >= heap.length) resize(2 * heap.length);
        heap[++size] = x;
        for (int i = size; i / 2 >= 1; i /= 2) {
            if (!less(heap[i], heap[i / 2])) break;
            Key temp = heap[i];
            heap[i] = heap[i / 2];
            heap[i / 2] = temp;
        }
    }

    public Key extractMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
        Key resMin = heap[1];
        heap[1] = heap[size --];
        for (int i = 1; i * 2 <= size;) {
            int toSwap = i;
            if (less(heap[i * 2], heap[i])) toSwap = i * 2;
            if (less(heap[i * 2 + 1], heap[i])) toSwap = i * 2 + 1;
            if (toSwap > i) {
                Key temp = heap[i];
                heap[i] = heap[toSwap];
                heap[toSwap] = temp;
                i = toSwap;
            }
            else {
                break;
            }
        }
        return resMin;
    }
    
    private boolean less(Key first, Key second) {
        if (comparator == null) {
            return (((Comparable<Key>) first).compareTo(second) < 0);
        }
        else
            return comparator.compare(first, second) < 0;
    }
    
    @Override
    public Iterator<Key> iterator() { 
        return new HeapIterator(); 
    }

    private class HeapIterator implements Iterator<Key> {

        final private Heap<Key> example;

        public HeapIterator() {
            if (comparator == null) {
                example = new Heap<>(size());
            }
            else {
                example = new Heap<>(size(), comparator);
            }
            for (int i = 1; i <= size; i++)
                example.insert(heap[i]);
        }

        @Override
        public boolean hasNext()  {
            return !example.isEmpty();       
        }
        
        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return example.extractMin();
        }
    }

}