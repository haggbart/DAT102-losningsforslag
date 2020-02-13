

public class CircularArrayQueue<T> implements QueueADT<T> {

    private final static int DEFAULT_CAPACITY = 100;
    private int front, rear, count;
    private T[] queue;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularArrayQueue(int initialCapacity) {
        front = rear = count = 0;
        queue = (T[]) (new Object[initialCapacity]);
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            expandCapacity();


        queue[rear] = element;
        rear = (rear+1) % queue.length;

        count++;
    }

    private void expandCapacity() {
        T[] larger = (T[]) (new Object[queue.length * 2]);

        for (int scan = 0; scan < count; scan++) {
            larger[scan] = queue[front];
            front = (front + 1) % queue.length;
        }

        front = 0;
        rear = count;
        queue = larger;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new EmptyCollectionException("queue");

        T result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;

        count--;
        return result;
    }

    @Override
    public T first() {
        if (isEmpty())
            throw new EmptyCollectionException("queue");

        T result = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;

        count--;
        return result;
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
        int end = count+front;
        for (int i = front; i < end; i++) {
            sb.append(queue[i % queue.length]);
            if (i == end-1) return sb.append("]").toString();
            sb.append(", ");
        }
        return sb.toString();
    }
}