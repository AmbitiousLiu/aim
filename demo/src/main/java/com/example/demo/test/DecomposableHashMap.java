package com.example.demo.test;

import java.util.*;

/**
 * @author jleo
 * @date 2021/3/1
 */
public class DecomposableHashMap<K, V> implements Map<K, V> {

    static class Node<K, V> implements Map.Entry<K, V> {
        boolean decomposed = false;
        int[] keyHash;
        K key;
        V value;
        Node<K, V>[] next;

        Node(int[] keyHash, K key, V value) {
            this.keyHash = keyHash;
            this.key = key;
            this.value = value;
        }

        Node() {
        }

        public void decompose() {
            this.key = null;
            this.value = null;
            this.decomposed = true;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    public Node<K, V>[] root;

    public int[] groupStrategy = {6, 5, 4, 4, 4, 3, 3, 3};

    public int size = 0;

    DecomposableHashMap(int[] groupStrategy) {
        if (groupStrategy != null) {
            int count = 0;
            for (int i : groupStrategy) {
                count += i;
            }
            if (count != 32) {
                throw new IllegalArgumentException("the sum of 'groupStrategy' must equal to 32");
            }
            this.groupStrategy = groupStrategy;
        }
        @SuppressWarnings({"unchecked"})
        Node<K,V>[] tab = (Node<K,V>[])new Node[(int) Math.pow(2, this.groupStrategy[0])];
        root = tab;
    }

    DecomposableHashMap() {
        this(null);
    }

    public int[] hash(Object key) {
        int hashCode = key.hashCode();
        int[] hash = new int[groupStrategy.length];
        for (int i = 0; i < groupStrategy.length; i++) {
            hash[i] = hashCode & ((int) Math.pow(2, groupStrategy[i]) - 1);
            hashCode = hashCode >>> groupStrategy[i];
        }
        return hash;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    static class FloorNode<K, V> {
        int floor;
        Node<K, V>[] nodes;
        int index;

        FloorNode(int floor, Node<K, V>[] nodes, int index) {
            this.nodes = nodes;
            this.floor = floor;
            this.index = index;
        }
    }

    private FloorNode<K, V> getNodeByHash(int[] hash) {
        Node<K, V>[] nodes = root;
        int floor = 0;
        for (int i : hash) {
            Node<K, V> kvNode = nodes[i];
            if (kvNode == null) {
                return new FloorNode<>(floor, nodes, i);
            }
            if (!kvNode.decomposed) {
                return new FloorNode<>(floor, nodes, i);
            }
            nodes = kvNode.next;
            floor++;
        }
        return null;
    }

    public Node<K, V> search(Object key) {
        if (root == null || key == null) {
            return null;
        }
        int[] hash = hash(key);
        FloorNode<K, V> floorNode = getNodeByHash(hash);
        if (floorNode == null) {
            return null;
        }
        Node<K, V> nodeByHash = floorNode.nodes[floorNode.index];
        if (nodeByHash == null) {
            return null;
        }
        if (key.equals(nodeByHash.getKey())) {
            return nodeByHash;
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return search(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        Node<K, V> kvNode = search(key);
        if (kvNode == null) {
            return null;
        }
        return kvNode.getValue();
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be 'null'");
        }
        int[] hash = hash(key);

        Node<K, V>[] nodes = root;
        int floor = 0;
        for (int i : hash) {
            Node<K, V> kvNode = nodes[i];
            if (kvNode == null) {
                break;
            }
            if (!kvNode.decomposed) {
                break;
            }
            nodes = kvNode.next;
            floor++;
        }
        Node<K, V> node = nodes[hash[floor]];
        if (node == null) {
            node = new Node<K, V>(hash, key, value);
            node.keyHash = hash;
            node.key = key;
            node.value = value;
            return node.getValue();
        }

        if (key.equals(node.key)) {
            node.value = value;
            node.keyHash = hash;
            return node.getValue();
        }

        @SuppressWarnings({"unchecked"})
        Node<K, V>[] newNodes = (Node<K, V>[]) new Node[(int) Math.pow(2, groupStrategy[floor + 1])];
        Node<K, V> newNode = new Node<>();
        newNode.key = node.getKey();
        newNode.value = node.getValue();
        newNode.keyHash = node.keyHash;
        nodes[hash[floor + 1]] = newNode;
        node.next = nodes;
        node.decompose();

        return put(key, value);
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
