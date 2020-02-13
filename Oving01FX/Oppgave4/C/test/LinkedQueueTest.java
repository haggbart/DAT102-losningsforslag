public class LinkedQueueTest extends QueueADTTest {

    @Override
    protected QueueADT<Integer> reset() {
        return new LinkedQueue<>();
    }
}
