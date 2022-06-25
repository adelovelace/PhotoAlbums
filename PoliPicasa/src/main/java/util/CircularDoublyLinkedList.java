package util;



import java.util.List;



public class CircularDoublyLinkedList<E> implements List<E> {


    private static class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data, Node<E> next, Node<E> prev){
            this.data = data;
            this.next = next;
            this.prev = prev;

        }
    }


    private Node <E> head;
;
    private int listSize;

    public CircularDoublyLinkedList( Node<E> head) {
        this.head = head;
    }


    // Intenta insertar el elemento al inicio y retorna verdadero si lo logró hacer, falso sino
    public boolean addFirst(E e){
        return false;
    }

    // Intenta insertar el elemento al final y retorna verdadero si lo logró hacer, falso sino
    public boolean addLast(E e){
        return false;
    }

    // Retorna el primer elemento. Lanza una excepción si la lista está vacía.
    public E getFirst(){
        return  null;
    }

    // Retorna el último elemento. Lanza una excepción si la lista está vacía.
    public E getLast(){
        return null;
    }

    // Retorna la posición de E si se encuentra en la Lista. Retorna -1 si no lo encuentra.
    public int indexOf(Object e){
        return 0;
    }

    // Retorna la cantidad de elementos en la lista.
    public int size(){
        return 0;
    }


    // Remueve el último elemento de la lista y retorna verdadero. Retorna falso si la lista estaba vacía antes de la remoción.
    public boolean removeLast(){
        return false;
    }

    // Remueve el primer elemento de la lista y retorna verdadero. Retorna falso si la lista estaba vacía antes de la remoción.
    public boolean removeFirst(){
        return false;
    }

    // Inserta el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    public boolean insert(int index, E e){
        return false;
    }

    // Modifica el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    public E set(int index, E e){
        return null;
    }

    // Retorna verdero si la lista está vacía y falso si contiene nodos.
    public boolean isEmpty(){
        return false;
    }

    // Recorre la lista para retornar el elemento en el índice indicado.
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    public E get(int index){
        return null;
    }

    // Retorna verdadero si el elemento se encuentra en la Lista y falso sino.
    //  También retorna falso si el elemento es null.
    public boolean contains(Object e){
        return false;
    }

    // Remueve el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    public boolean remove(int index){
        return false;
    }


}
