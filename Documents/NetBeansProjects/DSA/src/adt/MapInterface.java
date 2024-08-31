/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Sim Yin Horng
 * @param <K>
 * @param <V>
 */
public interface MapInterface<K,V> {
    
    public V put(K key,V value);
    
    public V get(K key);
    
    public void remove(K key);
    
    public boolean containsKey(K key);
    
    public boolean containsValue(V values);
    
    public int size();
    
    public void clear();
    
    public ListInterface<V> values();
    
    public SetInterface<K> keySet();
    
    public boolean isEmpty();
}
