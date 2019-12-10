package com.robosh;


import com.robosh.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {

    private Node<T> root;
    private int size;

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        if (root != null) {
            newNode.next = root;
        }
        root = newNode;
        size++;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T value = root.value;
        root = root.next;
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }

        public Node() {
        }
    }
}
