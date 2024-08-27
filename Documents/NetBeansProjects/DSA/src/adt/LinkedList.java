package adt;

public class LinkedList<T> implements ListInterface<T> {

    private Node firstNode;
    private int entryNum;

    public LinkedList() {
        clear();
    }

    @Override
    public final void clear() {
        firstNode = null;
        entryNum = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        entryNum++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((newPosition >= 1) && (newPosition <= entryNum + 1)) {
            Node newNode = new Node(newEntry);
            if (isEmpty() || (newPosition == 1)) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;
                }
                newNode.next = nodeBefore.next;
                nodeBefore.next = newNode;
            }
            entryNum++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= entryNum)) {
            if (givenPosition == 1) {
                result = firstNode.data;
                firstNode = firstNode.next;
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;
                }
                result = nodeBefore.next.data;
                nodeBefore.next = nodeBefore.next.next;
            }
            entryNum--;
        }
        return result;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= entryNum)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;
            }
            currentNode.data = newEntry;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= entryNum)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;
            }
            result = currentNode.data;
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() {
        return entryNum;
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        result = entryNum == 0;
        return result;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
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
