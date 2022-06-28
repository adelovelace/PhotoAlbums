package util;

public class CircularDoublyLinkedList<E> implements List<E> {


    private static class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data){
            this.data = data;
        }
    }


    private Node <E> head;
    private Node <E> tail;

;
    private int listSize = 0;

    public CircularDoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean addFirst(E e) {

        Node <E> newNode = new Node<E>(e);

        if(e == null){
            return false;
        }

        if(this.listSize == 0){
            this.head = newNode;
            this.head.prev = this.head;
            this.head.next = this.head;
            listSize ++;
            return true;
        }

        if(this.head == null){
            this.head = newNode;
            this.head.prev = this.head;
            this.head.next = this.head;
            listSize ++;
            return true;
        }

        newNode.prev = this.head;
        this.head.next = newNode;
        listSize++;

        return true;
    }

    @Override
    public boolean addLast(E e) {
        return false;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public int size() {

        return listSize;
    }

    @Override
    public boolean removeLast() {
        return false;
    }

    @Override
    public boolean removeFirst() {
        return false;
    }

    @Override
    public boolean insert(int index, E e) {



        return false;
    }

    @Override
    public boolean set(int index, E e) {
        return false;
    }

    @Override
    public boolean isEmpty() {

        if(this.head == null){
            return true;
        }

        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }
}
