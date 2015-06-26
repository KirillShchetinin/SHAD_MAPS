/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;


/**
 *
 * @author Kirill
 */
public class CollectionsTest {

    private final static int TEST_LENGTH = 3;
    List<Book> books;

    public CollectionsTest() {
        books = new ArrayList<>();
        String[] titles =  new String[TEST_LENGTH];
        String[] authors = new String[TEST_LENGTH];
        Integer[] yearsOfPublishing = new Integer[TEST_LENGTH];
        Topic[] topic = new Topic[TEST_LENGTH];
        
        titles[0] = "The Raven";
        titles[1] = "The angel of the odd";
        titles[2] = "Boogeyman";
        authors[0] = "Edgar Allan Poe";
        authors[1] = "Edgar Allan Poe";
        authors[2] = "Stephen King";
        topic[0] = Topic.CLASSICS;
        topic[1] = Topic.CLASSICS;
        topic[2] = Topic.CLASSICS;
        yearsOfPublishing[0] = 0;
        yearsOfPublishing[1] = 1;
        yearsOfPublishing[2] = 1;
        
        for(int i = 0; i < TEST_LENGTH; i++) {
            List<String> listOfAuthors = new ArrayList<>();
            listOfAuthors.add(authors[i]);
            books.add(new Book(titles[i], listOfAuthors,yearsOfPublishing[i],topic[i]));
        }
    }
    
    @org.junit.Test
    public void testGroupBookByAuthors() {
        System.out.println("groupBookByAuthors");
    
        String expResult = "{Edgar Allan Poe=[The Raven, The angel of the odd], Stephen King=[Boogeyman]}";
        
        Map<String, List<Book>> result = Collections.groupBooksByAuthors(books);
        System.out.println(result.toString());
        System.out.println(expResult);
        assertEquals(result.toString(),expResult);
    }

    @org.junit.Test
    public void testFindTopAuthorsPerEachYear() {
        //Пример: (1984=['Кинг'], 1990=['Роулинг', 'Фрай'], ...)
        System.out.println("findTopAuthorsPerEachYear");
        String expResult = "{0=[Edgar Allan Poe], 1=[Edgar Allan Poe, Stephen King]}";
        Map<Integer, Set<String>> result = Collections.findTopAuthorsPerEachYear(books);
        assertEquals(expResult, result.toString());
    }

    @org.junit.Test
    public void testFindTopicsWithTheMostNumberOfBooks() {
        System.out.println("findTopicsWithTheMostNumberOfBooks");
        String expResult = "[(classic, [The Raven, The angel of the odd, Boogeyman])]";
        Set<Tuple<Topic, List<String>>> result = Collections.findTopicsWithTheMostNumberOfBooks(books);
        System.out.println(result.toString());
        System.out.println(expResult);
        assertEquals(expResult, result.toString());
    }
    
}