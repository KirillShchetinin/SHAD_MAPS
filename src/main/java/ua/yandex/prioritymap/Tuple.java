/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prioritymap;

import java.util.Map;

/**
 *
 * @author Кирилл
 * @param <K>
 * @param <V>
 */
public class Tuple<K,V> implements Map.Entry<K,V> {
    public final K first;
    public V second;
    
    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    @Override    
    public String toString() {
        return "("+first.toString()+", "+second.toString()+")";
    }

    @Override
    public K getKey() {
        return first;
    }

    @Override
    public V getValue() {
        return second;
    }

    @Override
    public V setValue(V value) {
        V old = second;
        second = value;
        return old;
    }
}