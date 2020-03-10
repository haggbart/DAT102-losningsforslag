package no.hvl.dat102;

import no.hvl.dat102.kjedet.KjedetOrdnetListe;

public class Main {
    public static void main(String[] args) {
        var liste = new KjedetOrdnetListe<Integer>();
        liste.leggTil(1);
        System.out.println(liste);
        liste.leggTil(2);
        System.out.println(liste);
        liste.leggTil(3);
        System.out.println(liste);
        liste.leggTil(10);
        System.out.println(liste);
        liste.leggTil(5);
        System.out.println(liste);
        liste.leggTil(4);

        System.out.println(liste);
    }
}
