package no.hvl.dat102.mengde.kjedet;

import java.util.*;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random random = new Random();
	private int antall;
	private LinearNode<T> hode;
	private Iterator<T> iterator;

	public KjedetMengde() {
		antall = 0;
		hode = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNext(hode);
			hode = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> other) {
		Iterator<T> teller = other.iterator();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	public void settInnAlle(MengdeADT<T> other) {
		Iterator<T> teller = other.iterator();
		while (teller.hasNext()) {
			settInn(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat;

		int valg = random.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = hode.getElement();
			hode = hode.getNext();
		} else {
			forgjenger = hode;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNext();
			}
			aktuell = forgjenger.getNext();
			resultat = aktuell.getElement();
			forgjenger.setNext(aktuell.getNext());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");

		var previous = new LinearNode<T>();
		LinearNode<T> current = hode;

		while (current != null) {
			if (current.getElement().equals(element)) {

				if (antall == 1) {
					hode = null;
				} else if (current == hode) {
					hode = current.getNext();
				} else {
					previous.setNext(current.getNext());
				}
				antall--;
				return current.getElement();
			}
			previous = current;
			current = current.getNext();
		}
		return null;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = hode;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNext();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof MengdeADT)) {
			return false;
		}

		var other = (MengdeADT<T>)object;

		if (antall != other.antall()) {
			return false;
		}

		iterator = other.iterator();
		while (iterator.hasNext()) {
			if (!inneholder(iterator.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> other) {

		var union = new KjedetMengde<T>();

		union.settInnAlle(this);
		union.leggTilAlle(other);

		return union;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> other) {

		var snitt = new KjedetMengde<T>();

		T current;
		iterator = other.iterator();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (inneholder(current)) {
				snitt.settInn(current);
			}
		}
		return snitt;
	}

	@Override
	public MengdeADT<T> komplement(MengdeADT<T> other) {
		var komplement = new KjedetMengde<T>();

		LinearNode<T> current = hode;
		while (current != null) {
			if (!other.inneholder(current.getElement())) {
				komplement.leggTil(current.getElement());
			}
			current = current.getNext();
		}
		return komplement;
	}

	@Override
	public boolean delmengde(MengdeADT<T> other) {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new KjedetIterator<T>(hode);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNext(hode);
		hode = nyNode;
		antall++;
	}

}
