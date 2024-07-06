package com.project;

public class HashMap<K,V> {
    LinkedList<HashNode> buckets[];
    int n;
    int M;

    private int size;
    @SuppressWarnings("unchecked")
    public HashMap() {
        this.M = 4;
        this.buckets = new LinkedList[4];
        for (int i = 0; i < this.M; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }
    @SuppressWarnings("unchecked")
    public void put(K key, V val) {
        int bucketIndex = hashFunction(key);
        int foundIndex = doesExist(key, bucketIndex);

        if (foundIndex == -1) {
            buckets[bucketIndex].add(new HashNode<>(key, val));
            n++;
            size++;
        } else {
            HashNode node = buckets[bucketIndex].get(foundIndex);
            node.value = val;
            buckets[bucketIndex].add(foundIndex, node);
        }
        double factor = (double)n/M;

        if(factor > 2.0) {
            rehash();
        }

    }

    public int hashFunction(K key) {
        int bucketIndex = key.hashCode();
        return Math.abs(bucketIndex) % M;
    }
    @SuppressWarnings("unchecked")

    public int doesExist(K key, int bucketIndex) {
        LinkedList<HashNode> list = buckets[bucketIndex];
        for (int i = 0; i < list.size(); i++) {
            if (key == list.get(i).key) {
                return i;
            }
        }
        return -1;
    }
    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<HashNode> oldBucket[] = buckets;
        buckets = new LinkedList[M*2];
        for(int i=0; i<M*2; i++) {
            buckets[i] = new LinkedList<>();
        }
        for(int i=0; i<oldBucket.length; i++) {
            LinkedList<HashNode> ll = oldBucket[i];
            for(int j=0; j<ll.size(); j++) {
                HashNode node = ll.get(j);
                put((K) node.key, (V) node.value);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public V get(K key) {
        int bucketindex = hashFunction(key);
        int foundindex = doesExist(key, bucketindex);
        if(foundindex == -1) {
            return null;
        } else {
            HashNode node = buckets[bucketindex].get(foundindex);
            return (V) node.value;
        }
    }
    public V remove(K key) {
        int bucketIndex = hashFunction(key);
        int foundIndex = doesExist(key, bucketIndex);

        if (foundIndex == -1) {
            return null;
        } else {
            HashNode<K, V> removedNode = buckets[bucketIndex].remove(foundIndex);
            size--;
            n--;

            double factor = (double) n / M;
            if (factor < 0.25) {

                rehash();
            }

            return removedNode.value;
        }
    }

    public boolean containsKey(K key) {
        int bucketIndex = hashFunction(key);
        LinkedList<HashNode> list = buckets[bucketIndex];

        for (int i = 0; i < list.size(); i++) {
            HashNode<K, V> node = list.get(i);
            if (node.key.equals(key)) {
                return true; // Key found in this bucket
            }
        }
        return false; // Key not found in this bucket
    }

    public int size(){
        return size;
    }
}
class HashNode<K,V>{
    K key;
    V value;
    @SuppressWarnings("unchecked")
    public HashNode(K key, V value) {
        this.key=key;
        this.value=value;
    }
}