package no.hvl.dat102.mengde.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;

public class ArrayMengde<T> implements MengdeADT<T> {

	private final static Random RANDOM = new Random();
	private final static int DEFAULT_SIZE = 100;
	private int antall;
	private T[] array;
	private Iterator<T> iterator;

	public ArrayMengde() {
		this(DEFAULT_SIZE);
	}

	public ArrayMengde(int start) {
		antall = 0;
		array = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == array.length) {
				utvidKapasitet();
			}
			array[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * array.length]);
		for (int i = 0; i < array.length; i++) {
			hjelpetabell[i] = array[i];
		}
		array = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = RANDOM.nextInt(antall);
		svar = array[indeks];
		array[indeks] = array[antall - 1];
		antall--;

		return svar;
	}

	@Override
	public T fjern(T element) {
		// Søker etter og fjerner element. Returnerer null-ref ved ikke-funn

		if (erTom())
			throw new EmptyCollectionException("mengde");

		T current;

		iterator = iterator();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.equals(element)) {
				iterator.remove();
				antall--;
				return current;
			}
		}
		return null;
	}

	@Override
	public boolean inneholder(T element) {
		for (int i = 0; (i < antall); i++) {
			if (array[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof MengdeADT)) {
			return false;
		}

		var m = (MengdeADT<T>)other;

		if (antall != m.antall()) {
			return false;
		}
		iterator = m.iterator();
		while (iterator.hasNext()) {
			if (!inneholder(iterator.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> other) {
		Iterator<T> teller = other.iterator();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	public void settInnAlle(MengdeADT<T> other) {
		Iterator<T> teller = other.iterator();
		while (teller.hasNext()) {
			settInn(teller.next());
		}
	}

	/*
	 * Denne versjonen av unionen er lite effekktiv
	 * 
	 * @Override public MengdeADT<T> union(MengdeADT<T> m2) { TabellMengde<T> begge
	 * = new TabellMengde<T>(); for (int i = 0; i < antall; i++) {
	 * begge.leggTil(tab[i]); } Iterator<T> teller = m2.oppramser();
	 * 
	 * while (teller.hasNext()) { begge.leggTil(teller.next()); } return
	 * (MengdeADT<T>)begge; }
	 */
	@Override

	public MengdeADT<T> union(MengdeADT<T> other) {

		var union = new ArrayMengde<T>();

		union.settInnAlle(this);
		union.leggTilAlle(other);

		return union;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> other) {

		var snitt = new ArrayMengde<T>();

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

		var komplement = new ArrayMengde<T>();

		iterator = iterator();
		T current;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (!other.inneholder(current)) {
				komplement.settInn(current);
			}
		}
		return komplement;
	}

	@Override
	public boolean delmengde(MengdeADT<T> other) {
		boolean erUnderMengde = true;
		// Fyll ut
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator<T>(array, antall);
	}

	private void settInn(T element) {
		if (antall == array.length) {
			utvidKapasitet();
		}
		array[antall] = element;
		antall++;
	}

	@Override
	public String toString() {
//		var sb = new StringBuilder();
//		if (antall == 0) {
//			return "[]";
//		}
//		iterator = iterator();
//		while (iterator.hasNext()) {
//			sb.append(iterator.next()).append(", ");
//		}
//		return sb.toString();
		if (erTom()) return "[]";
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < antall; i++) {
			sb.append(array[i]);
			if (i == antall-1) return sb.append("]").toString();
			sb.append(", ");
		}
		return sb.toString();
	}

}
