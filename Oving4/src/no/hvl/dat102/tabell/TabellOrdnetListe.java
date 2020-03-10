package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int antall;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		antall = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");


		antall--;
		T resultat = liste[antall];
		liste[antall] = null;

		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		for (int i = 0; i < antall - 1; i++) {
			liste[i] = liste[i+1];
		}
		antall--;
		liste[antall] = null;
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		
		T resultat = null;
		// ...Fyll ut

		return resultat;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public void leggTil(T element) {

		if (erTom()) {
			liste[0] = element;
			antall++;
			return;
		}

		if (antall == liste.length) {
			utvid();
		}

		int i = 0;
		while (i < antall && liste[i].compareTo(element) < 0) {
			i++;
		}

		int j = antall;

		while (j > i) {
			liste[j] = liste[j-1];
			j--;
		}

		liste[i] = element;
		antall++;
	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		if (erTom()) return null;
		int index = finn(element);
		if (index == -1) return null;
		antall--;
		for (int i = index; i < antall; i++) {
			liste[i] = liste[i+1];
		}

		return element;
	}

	private int finn(T element) {
		for (int i = 0; i < antall; i++) {
			if (liste[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		var sb = new StringBuilder();

		for (int i = 0; i < antall; i++) {
			sb.append(liste[i]).append(", ");
		}
		return sb.toString();
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
