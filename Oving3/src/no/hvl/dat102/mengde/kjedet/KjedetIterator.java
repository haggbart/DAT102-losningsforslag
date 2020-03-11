package no.hvl.dat102.mengde.kjedet;

//****************************************************************

//    Representerer en iterator for en kjedet struktur av noder 
//    kjedet lineært.
//****************************************************************
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ole olsen
 * 
  */

public class KjedetIterator<T> implements Iterator<T> {
	private LinearNode<T> current; // den aktuelle posisjonen.

	/*************************************************************
	 * Lager en iterator (oppramsar)..
	 *************************************************************/
	/**
	 * @param samling
	 */
	public KjedetIterator(LinearNode<T> samling) {
		current = samling;
	}

	/************************************************************
	 * Returnerer sann hvis iteratoren har minst ett element igjen.
	 *************************************************************/
		
	@Override
	public boolean hasNext() {
		return (current != null);
	}

	/*************************************************************
	 * Returnerer neste element hvis det fins.
	 *************************************************************/

	@Override
	public T next() {
		T resultat = null;
		if (!hasNext())
			throw new NoSuchElementException();
		resultat = current.getElement();
		current = current.getNext();
		
		return resultat;
	}

	/*************************************************************
	 * Remove operasjonen er ikke støttet
	 *************************************************************/
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
