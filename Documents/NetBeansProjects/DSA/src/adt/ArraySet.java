/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author admin
 */
public class ArraySet<T> implements SetInterface<T> {

    private T[] setArray;
    int numberOfElements;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;

    public ArraySet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArraySet(int initial_capacity) {
        setArray = (T[]) new Object[initial_capacity];
        numberOfElements = 0;
    }

    @Override
    public boolean add(T newElement) {
        if (isArrayFull()) {
            doubleArray();
        }

        for (int i = 0; i < numberOfElements; i++) {
            if (setArray[i].equals(newElement)) {
                return false;
            }
        }
        setArray[numberOfElements] = newElement;
        numberOfElements++;
        return true;
    }

    @Override
    public boolean remove(T anElement) {
        for (int i = 0; i < numberOfElements; i++) {
            if (setArray[i].equals(anElement)) {
                removeGap(i);
                numberOfElements--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkSubset(SetInterface anotherSet) {
        if (anotherSet instanceof ArraySet) {
            ArraySet aSet = (ArraySet) anotherSet;
            if (aSet.numberOfElements > this.numberOfElements) {
                return false;
            }

            for (int i = 0; i < aSet.numberOfElements; i++) {
                boolean found = false;
                for (int j = 0; j < this.numberOfElements; j++) {
                    if(aSet.setArray[i].equals(this.setArray[j])){
                        found = true;
                    }
                }
                if(!found){
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public void union(SetInterface anotherSet) {
        if(anotherSet instanceof ArraySet){
            ArraySet aSet = (ArraySet) anotherSet;
            for(int i=0;i<aSet.numberOfElements;i++){
                add((T)aSet.setArray[i]);
                
            }
        }
    }

    @Override
    public SetInterface intersection(SetInterface anotherSet) {
        SetInterface <T> result = new ArraySet<>();
        if(anotherSet instanceof ArraySet){
            ArraySet aSet = (ArraySet) anotherSet;
            for (int i = 0; i < aSet.numberOfElements; i++) {
                boolean found = false;
                for (int j = 0; j < this.numberOfElements; j++) {
                    if(aSet.setArray[i].equals(this.setArray[j])){
                        result.add((T)aSet.setArray[i]);
                        break;
                    }
                }

            }

        }
        
        return result;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    private void removeGap(int index) {
        for (int i = index; i < numberOfElements - 1; i++) {
            setArray[i] = setArray[i + 1];
        }
    }
    
    public String toString(){
        String outputStr = "";
        for(int i = 0; i<numberOfElements;i++){
            outputStr += setArray[i]+"\n";
        }
        return outputStr;
    }

    private boolean isArrayFull() {
        return numberOfElements == setArray.length;
    }

    private void doubleArray() {
        T[] oldArray = setArray;
        setArray = (T[]) new Object[oldArray.length * 2];

        for (int i = 0; i < oldArray.length; i++) {
            setArray[i] = oldArray[i];
        }
    }

    @Override
    public Iterator<T> getIterator() {
        return new IteratorForArraySet();
    }

    private class IteratorForArraySet implements Iterator<T>{
        
        private int nextIndex;
        
        public IteratorForArraySet(){
            nextIndex=0;
            
        }
        
        @Override
        public boolean hasNext() {
            return nextIndex < numberOfElements;
        }

        @Override   
        public T next() {
            if(hasNext()){
                T nextElement = setArray[nextIndex++];
                return nextElement;
            }else{
                return null;
            }
        }
        
    }
    
    
    
}
