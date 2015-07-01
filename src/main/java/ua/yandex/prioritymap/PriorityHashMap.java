/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prioritymap;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author Кирилл
 * @param <K>
 * @param <V>
 */
public class PriorityHashMap<K extends Comparable, V> implements Iterable<Map.Entry<K, V>> { 
    
    private final static double CRITICAL_LOAD = 0.75f;
    private final static int INIT_CAPACITY = 8;
    
    private int size = 0;
    private MyList<Tuple<K,V> >[] buckets;
    Heap<K> heap;
    
    PriorityHashMap() {
        this(INIT_CAPACITY);
    }
    
    PriorityHashMap(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        buckets = new MyList[capacity];
        for (int i = 0; i < capacity; i ++) {
            buckets[i] = new MyLinkedList<>();
        }
        heap = new Heap<>(capacity);
    }
    
    public void put(K key, V value) {
        if (get(key) != null) {
            return ;
        }
        
        int index = hashIndex(key.hashCode());
        
        heap.insert(key);
        buckets[index].add(new Tuple(key, value));
        size ++;
        
        if (size * 1.0 / buckets.length > CRITICAL_LOAD) {
            resize(buckets.length << 1);
        }
    }
    
    public V get(K key) {
        int index = hashIndex(key.hashCode());
        
        Iterator listIterator = buckets[index].iterator();
        while (listIterator.hasNext()) {
            Tuple<K,V> e = (Tuple<K,V>) listIterator.next();
            if (e.first.equals(key)) {
                return e.second;
            }
        }
        return null;
    }
    
    public V peekValueWithPriorityKey() {
        if (size == 0) throw new NoSuchElementException("Empty Map");
        K priorityKey = heap.min();
        return get(priorityKey);
    }
    
    public V pollValueWithPriorityKey() {
        if (size == 0) throw new NoSuchElementException("Empty Map");
        K priorityKey = heap.extractMin();
        V value = get(priorityKey);
        removeFromBucket(priorityKey, value);
        return value;
    }
    
    private void removeFromBucket(K key, V value) {
        int index = hashIndex(key.hashCode());
        Tuple<K,V> example = new Tuple<> (key, value);
        int elementPosition = findInstance(index, example);
        
        if (elementPosition == -1) throw new NoSuchElementException(
                "There is no element to remove");
        
        buckets[index].remove(elementPosition);
        size --;
    }
    
    private int findInstance(int index, Tuple<K,V> tuple) {
        Iterator<Tuple<K,V> > listIterator = buckets[index].iterator();
        for (int i = 0; listIterator.hasNext(); i ++) {
            Tuple<K,V> e = listIterator.next();
            if (e.first == tuple.first && e.second == tuple.second) {
                return i;
            }
        }
        return -1;
    }
    private void resize(int newCapacity) {
        int oldCapacity = buckets.length;
        
        MyList<Tuple<K,V> >[] oldBuckets = new MyList[newCapacity];
        System.arraycopy(buckets, 0, oldBuckets, 0, oldCapacity);
        
        size = 0;
        heap.clear();
        buckets = new MyList[newCapacity];
        for (int i = 0; i < newCapacity; i ++) {
            buckets[i] = new MyLinkedList<>();
        }
        
        for (int i = 0; i < oldCapacity; i ++) {
            Iterator<Tuple<K,V>> listIterator = oldBuckets[i].iterator();
            while (listIterator.hasNext()) {
                Tuple<K,V> e = listIterator.next();
                put(e.first, e.second);
            }
        }
    }
    
    private int hashIndex(int hash) {
        hash ^= (hash >> 16);
        hash ^= (hash >> 8);
        hash ^= (hash >> 4);
        hash ^= (hash >> 2);
        return (hash & (buckets.length - 1));
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashIterator();
    }
    
    Iterator entrySet() {
        return new PriorityHashMap.HashIterator();
    }
    
    class HashIterator implements Iterator<Map.Entry<K,V>> {
        
        Iterator keyIterator;

        HashIterator() {
            keyIterator = heap.iterator();
        }

        @Override
        public final boolean hasNext() {
            return keyIterator.hasNext();
        }

        @Override
        public Tuple<K, V> next() {
            K key = (K) keyIterator.next();
            return new Tuple(key, get(key));
        }
    }
}
