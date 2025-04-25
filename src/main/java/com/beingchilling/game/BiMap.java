package com.beingchilling.game;

import java.util.HashMap;

import java.util.Set;


/**
 * Generikus osztály mind a két irányban HashMap
 * @param <K> kulcs
 * @param <V> érték
 */
public class BiMap<K, V> {

    /**
     * A map kulcs alapján tárolunk értékeket
     */
    private final HashMap<K, V> forwardMap = new HashMap<>();

    /**
     * A map értké alapján tároljuk a kulcsokat
     */
    private final HashMap<V, K> backwardMap = new HashMap<>();

    /**
     * A mapba rakja a megadott kulcsot és értékét
     * @param key kulcs
     * @param value ért
     */
    public void put(K key, V value)
    {
        forwardMap.put(key, value);
        backwardMap.put(value, key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param value the value
     * @return the key
     */
    public K getK(V value)
    {
        return backwardMap.get(value);
    }

    /**
     * Returns the value to which the specified key is mapped
     * @param key the value
     * @return the value
     */
    public V getV(K key)
    {
        return forwardMap.get(key);
    }

    /**
     * Kulcs szerint törli a Mapból az értéket
     * @param key
     */
    public void removeByK(K key)
    {
        backwardMap.remove(forwardMap.get(key));
        forwardMap.remove(key);
    }

    /**
     * Érték szerint törli a Mapból a kulcsot
     * @param value
     */
    public void removeByV(V value)
    {
        forwardMap.remove(backwardMap.get(value));
        backwardMap.remove(value);
    }


    public void getKOrDefault(V value) {
        backwardMap.getOrDefault(value, null);
    }

    public void getVOrDefault(K key) {
        forwardMap.getOrDefault(key, null);
    }

    public void clear() {
        forwardMap.clear();
        backwardMap.clear();
    }

    public Set<K> keySet()
    {
        return forwardMap.keySet();
    }

    /**
     * return all the values in the map
     */
    public Set<V> valueSet()
    {
        return backwardMap.keySet();
    }

    /**
     * check if the map contains the Key
     */
    public boolean containsK(K key)
    {
        return forwardMap.containsKey(key);
    }

    /**
     * check if the map contains the Value
     */
    public boolean containsV(V value)
    {
        return backwardMap.containsKey(value);
    }

    /**
     * the size of the map
     */
    public int size(){return backwardMap.size();}
}
