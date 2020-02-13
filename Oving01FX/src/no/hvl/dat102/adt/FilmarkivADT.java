package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public interface FilmarkivADT {

    /**
     *
     * @return returnerer en array av filmer i arkivet
     */
    Film[] hentFilmer();


    /**
     * Legger til en ny Film
     * @param nyFilm filmen som skal legges til i akrivet
     */
    void leggTil(Film nyFilm);


    /**
     * Sletter en film hvis den fins
     * @param filmNummer nummeret på filmen som skal slettes
     * @return true om filmen er slettet
     */
    boolean slett(int filmNummer);


    /**
     * Søker og henter Filmer med gitt delstreng
     * @param delstreng
     * @return en array av filmer
     */
    Film[] sokTittel(String delstreng);


    /**
     * Søker og henter filmer laget av produsentene i samsvar med delstrengen
     * @param delstreng
     * @return en array av filmer
     */
    Film[] sokProdusent(String delstreng);


    /**
     *
     * @param sjanger
     * @return antall filmer av en gitt sjanger
     */
    int antallSjanger(Sjanger sjanger);


    /**
     *
     * @return totalt antall filmer i arkivet
     */
    int antall();
}
