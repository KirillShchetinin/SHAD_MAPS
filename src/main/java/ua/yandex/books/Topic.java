/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

/**
 *
 * @author Кирилл
 */
public enum Topic {
    
    COMPUTING("programming"), FANTASY("fantasy"), CLASSICS("classic"), FICTION("fiction");
    
    private final String handle;
    
    Topic(String name) {
        handle = name;
    }
    
    @Override
    public String toString() {
        return handle;
    }
}
