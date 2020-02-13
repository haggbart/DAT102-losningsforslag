package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.StabelADT;
import no.hvl.dat102.adt.StabelADTTest;

public class TabellStabelTest extends StabelADTTest {

	@Override
	protected StabelADT<Integer> reset() {
		return new TabellStabel<Integer>();
	}

}
