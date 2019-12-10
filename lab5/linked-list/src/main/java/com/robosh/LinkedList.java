package com.robosh;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private int size;
    private Node<T> first;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
//        if (index > size || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<T> node = findNode(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
//        if (index > size - 1 || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
        findNode(index).value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
//        if (index > size - 1 || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
        return findNode(index).value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
//        if (index > size - 1 || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
        if (index == 0) {
            Objects.checkIndex(index, size);
            first = first.next;
        } else {
            Node<T> previous = findNode(index - 1);
            previous.next = previous.next.next;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
//        if (isEmpty()) {
//            return false;
//        }

        Node<T> node = first;
        while (node != null) {
            if (node.value.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node() {
        }

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> findNode(int index) {
        Objects.checkIndex(index, size);
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }
}
