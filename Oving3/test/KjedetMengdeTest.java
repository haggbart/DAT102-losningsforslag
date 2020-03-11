import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class KjedetMengdeTest extends MengdeADTTest {

    @Override
    protected MengdeADT<Integer> reset() {
        return new KjedetMengde<>();
    }
}
