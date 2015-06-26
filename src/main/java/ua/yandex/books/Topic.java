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
    
    COMPUTING, FANTASY, CLASSICS, FICTION;
    
    @Override
    public String toString() {
        switch(this) {
            case COMPUTING:
                return "programming";
            case FANTASY:
                return "fantasy";
            case CLASSICS:
                return "classic";
            case FICTION:
                return "fiction";
            default:
                return "";
        }
    }
}
