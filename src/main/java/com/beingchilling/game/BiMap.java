package com.beingchilling.game;

import java.util.HashMap;

import java.util.Set;



public class BiMap<K, V> {

    private final HashMap<K, V> forwardMap = new HashMap<>();
    private final HashMap<V, K> backwardMap = new HashMap<>();

    public void put(K key, V value)
    {
        forwardMap.put(key, value);
        backwardMap.put(value, key);
    }

    public K getK(V value)
    {
        return backwardMap.get(value);
    }

    public V getV(K key)
    {
        return forwardMap.get(key);
    }

    public void removeByK(K key)
    {
        backwardMap.remove(forwardMap.get(key));
        forwardMap.remove(key);
    }

    public void removeByV(V value)
    {
        forwardMap.remove(backwardMap.get(value));
        backwardMap.remove(value);
    }

    public void getKOrDefault(V value)
    {
        backwardMap.getOrDefault(value,null);
    }

    public void getVOrDefault(K key)
    {
        forwardMap.getOrDefault(key,null);
    }

    public void clear()
    {
        forwardMap.clear();
        backwardMap.clear();
    }

    public Set<K> keySet()
    {
        return forwardMap.keySet();
    }

    public Set<V> valueSet()
    {
        return backwardMap.keySet();
    }

    public boolean containsK(K key)
    {
        return forwardMap.containsKey(key);
    }

    public boolean containsV(V value)
    {
        return backwardMap.containsKey(value);
    }
}
