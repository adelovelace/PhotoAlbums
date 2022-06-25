package util;

public class CircularDoublyLinkedList<E> implements List<E> {


    private static class Node<E> {

        private Object data;
        private Node<E> next;
        private Node<E> prev;

        public Node(Object data, Node<E> next, Node<E> prev){
            this.data = data;
            this.next = next;
            this.prev = prev;

        }
    }

    @Override
    public boolean addFirst(E e) {
        return false;
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
        return 0;
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
