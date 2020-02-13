public class CircularArrayQueueTest extends QueueADTTest {


    @Override
    protected QueueADT<Integer> reset() {
        return new CircularArrayQueue<>();
    }
}
