public class LinkedQueue<T> implements QueueADT<T> {

    private int count;
    private LinearNode<T> head, tail;

    public LinkedQueue() {
        count = 0;
        head = tail = null;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> node = new LinearNode<T>(element);

        if (isEmpty())
            head = node;
        else
            tail.setNext(node);

        tail = node;
        count++;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new EmptyCollectionException("queue");

        T result = head.getElement();
        head = head.getNext();
        count--;

        if (isEmpty())
            tail = null;
        return result;
    }

    @Override
    public T first() {
        if (isEmpty())
            throw new EmptyCollectionException("queue");
        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        LinearNode<T> current = head;
        for (int i = 0; i < count; i++) {
            sb.append(current.getElement());
            if (i == count-1) return sb.append("]").toString();
            sb.append(", ");
            current = current.getNext();
        }
        return sb.toString();
    }
}