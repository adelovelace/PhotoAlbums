package util;

import java.io.Serializable;

public class CircularDoublyLinkedList<E> implements List<E>, Serializable {


    private static class Node<E> implements Serializable {

        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data){
            this.data = data;
        }
    }


    private Node <E> head;
    private Node <E> tail;

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
            this.tail = newNode;
            this.listSize ++;
            return true;
        }

        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
            this.listSize ++;
            return true;
        }

        newNode.next = this.head;
        newNode.prev = this.tail;
        this.head.prev = newNode;
        this.tail.next = newNode;
        this.tail= newNode;

        this.listSize++;

        return true;
    }

    @Override
    public boolean addLast(E e) {

        Node <E> newNode = new Node<E>(e);

        if(e ==null){
            return false;
        }

        if(head==null){
            head= newNode;
            tail=newNode;
            this.listSize = 1;
            return true;
        }

        newNode.prev = tail;
        newNode.next = head;
        tail.next = newNode;
        head.prev = newNode;
        tail = newNode;
        this.listSize ++;

        return true;

    }

    @Override
    public E getFirst() {


        if(this.listSize == 0){
            return null;
        }

        if(this.head == null){
            return null;
        }

        return this.head.data;
    }

    @Override
    public E getLast() {

        if(this.listSize == 0){
            return null;
        }

        if(this.tail == null){
            return null;
        }

        return this.tail.data;
    }

    @Override
    public int indexOf(E e) {

        int index = 0;

        if(e == null){
            return index;
        }

        for(int i = 0; i < this.listSize; i++){
            if(this.head.data == e){
                index = i;
            }
        }

        return index;
    }

    @Override
    public int size() {

        return this.listSize;
    }

    @Override
    public boolean removeLast() {



        if(this.listSize == 0){
            return false;
        }

        if(this.tail == null){
            return false;
        }

        this.head.prev = this.tail.prev;
        this.tail.prev = this.tail.next;
        this.tail.prev = this.tail;
        this.listSize --;

        return true;
    }

    @Override

    public boolean removeFirst() {

        if(this.listSize == 0){
            return false;
        }

        if(this.head == null){
            return false;
        }

        this.tail.next = this.head.next;
        this.head.next = this.head.prev;
        this.head.next = this.head;
        this.listSize --;

        return true;

    }

    @Override
    public boolean insert(int index, E e) {

        Node <E> newNode = new Node<>(e);

        if(e == null){
            return false;
        }

        if(index < 0 || index >= this.listSize){
            throw new IndexOutOfBoundsException("Indice no está dentro del rango");
        }

        for (int i = 0; i < this.listSize; i++){

            if(index ==0){
                return addFirst(newNode.data);
            }

            if(index == this.listSize-1){
                return addLast(newNode.data);
            }

            if(index == i){
                Node <E> actualNode = new Node<>(get(i));
                newNode.prev = actualNode.prev;
                actualNode.prev = newNode;
                newNode.next = actualNode;
                this.listSize++;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean set(int index, E e) {

        if(e == null){
            return false;
        }

        if(index < 0 || index >= this.listSize){
            throw new IndexOutOfBoundsException("Indice no está dentro del rango");
        }

        for (int i = 0; i < this.listSize; i++){

            if(index ==0){
                return addFirst(e);
            }

            if(index == this.listSize-1){
                return addLast(e);
            }

            if(index == i){
                Node <E> actualNode = new Node<>(get(index));
                actualNode.data = e;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {

        if(this.listSize == 0){
            return true;
        }

        return false;
    }

    @Override
    public E get(int index) {

        if(index < 0 || index >= this.listSize){
            throw new IndexOutOfBoundsException("Indice no está dentro del rango");
        }

        Node <E> actualNode = this.head;

        for (int i = 0; i < this.listSize; i++){

            if(index == i){
                return actualNode.data;
            }

            actualNode = actualNode.next;
        }

        return null;
    }

    @Override
    public boolean contains(E e) {

        Node actualNode = this.head;

        if(e == null){
            return false;
        }

        do{

            if(actualNode.data.equals(e)){
                return true;
            }

            actualNode = actualNode.next;

        }while(actualNode != this.head);

        return false;
    }

    @Override
    public boolean remove(int index) {

        if(index < 0 || index >= this.listSize){
            throw new IndexOutOfBoundsException("Indice no está dentro del rango");
        }

        for (int i = 0; i < this.listSize; i++){

            if(index ==0){
                return removeFirst();
            }

            if(index == this.listSize-1){
                return removeLast();
            }

            if(index == i){
                Node <E> actualNode = new Node<>(get(index));
                Node <E> temNode = actualNode.next;
                actualNode.prev = actualNode.next;
                actualNode.next = temNode.prev;
                this.listSize--;
                return true;
            }
        }

        return false;
    }
}

