package no.hvl.dat102.klient;

import no.hvl.dat102.EmptyCollectionException;
import no.hvl.dat102.KjedetStabel;

public class KlientStabel {
	public static void main(String[] args) {

		String str = "netsket re etteD";
		int lengde = str.length();
		KjedetStabel<Character>  tegnStabel = new KjedetStabel<Character>();
		for (int i = 0; i < lengde; i++) {
			tegnStabel.push(str.charAt(i));
		}
		System.out.println(str);
		try {
			while (!tegnStabel.erTom()) {
				Character tegn =  tegnStabel.pop();
				System.out.print(tegn);
			}
			System.out.println();			
		} catch (EmptyCollectionException ex) {
			System.out.println(ex.getMessage());
		}
	}

}// class
