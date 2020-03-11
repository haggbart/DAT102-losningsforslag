package no.hvl.dat102;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.array.ArrayMengde;

/*
medlemmets navn (som er forskjellig for hvert medlem).
 hobbyer som er referanse til et objekt av klassen KjedetMengde eller TabellMengde
 statusIndeks som angir indeks til partneren i medlemstabellen dersom medlemmet er
“koblet”, ellers er den lik -1.
 */

public class Medlem {
    private String navn;
    private MengdeADT<Hobby> hobbyer;
    private Medlem partner;

    public Medlem(String navn) {
        this.navn = navn;
        hobbyer = new ArrayMengde<>();
        partner = null;
    }

    public boolean passerTil(Medlem other) {
        return hobbyer.equals(other.hobbyer);
    }

    public String getNavn() {
        return navn;
    }

    public MengdeADT<Hobby> getHobbyer() {
        return hobbyer;
    }

    public Medlem getPartner() {
        return partner;
    }

    public void setPartner(Medlem partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {

        return navn + ": " + hobbyer;
    }
}