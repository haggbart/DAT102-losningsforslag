package no.hvl.dat102.stack;

import java.util.Arrays;

public class ArrayStack<T> implements StackADT<T> {

    private final static int DEFAULT_CAPACITY = 100;

    private int top;
    private T[] stack;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[])(new Object[initialCapacity]);
    }

    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity();
        }

        stack[top] = element;
        top++;
    }

    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        top--;
        T result = stack[top];
        stack[top] = null;
        return result;
    }

    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("stack");
        }
        return stack[top-1];
    }

    private void expandCapacity() {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < top; i++) {
            sb.append(stack[i]);
            if (i == top-1) return sb.append("]").toString();
            sb.append(", ");
        }
        return sb.toString();
    }
}
