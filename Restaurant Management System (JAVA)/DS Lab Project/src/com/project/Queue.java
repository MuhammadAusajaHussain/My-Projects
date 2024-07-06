package com.project;

public class Queue<K> {
    private LinkedList<K> queue;

    public Queue() {
        queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(K data) {
        queue.add(data);
    }

    public K dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            return queue.removeFirst();
        }
    }

    public K peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No elements to peek.");
            return null;
        } else {
            return queue.get(0);
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Nothing to display.");
        } else {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                System.out.print(queue.get(i) + " ");
            }
            System.out.println();
        }
    }
}

