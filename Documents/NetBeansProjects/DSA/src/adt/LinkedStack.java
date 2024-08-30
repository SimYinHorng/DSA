/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author admin
 */
public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);

        newNode.next = topNode;

        topNode = newNode;
    }

    @Override
    public T pop() {
       if(isEmpty()){
           return null;
       }else{
           Node temp = topNode;
           topNode = topNode.next;
           return temp.data;
       }
    }

    @Override
    public T peek() {
        if(isEmpty()){
            return null;
        }else{
            return topNode.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
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
            this.next = null;
        }
    }
}
