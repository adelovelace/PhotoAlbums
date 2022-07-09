package util;

import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<E> implements List<E>, Iterable<E> {

    private E[] array;
    private int capacity;
    private int current;

    public ArrayList() {
        capacity = 10;
        array = (E[]) new Object[capacity];
        current = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < current && current >= 0;
            }

            @Override
            public Object next() {
                return array[i++];
            }

        };
        return it;
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index >= current) throw new IndexOutOfBoundsException("El indice está fuera del límite");
        ListIterator<E> lit = new ListIterator() {
            int i = index;

            @Override
            public boolean hasNext() {
                return i < current && current >= 0;
            }

            @Override
            public Object next() {
                return array[i++];
            }

            @Override
            public boolean hasPrevious() {
                return i >= 0 && current != 0;
            }

            @Override
            public Object previous() {
                return array[i--];
            }

            @Override
            public int nextIndex() {
                if (hasNext()) return i + 1;
                return -1;
            }

            @Override
            public int previousIndex() {
                if (hasPrevious()) return i - 1;
                return -1;
            }

            @Override
            public void remove() {
                for (int j = i; j < size() - 1; j++) {
                    array[j] = array[j + 1];
                }
                current--;
            }

            @Override
            public void set(Object arg0) {
                array[i] = (E) arg0;

            }

            @Override
            public void add(Object arg0) {
                if (size() == capacity) addCapacity();
                for (int j = i; j < current; j++) {
                    array[j + 1] = array[j];
                }
                array[i] = (E) arg0;
                current++;
            }

        };
        return lit;
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
        current = 0;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }

        if (current == 0) {
            array[0] = e;
            current = 1;
            return true;
        }

        if (current == capacity) {
            addCapacity();
        }

        for (int i = current - 1; i > 0; i--) {
            array[i + 1] = array[i];
        }
        array[0] = e;
        current++;
        return true;
    }

    private void addCapacity() {
        capacity = capacity * 2;
        E[] array2 = (E[]) new Object[capacity];
        for (int i = 0; i < current; i++) {
            array2[i] = array[i];
        }
        array = array2;
    }

    private void reduceCapacity() {
        E[] tmp = (E[]) new Object[(capacity * 2) / 3];
        for (int i = 0; i < current; i++) tmp[i] = array[i];
        array = tmp;
        capacity = capacity * 2 / 3;
        System.out.println(capacity);

    }

    @Override
    public boolean addLast(E e) {

        if (e == null) return false;
        if (current == capacity) addCapacity();
        array[current++] = e;
        return true;
    }

    @Override
    public E getFirst() {
        return array[0];

    }

    @Override
    public E getLast() {

        return array[current - 1];

    }

    @Override
    public int indexOf(E e) {

        if (e == null) new NullPointerException();
        if (isEmpty()) return -1;
        else {
            for (int i = 0; i < current - 1; i++) {
                if (array[i].equals(e)) return i;
            }
            return -1;
        }

    }

    @Override
    public int size() {

        return current;

    }

    @Override
    public boolean removeLast() {

        if (isEmpty()) return false;
        array[current - 1] = null;
        current--;
        if ((capacity * 2 / 3) > 10 && current < (capacity * 2) / 3) reduceCapacity();
        return true;

    }

    @Override
    public boolean removeFirst() {

        if (isEmpty()) return false;
        array[0] = null;
        for (int i = 0; i < current - 1; i++) {
            array[i] = array[i + 1];
        }
        removeLast();
        return true;

    }

    @Override
    public boolean insert(int index, E e) {

        if (isEmpty() || e == null) return false;
        if (index > current - 1 || index < 0) throw new IndexOutOfBoundsException("Index Out of Bound");
        if (current == capacity) addCapacity();
        for (int i = index; i < current; i++) {
            array[i + 1] = array[i];
        }
        array[index] = e;
        current++;
        return true;

    }

    @Override
    public boolean set(int index, E e) {

        if (e == null || index > current - 1) return false;
        array[index] = e;
        return true;

    }

    @Override
    public boolean isEmpty() {

        return current == 0;

    }

    @Override
    public E get(int index) {

        if (index > current - 1) throw new IndexOutOfBoundsException("The index is out of Bound");
        return array[index];

    }

    @Override
    public boolean contains(E e) {

        for (int i = 0; i < current; i++) {
            if (array[i].equals(e))
                return true;
        }

        return false;
    }

    @Override
    public boolean remove(int index) {

        if (index > current - 1) throw new IndexOutOfBoundsException("The index is out of Bound");
        if (index == 0) return removeFirst();
        if (index == current - 1) return removeLast();
        if (isEmpty()) return false;
        else {
            for (int i = index; i <= current - 2; i++) {
                array[i] = array[i + 1];
            }
            return removeLast();
        }
    }

}
