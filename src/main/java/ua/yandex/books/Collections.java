/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Кирилл
 */
public class Collections {

    public static Map<String, List<Book> > groupBooksByAuthors(List<Book> books) { 
        Map<String, List<Book>> result = new HashMap<>();
        for (Book book : books) {
            List<String> authors = book.getAuthors();
            for (String author : authors) {
                if (!result.containsKey(author)) {
                    result.put(author, new ArrayList<Book>());
                }
                if (!result.get(author).contains(book)) {
                    result.get(author).add(book);
                }
            }
        }
        return result;
    }
    public static Map<Integer, Set<String>> findTopAuthorsPerEachYear(List<Book> books) {
        Map<Integer, List<Book> > booksPerYear = new HashMap<>();
        for (Book book : books) {
            if (!booksPerYear.containsKey(book.getYearOfPublishing())) {
                booksPerYear.put(book.getYearOfPublishing(), new ArrayList<Book>());
            }
            booksPerYear.get(book.getYearOfPublishing()).add(book);
        }
        
        Map<Integer, Set<String> > result = new HashMap<>();
        
        for (Map.Entry<Integer, List<Book>> pair : booksPerYear.entrySet()) {
            int year = pair.getKey();
            Map<String, Integer> booksByAuthor = new HashMap<>();
            int maxAmountOfBooks = 0;
            for (Book book : booksPerYear.get(year)) {
                for (String name : book.getAuthors()) {
                    if (!booksByAuthor.containsKey(name)) {
                        booksByAuthor.put(name, 0);
                    }
                    booksByAuthor.put(name, booksByAuthor.get(name) + 1);
                    maxAmountOfBooks = max(maxAmountOfBooks, booksByAuthor.get(name));
                }
            }
            
            for (Map.Entry<String, Integer> entry : booksByAuthor.entrySet()) {
                if (entry.getValue() == maxAmountOfBooks) {
                    if (!result.containsKey(year)) {
                        result.put(year, new HashSet<String>());
                    }
                    result.get(year).add(entry.getKey());
                }
            }
        }
        return result;
    }

    public static Set<Tuple<Topic, List<String>>> findTopicsWithTheMostNumberOfBooks(List<Book> books) {
        Map<Topic, List<String>> booksByTopic = new HashMap<>();

        for (Book book : books) {
            if (!booksByTopic.containsKey(book.getTopic())) {
                booksByTopic.put(book.getTopic(), new ArrayList<String>());
            }
            booksByTopic.get(book.getTopic()).add(book.getTitle());
        }
        
        int maxBooksInTopic = 0;
        for (Map.Entry<Topic, List<String>> pair : booksByTopic.entrySet()) {
            maxBooksInTopic = max(maxBooksInTopic, pair.getValue().size());
        }
        
        Set<Tuple<Topic, List<String>>> result = new HashSet<> ();
        for (Entry<Topic, List<String>> pair : booksByTopic.entrySet()) {
            if (pair.getValue().size() == maxBooksInTopic) {
                result.add(new Tuple(pair));
            }
        }
        
        return result;
    }
}