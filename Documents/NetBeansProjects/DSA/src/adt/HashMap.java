/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;

/**
 *
 * @author user
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements MapInterface<K, V>, Serializable {

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = buckets[index];

        if (node == null) {
            buckets[index] = new Node<>(key, value);
            size++;
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value; // Update the value if key already exists
                    return;
                }
                if (node.next == null) {
                    break;
                }
                node = node.next;
            }
            node.next = new Node<>(key, value); // Add new node if key not found
            size++;
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        return null; // Return null if key not found
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        Node<K, V> prev = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = node.next; // Remove first node in the bucket
                } else {
                    prev.next = node.next; // Remove node in the middle or end
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new Node[capacity];
        size = 0;
    }

    @Override
    public ListInterface<V> values() {
        ArrayList<V> valuesList = new ArrayList<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                valuesList.add(node.value);
                node = node.next;
            }
        }
        return valuesList;
    }

    @Override
    public SetInterface<K> keySet() {
        ArraySet<K> keySet = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    private class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

    }
}
