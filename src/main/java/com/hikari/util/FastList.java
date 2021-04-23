package com.hikari.util;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public final class FastList<T> implements  List<T>,RandomAccess, Serializable {

    private static final long serialVersionUID = -4598088075242913858L;

    private final Class<?> clazz;
    private T[] elementData;
    private int size;

    /**
     * default length: 32
     * @param clazz
     */
    public FastList(Class<?> clazz) {
        this.elementData = (T[]) Array.newInstance(clazz, 32);
        this.clazz = clazz;
    }

    public FastList(Class<?> clazz, int capacity) {
        this.elementData = (T[]) Array.newInstance(clazz, capacity);
        this.clazz = clazz;
    }

    @Override
    public boolean add(T element) {
        if (size < elementData.length) {
            // add directly
            elementData[size++] = element;
        } else {
            final int oldCapacity = elementData.length;
            final int newCapacity = oldCapacity << 1;
            final T[] newElementData = (T[]) Array.newInstance(clazz, newCapacity);
            System.arraycopy(elementData, 0, newElementData, 0, oldCapacity);
            newElementData[size++] = element;
            elementData = newElementData;
        }
        return true;
    }

    /**
     * get an element
     */
    @Override
    public T get(int index) {
        return elementData[index];
    }

    public T removeLast() {
        T element = elementData[--size];
        elementData[size] = null;
        return element;
    }


    @Override
    public boolean remove(Object element) {
        for (int index = size-1; index>=0; index--) {
            if (element == elementData[index]) {
                final int numMoved = size - index -1;
                if (numMoved > 0 ) {
                    System.arraycopy(elementData, index+1, elementData, index, numMoved);
                }
                elementData[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T set(int index, T element) {
        T old = elementData[index];
        elementData[index] = element;
        return old;
    }

    @Override
    public T remove(int index) {
        if (size == 0) return null;
        final T old = elementData[index];
        final int numMoved = size-index-1;
        if (numMoved>0) System.arraycopy(elementData, index+1, elementData, index, numMoved);
        elementData[--size] = null;
        return old;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public T next() {
                if (index < size) return elementData[index];
                throw new NoSuchElementException("no more elements in FastList");
            }
        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
