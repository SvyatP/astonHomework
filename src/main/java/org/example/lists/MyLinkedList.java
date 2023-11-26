package org.example.lists;

/**
 * The MyLinkedList class implements the MyList interface and represents a linked list.
 * It provides methods to add, delete, and retrieve elements from the list, as well as perform sorting operations.
 *
 * @param <T> the type of elements stored in the list
 */
public class MyLinkedList<T> implements MyList<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructs an empty MyLinkedList.
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructs a new Node with the specified element.
         *
         * @param element the element to be stored in the Node
         */
        Node(T element) {
            data = element;
            next = null;
        }
    }

    /**
     * Deletes all elements from the list and returns a new empty MyLinkedList.
     *
     * @return the modified MyLinkedList (empty list)
     */
    @Override
    public MyLinkedList<T> deleteAll() {
        MyLinkedList<T> newList = new MyLinkedList<>();
        size = 0;
        head = null;
        return newList;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void addItem(T element) {
        addItem(size, element);
    }

    /**
     * Inserts the specified element at the specified index in the list.
     * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
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

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> previousNode = getNode(index - 1);
            newNode.next = previousNode.next;
            previousNode.next = newNode;
        }
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> previousNode = getNode(index - 1);
            previousNode.next = previousNode.next.next;
        }
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
        Node<T> node = getNode(index);
        return node.data;
    }

    /**
     * Sorts the elements in the list in ascending order.
     * The elements must implement the Comparable interface.
     */
    @Override
    public void sort() {
        if (size <= 1) {
            return;
        }

        Node<T> current = head.next;
        while (current != null) {
            Node<T> insertionNode = current;
            T temp = insertionNode.data;

            Node<T> previousNode = getNodeBefore(insertionNode);
            while (previousNode != null && temp instanceof Comparable && ((Comparable<T>) previousNode.data).compareTo(temp) > 0) {
                insertionNode.data = previousNode.data;
                insertionNode = previousNode;
                previousNode = getNodeBefore(previousNode);
            }
            insertionNode.data = temp;

            current = current.next;
        }
    }

    /**
     * Returns the Node before the specified Node in the list.
     *
     * @param node the Node for which to find the previous Node
     * @return the Node before the specifiedNode in the list, or null if the specified Node is the head of the list
     */
    private Node<T> getNodeBefore(Node<T> node) {
        Node<T> current = head;
        while (current != null && current.next != node) {
            current = current.next;
        }
        return current;
    }

    /**
     * Returns the Node at the specified index in the list.
     *
     * @param index the index of the Node to be retrieved
     * @return the Node at the specified index
     */
    private Node<T> getNode(int index) {
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Prints the elements of the list to the standard output.
     */
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
