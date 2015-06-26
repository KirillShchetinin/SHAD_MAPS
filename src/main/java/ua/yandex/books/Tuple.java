/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

import java.util.Map;

/**
 *
 * @author Кирилл
 * @param <K>
 * @param <V>
 */
public class Tuple<K,V> {
    public final K first;
    public final V second;
    
    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public Tuple(Map.Entry<K,V> pair) {
        this.first = pair.getKey();
        this.second = pair.getValue();
    }
    
    @Override
    public String toString() {
        return "("+first.toString()+", "+second.toString()+")";
    }
}
