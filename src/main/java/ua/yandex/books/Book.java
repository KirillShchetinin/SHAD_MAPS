/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Кирилл
 */
public class Book {

    private final String title;
    private final List<String> authors;
    private final int yearOfPublishing;
    private final Topic topic;
    private final int hash;
    
    public Book(String title, List<String> authors, int yearOfPublishing, Topic topic) {
        this.title = title;
        this.authors = authors;
        this.yearOfPublishing = yearOfPublishing;
        this.topic = topic;
        this.hash = 0;
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
    
    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0) {
            h = h * 37 + title.hashCode();
            String[] list = (String[]) authors.toArray();
            for (int i = 0; i < authors.size(); i ++) {
                h = 37 * h + list[i].hashCode();
            }
            h = h * 37 * 37 * 37 + this.yearOfPublishing;
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (this.yearOfPublishing != other.yearOfPublishing) {
            return false;
        }
        if (this.topic != other.topic) {
            return false;
        }
        return this.hash == other.hash;
    }
}