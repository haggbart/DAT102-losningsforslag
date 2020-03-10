package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.*;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = foerste.getElement();
		foerste = foerste.getNeste();
		antall--;

		if (erTom()) {
			siste = null;
		}

		return resultat;
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();
		antall--;
		if (antall == 0) {
			foerste = null;
			siste = null;
		} else {
			siste = foerste;
			for (int i = 1; i < antall; i++) {
				siste = siste.getNeste();
			}
			siste.setNeste(null);
		}
		return resultat;

		/*T resultat = siste.getElement();
		LinearNode<T> forrige = new LinearNode<>();
		LinearNode<T> current = foerste;
		while (current != null) {
			if (current.getNeste() == null) {
				siste = forrige;
				siste.setNeste(null);
				break;
			}
			forrige = current;
			current = current.getNeste();
		}
		antall--;

		if (erTom()) {
			foerste = null;
		}

		return resultat;*/
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T svar = foerste.getElement();

		return svar;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		return resultat;
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
	public void leggTil(T element) {

		LinearNode<T> nyNode = new LinearNode<>(element);

		if (erTom()) {
			foerste = siste = nyNode;
		}

		else if (foerste.getElement().compareTo(element) >= 0) {
			nyNode.setNeste(foerste);
			foerste = nyNode;
		}

		else if (element.compareTo(siste.getElement()) >= 0) {
			siste.setNeste(nyNode);
			siste = nyNode;
		} else {

			LinearNode<T> forrige = new LinearNode<>();
			LinearNode<T> current = foerste;
			while (current != null) {
				if (current.getElement().compareTo(element) >= 0) {
					forrige.setNeste(nyNode);
					nyNode.setNeste(current);
					break;
				} else {
					forrige = current;
					current = current.getNeste();
				}
			}
		}
		antall++;
	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // F�rste element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		var current = foerste;
		while (current != null) {
			sb.append(current.getElement()).append(", ");
			current = current.getNeste();
		}
		return sb.toString();
	}
}// class
