import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


abstract class QueueADTTest<T> {

    private QueueADT<Integer> queue;

    protected abstract QueueADT<Integer> reset();

    @BeforeEach
    public void setup() {
        queue = reset();
    }

    @Test
    void size() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    @Test
    void testToString() {
    }

    @Test
    void EnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        try {
            assertEquals(1, (int)queue.dequeue());
            assertEquals(2, (int)queue.dequeue());
            assertEquals(3, (int)queue.dequeue());
            assertEquals(4, (int)queue.dequeue());
            assertEquals(5, (int)queue.dequeue());
        } catch (EmptyCollectionException e) {
            fail("dequeue feiltet uventet " + e.getMessage());
        }
    }

    @Test
    void EnqueueAndDequeueWithDuplicates() {
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);

        try {
            assertEquals(1, (int)queue.dequeue());
            assertEquals(1, (int)queue.dequeue());
            assertEquals(3, (int)queue.dequeue());
            assertEquals(3, (int)queue.dequeue());
            assertEquals(3, (int)queue.dequeue());
        } catch (EmptyCollectionException e) {
            fail("dequeue feiltet uventet " + e.getMessage());
        }
    }

    @Test
    void EnqueueDequeueFirst() {
        try {
            queue.enqueue(1);
            queue.dequeue();
            queue.enqueue(2);
            queue.enqueue(3);
            queue.dequeue();
            assertEquals(3, (int)queue.first());
        } catch (EmptyCollectionException e) {
            fail("enqueue eller dequeue og first feilet uventet " + e.getMessage());
        }
    }

    @Test
    void isNotEmpty() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertFalse(queue.isEmpty());
    }

    @Test
    void EnqueueDequeueIsEmpty() {
        try {
            queue.enqueue(1);
            queue.dequeue();
            assertTrue(queue.isEmpty());
        } catch (EmptyCollectionException e) {
            fail("enqueue, dequeue is empty feilet uventet " + e.getMessage());
        }
    }

    @Test
    void DequeueIsEmpty() {
        assertThrows(EmptyCollectionException.class,() -> queue.dequeue());
    }
}