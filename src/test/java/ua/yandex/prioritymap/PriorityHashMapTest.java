/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prioritymap;

import java.util.Iterator;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kirill
 */
public class PriorityHashMapTest {
    
    public PriorityHashMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of put method, of class PriorityHashMap.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        String key = "key";
        String value = "value";
        PriorityHashMap instance = new PriorityHashMap();
        instance.put(key, value);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(value, instance.get(key));
    }

    /**
     * Test of get method, of class PriorityHashMap.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String key = "key";
        String value = "value";
        PriorityHashMap<String, String> instance = new PriorityHashMap<>();
        instance.put(key, value);
        String expResult = "value";
        String result = instance.get(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of peekValueWithPriorityKey method, of class PriorityHashMap.
     */
    @Test
    public void testPeekValueWithPriorityKey() {
        System.out.println("peekValueWithPriorityKey");
        PriorityHashMap<Integer, String> instance = new PriorityHashMap<>();
        String expResult = "AB";
        Integer[] key = new Integer[2];
        String[] value = new String[2];
        
        key[0] = 1;        
        value[0] = "AB";
        instance.put(key[0], value[0]);
        key[1] = 10;
        value[1] = "BA";
        
        instance.put(key[1], value[1]);
        String result = instance.peekValueWithPriorityKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of pollValueWithPriorityKey method, of class PriorityHashMap.
     */
    @Test
    public void testPollValueWithPriorityKey() {

        System.out.println("pollValueWithPriorityKey");
        PriorityHashMap<Integer, String> instance = new PriorityHashMap<>();
        String expResult = "BA";
        Integer[] key = new Integer[2];
        String[] value = new String[2];
        
        key[0] = 4;        
        value[0] = "BA";
        instance.put(key[0], value[0]);
        key[1] = 7;
        value[1] = "AB";
        
        instance.put(key[1], value[1]);
        String result = instance.pollValueWithPriorityKey();
        assertEquals(expResult, result);

    }

    /**
     * Test of entrySet method, of class PriorityHashMap.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");
        PriorityHashMap<String, String> instance = new PriorityHashMap<>();
        instance.put("c", "AV");
        instance.put("d", "DF");
        instance.put("b", "GF");
        
        
        Iterator result = instance.entrySet();
        String answer = "";
        
        for(Map.Entry<String,String> entry: instance) {
            answer += result.next().toString();
            if(result.hasNext()) {
                answer += ", ";
            }
        }
        
        assertEquals(answer, "(b, GF), (c, AV), (d, DF)");
    }
    
}