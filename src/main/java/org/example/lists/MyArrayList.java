package org.example.lists;

import java.util.Arrays;
import java.util.Objects;

/**
 * The MyArrayList class implements the MyList interface and represents a resizable array-based list.
 * It provides methods to add, delete, and retrieve elements from the list, as well as perform sorting operations.
 *
 * @param <T> the type of elements stored in the list
 */
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty MyArrayList with the default initial capacity.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds the specified element to the end of the list.
     * If the list is full, the capacity is increased to accommodate the new element.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void addItem(T element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size] = element;
        size++;
    }

    /**
     * Inserts the specified element at the specified index in the list.
     * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
     * If the list is full, the capacity is increased to accommodate the new element.
     *
     * @param index   the index at which the element should be inserted
     * @param element the element to be inserted into the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void addItem(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length) {
            increaseCapacity();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * Deletes the element at the specified index from the list.
     * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the element to be deleted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void deleteElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    /**
     * Retrieves the element at the specified index from the list.
     * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    /**
     * Deletes all elements from the list and resets its capacity to the default.
     *
     * @return the modified MyArrayList (empty list)
     */
    @Override
    public MyArrayList<T> deleteAll() {
        elements = (T[]) new Object[10];
        size = 0;
        return this;
    }

    /**
     * Increases the capacity of the list by doubling its current capacity.
     */
    public void increaseCapacity() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    /**
     * Sorts the elements in the list in ascending order.
     * The elements must implement the Comparable interface.
     *
     * @throws ClassCastException if the elements are not comparable
     */
    @Override
    public void sort() {
        if (!(elements[0] instanceof Comparable)) {
            throw new ClassCastException("Elements are not comparable");
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable<T>) elements[j]).compareTo((T) elements[j + 1]) > 0) {
                    T temp = (T) elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Returns a string representation of the elements in the list.
     * The string is formatted as an array, excluding null elements.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(elements).filter(Objects::nonNull).toArray());
    }
}
