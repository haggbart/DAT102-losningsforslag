package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.StabelADT;
import no.hvl.dat102.adt.StabelADTTest;

public class KjedetStabelTest extends StabelADTTest {
	   @Override
		protected StabelADT<Integer> reset() {
			return new KjedetStabel<Integer>();
		}		
}
