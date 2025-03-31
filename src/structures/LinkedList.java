package structures;

import java.util.NoSuchElementException;

public class LinkedList <T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty(){
        boolean empty = false;
        if (first == null){
            empty = true;
        }
        return empty;
    }

    public int size(){
        return size;
    }

    public void addFirst(T element){
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
        size++;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()){
            first = newNode;
        } else {
            Node<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public void addAt(T element, int index){

        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> newNode = new Node<>(element);

        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);

        size++;
    }

    public void removeFirst() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        } else {
            Node<T> temp = first;
            first = first.getNext();
            temp.setNext(null);
            size--;
        }
    }

    public void removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        } else {

            Node<T> current = first;
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }
            current.setNext(null);
            size--;
        }
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            first = first.getNext();

            if (first == null) {
                last = null;
            }
        } else {

            Node<T> previous = first;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }
            Node<T> nodeToRemove = previous.getNext();
            previous.setNext(nodeToRemove.getNext());
            if (nodeToRemove == last) {
                last = previous;
            }
        }
        size--;
    }

    public boolean contains(T element) {
        boolean found = false;
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }
        Node<T> current = first;
        while (current.getNext() != null) {
            if (current.getData().equals(element)) {
                found = true;
            }
            current = current.getNext();
        }
        return found;
    }
}
