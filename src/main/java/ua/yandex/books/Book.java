/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

import java.util.List;

/**
 *
 * @author Кирилл
 */
public class Book {

    private final String title;
    private final List<String> authors;
    private final int yearOfPublishing;
    private final Topic topic;
    
    public Book(String title, List<String> authors, int yearOfPublishing, Topic topic) {
        this.title = title;
        this.authors = authors;
        this.yearOfPublishing = yearOfPublishing;
        this.topic = topic;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<String> getAuthors() {
        return authors; 
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    @Override
    public String toString() {
        return getTitle();
    }
}