/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author admin
 * @param <T>
 */
public class CircularLinkedQueue<T> implements QueueInterface<T> {

    private Node lastNode;

    public CircularLinkedQueue() {
        this.lastNode = null;
    }

    @Override
    public Iterator<T> getIterator() {

        return null;

    }

    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            newNode.next = newNode;
        } else {
            newNode.next = lastNode.next;
            lastNode.next = newNode;
        }
        lastNode = newNode;

    }

    @Override
    public T dequeue() {
        T front = null;
        if (!isEmpty()) {
            front = lastNode.next.data;
            if (lastNode.next == lastNode) {
                lastNode.next = null;
                lastNode = null;
            } else {
                lastNode.next = lastNode.next.next;
            }
        }
        return front;
    }

    @Override
    public T getFront() {
        T front = null;
        if (!isEmpty()) {
            front = lastNode.next.data;
        }
        return front;
    }

    @Override
    public boolean isEmpty() {
        return lastNode == null;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            lastNode.next = null;
            lastNode = null;
        }
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
