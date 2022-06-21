package util;


public class Node {

    private Object data;
    CircularDoublyLinkedList next;
    CircularDoublyLinkedList prev;

    public Node(Object data, CircularDoublyLinkedList next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CircularDoublyLinkedList getNextNode() {
        return next;
    }

    public void setNextNode(CircularDoublyLinkedList next) {
        this.next = next;
    }

    public CircularDoublyLinkedList getPrev() {
        return prev;
    }

    public void setPrev(CircularDoublyLinkedList prev) {
        this.prev = prev;
    }
}
