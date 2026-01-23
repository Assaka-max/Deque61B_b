package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{

    private static final int INITIAL_CAPACITY = 8;
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] item;

    public ArrayDeque61B(){
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        item = (T[]) new Object[INITIAL_CAPACITY];
    }

    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        int index = Math.floorMod(nextFirst+1, item.length);
        for(int i = 0; i < size; i++) {
            newArray[i] = item[index];
            index = Math.floorMod(index+1, item.length);
        }
        item = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if(size == item.length) resize(size*2);
        item[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst-1, item.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size == item.length) resize(size*2);
        item[nextLast] = x;
        nextLast = Math.floorMod(nextLast+1, item.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = 0;
        while (get(index) != null) {
            returnList.add(get(index));
            index++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(index < size) return item[Math.floorMod(nextFirst+1+index, item.length)];
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
