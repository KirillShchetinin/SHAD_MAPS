package ua.yandex.prioritymap;

import java.util.Iterator;

/**
 *
 * @author Кирилл
 */
class Node<T> {

    T element;
    Node next;

    Node() {
        next = null;
    }

    Node(T e) {
        element = e;
        next = null;
    }

    Node(T e, Node n) {
        element = e;
        next = n;
    }
}

public class MyLinkedList<T> implements MyList<T>, Iterable<T> {

    private Node<T> begin;
    private int size;

    MyLinkedList() {
        begin = null;
        size = 0;
    }

    private Node getEnd() {
        Node it = begin;
        if (it == null) {
            return it;
        }
        while (it.next != null) {
            it = it.next;
        }
        return it;
    }

    private boolean checkBoarder(int index) {
        if (index < 0) {
            return false;
        }
        return (index <= size);
    }

    @Override
    public void add(Object e) {
        if (size == 0){
            begin= new Node(e);
        }
        else{
            Node end = getEnd();
            end.next = new Node(e);
        }
        size++;
    }

    @Override
    public void add(int index, T e) {
        if (!checkBoarder(index)) {
            throw new IllegalListIndexException("index does not belong to [0,size]");
        }
        if (index == 0) {
            Node temp = new Node(e, begin);
            begin = temp;
            size++;
        } else {
            Node it = begin;
            for (; index > 1; index--) {
                it = it.next;
            }
            if (it.next == null) {
                add(e);
            } else {
                Node temp = new Node(e, it.next);
                it.next = temp;
                size++;
            }
        }
    }

    @Override
    public void addAll(T[] c) {
        for (T e : c) {
            add(e);
        }
    }

    @Override
    public void addAll(int index, T[] c) {
        if (!checkBoarder(index)) {
            throw new IllegalListIndexException("index does not belong to [0,size]");
        }
        for (T e : c) {
            add(index++, e);
        }
    }

    @Override
    public T get(int index) {
        if (index == size || !checkBoarder(index)) {
            throw new IllegalListIndexException("index does not belong to [0,size-1]");
        }
        Node it = begin;
        while (index-- > 0) {
            it = it.next;
        }
        return (T) it.element;
    }

    @Override
    public T remove(int index) {
        if (index == size || !checkBoarder(index)) {
            throw new IllegalListIndexException("index does not belong to [0,size-1]");
        }
        Node it = begin;
        while (index > 1) {
            it = it.next;
            index --;
        }
        T e;
        if (index == 0) {
            e = begin.element;
            begin = begin.next;
        } else {
            e = (T) (it.next).element;
            it.next = (it.next).next;
        }
        size--;
        return e;
    }

    @Override
    public void set(int index, T e) {
        if (index == size || !checkBoarder(index)) {
            throw new IllegalListIndexException("index does not belong to [0,size-1]");
        }
        Node it = begin;
        while (index > 0) {
            it = it.next;
            index --;
        }
        it.element = e;
    }

    @Override
    public int indexOf(T o) {
        int index = 0;
        Node it = begin;
        while (it != null) {
            if (it.element.equals(o)) {
                return index;
            }
            index++;
            it = it.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return (size);
    }

    @Override
    public void clear() {
        begin = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T[] toArray() {
        Object[] tempList = new Object[size];
        Node it = begin;
        for (int i = 0; i < size; i++) {
            tempList[i] = (T) it.element;
            it = it.next;
        }
        return (T[]) tempList;
    }

    public void addFirst(Object e) {
        begin = new Node(e, begin);
    }

    public void addLast(Object e) {
        add(e);
    }

    public Object getFirst() {
        if (size == 0) {
            throw new IllegalListIndexException("List is empty");
        }
        return begin.element;
    }

    public Object getLast() {
        if (size == 0) {
            throw new IllegalListIndexException("List is empty");
        }
        Node end = getEnd();
        return end.element;
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<T> {
        private final T[] array;
        private int current = 0;
        
        public ListIterator() {
            array = toArray();
        }
        
        @Override
        public boolean hasNext() {
            return (current < size);
        }
        
        @Override
        public T next() {
            if (!hasNext()) throw new IllegalListIndexException("");
            return array[current++];
        }
    }

}
