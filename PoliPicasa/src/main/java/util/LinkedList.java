package util;

public class LinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList()
    {
        first=last=null;
        size = 0;
    }

    private class Node<E>
    {
        private E data;
        private Node<E> next;

        public Node(E data)
        {
            this.data = data;
            this.next = null;
        }

    }

    @Override
    public boolean isEmpty() {
        return first == null && last ==null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean addFirst(E e) {
        if(e == null) {
            return false;
        }
        Node<E> node = new Node<>(e);
        if(isEmpty()){
            first = last = node;
        }
        else{
            node.next = first;
            first = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if(e== null){
            return false;
        }
        Node<E> node = new Node(e);
        if(isEmpty()) first = last = node;
        else{
            last.next = node;
            last = node;
        }
        size++;
        return true;
    }

    @Override
    public E getFirst() {
        if (isEmpty()){
            return null;
        }
        else{
            return first.data;
        }
    }

    @Override
    public E getLast() {
        if (isEmpty()){
            return null;
        }
        else{
            return last.data;
        }
    }

    @Override
    public int indexOf(E e) {
        if(e==null) {
            return -1;
        }
        int i = 0;
        for(Node<E> node = first; node!=null;node=node.next){
            if(node.data.equals(e)){
                return i;
            }
            i++;
        }
        return -1;
    }


    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }
        last.data=null;
        if(first == last){
            first = last = null;
        }
        else{
            Node<E> prev = getPrevious(last);
            prev.next = null;
            last = prev;
        }
        size --;
        return true;
    }

    private Node<E> getPrevious(Node<E> node)
    {
        if(node == first){
            return null;
        }
        Node<E> n = first;
        while(n!=null && n.next!=node){
            n = n.next;
        }
        return n;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()) {
            return false;
        }
        if(first == last)
        {
            first.data = null;

        }
        else
        {
            Node<E> node = first;
            first = first.next;
            node.data = null;
            node.next = null;
        }
        size--;
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if(e==null) {
            return false;
        }
        if(index>=size || index<0){
            return false;
        }
        if(index == 0) {
            return addFirst(e);
        }
        if(index == size-1){
            return addLast(e);
        }
        Node<E> node = new Node(e);
        Node<E> prev = getNode(index-1);
        Node<E> sig  = prev.next;
        prev.next= node;
        node.next = sig;
        return true;
    }
    public Node<E> getNode(int index){
        Node<E> node = first;
        for(int i = 0; i<index; i++){
            node= node.next;
        }
        return node;
    }

    @Override
    public boolean set(int index, E e) {
        if(e==null){
            return false;
        }
        if(index>=size || index<0) {
            return false;
        }
        if(index==size-1){
            last.data = e;
        }
        else{
            Node<E> node = getNode(index);
            node.data = e;
        }
        return true;
    }


    @Override
    public E get(int index) {
        if(index<0 || index>= size) {
            throw new IndexOutOfBoundsException("El indice esta fuera del limite");
        }
        if(index == 0) {
            return first.data;
        }
        if(index == size -1){
            return last.data;
        }
        else{
            Node<E> node = getNode(index);
            return node.data;
        }
    }

    @Override
    public boolean contains(E e) {
        if(e==null) {
            return false;
        }
        for(Node<E> node = first; node!=null;node=node.next){
            if(node.data.equals(e)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(index<0 || index>size-1){
            return false;
        }
        if(index==0) {
            return removeFirst();
        }
        if(index==size-1){
            return removeLast();
        }
        else{
            Node<E> node = getNode(index-1);
            Node<E> elim = node.next;
            Node<E> sig = elim.next;
            elim.data=null;
            elim.next = null;
            node.next = sig;
            return true;
        }
    }

}

