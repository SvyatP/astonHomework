package org.example.lists;

/**
 * The MyList interface represents a list of elements.
 *
 * @param <T> the type of elements stored in the list
 */
public interface MyList<T> {
    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added to the list
     */
    void addItem(T element);

    /**
     * Inserts the specified element at the specified index in the list.
     *
     * @param index   the index at which the element should be inserted
     * @param element the element to be inserted into the list
     */
    void addItem(int index, T element);

    /**
     * Deletes the element at the specified index from the list.
     *
     * @param index the index of the element to be deleted
     */
    void deleteElement(int index);

    /**
     * Retrieves the element at the specified index from the list.
     *
     * @param index the index of the element to be retrieved
     * @return the element at the specified index
     */
    T getElement(int index);

    /**
     * Deletes all elements from the list and returns a new empty list.
     *
     * @return the modified list (empty list)
     */
    MyList<T> deleteAll();

    /**
     * Sorts the elements in the list.
     */
    void sort();
}
