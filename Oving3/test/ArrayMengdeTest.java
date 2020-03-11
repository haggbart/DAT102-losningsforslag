import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.array.ArrayMengde;

public class ArrayMengdeTest extends MengdeADTTest {

    @Override
    protected MengdeADT<Integer> reset() {
        return new ArrayMengde<>();
    }
}
