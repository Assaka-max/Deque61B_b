package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
        return size;
    }

    @Override
    public T removeFirst() {
        if(size != 0) {
            int index = Math.floorMod(nextFirst + 1, item.length);
            T removed = item[index];
            item[index] = null;
            nextFirst = index;
            size--;
            return removed;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(size != 0) {
            int index = Math.floorMod(nextLast - 1, item.length);
            T removed = item[index];
            item[index] = null;
            nextLast = index;
            size--;
            return removed;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if(index < size) return item[Math.floorMod(nextFirst+1+index, item.length)];
        return null;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int currentIndex;
        private int itemsReturned;

        public ArrayDequeIterator(){
            this.currentIndex = Math.floorMod(nextFirst+1, item.length);
            this.itemsReturned = 0;
        }

        @Override
        public boolean hasNext() {
            return itemsReturned < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = item[currentIndex];
            currentIndex = Math.floorMod(currentIndex +1, item.length);
            itemsReturned++;
            return result;
        }
    }

    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other instanceof Deque61B<?> otherDeque){
            if(otherDeque.size() != this.size()) return false;
            Iterator<T> thisIter = this.iterator();
            Iterator<?> otherIter = otherDeque.iterator();

            while (thisIter.hasNext() && otherIter.hasNext()){
                if(!thisIter.next().equals(otherIter.next())) return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return this.toList().toString();
    }
}
