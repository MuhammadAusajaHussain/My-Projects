package com.project;

public class LinkedList<T> {
    Node<T> head;
    int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    public LinkedList(LinkedList<T> current) {
        this();
        if (current != null && !current.isEmpty()) {
            Node<T> currentHead = current.head;
            while (currentHead != null) {
                this.add(currentHead.data);
                currentHead = currentHead.next;
            }
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            T removedData = head.data;
            head = head.next;
            size--;
            return removedData;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            T removedData = current.next.data;
            current.next = current.next.next;
            size--;
            return removedData;
        }
    }

    public T removeFirst() {
        if (head != null) {
            Node<T> removedNode = head;
            head = head.next;
            size--;
            return removedNode.data;
        }
        return null;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String s = "{";
        Node<T> current = head;
        if (current == null) {
            return s + "}";
        }
        while (current.next != null) {
            s += String.valueOf(current.data) + "->";
            current = current.next;
        }
        s += String.valueOf(current.data);
        return s + "}";
    }


}
class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}