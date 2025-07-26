package com.edu;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DefaultCustomArrayList<E> implements CustomArrayList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] data;
    private int size;

    public DefaultCustomArrayList() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        data[size++] = element;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private void removeAt(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
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
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) data[index++];
            }
        };
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}