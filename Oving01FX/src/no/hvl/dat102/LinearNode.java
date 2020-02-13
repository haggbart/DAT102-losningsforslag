package no.hvl.dat102;

public class LinearNode<T> {

    private LinearNode<T> next;
    private T element;

    public LinearNode() {
        next = null;
        element = null;
    }

    public LinearNode(T element) {
        next = null;
        this.element = element;
    }

    public LinearNode<T> getNext() {
        return next;
    }

    public void setNext(LinearNode<T> node) {
        next = node;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
