package no.hvl.dat102;

import java.util.ArrayList;

public class Medlemmer {

    private ArrayList<Medlem> medlemmer;

    public Medlemmer() {
        medlemmer = new ArrayList<>();
    }

    public void leggTil(Medlem medlem) {
        medlemmer.add(medlem);
    }

    public Medlem finnPartnerFor(Medlem medlem) {
        for (Medlem other : medlemmer) {
            if (other.getPartner() == null
                    && !medlem.equals(other)
                    && other.getHobbyer().equals(medlem.getHobbyer())) {
                medlem.setPartner(other);
                other.setPartner(medlem);
                return other;
            }
        }
        return null;
    }

    public void tilbakestill() {
        for (Medlem medlem : medlemmer) {
            medlem.setPartner(null);
        }
    }
}
