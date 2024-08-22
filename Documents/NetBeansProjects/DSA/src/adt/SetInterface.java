package adt;

import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author admin
 * @param <T>
 */
public interface SetInterface<T> {
    public boolean add(T newElement);
    public boolean remove(T anElement);
    public boolean checkSubset(SetInterface anotherSet);
    public void union(SetInterface anotherSet);
    public SetInterface intersection(SetInterface anotherSet);
    public boolean isEmpty();
    public Iterator<T> getIterator();
}
