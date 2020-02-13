package no.hvl.dat102;

import no.hvl.dat102.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {


    private Film[] filmer;
    private int antall;

    public Filmarkiv() {
        this(10);
    }

    public Filmarkiv(int storrelse) {
        filmer = new Film[storrelse];
    }

    @Override
    public Film[] hentFilmer() {
        return filmer;
    }

    @Override
    public void leggTil(Film film) {
        if (antall == filmer.length) {
            utvidKapasitet();
        }
        filmer[antall] = film;
        antall++;
    }

    /**
     * øker array-lengden med 50% slik som arraylist.
     * 10% blir veldig lite og ville ført til at metoden blir utført unødvendig ofte
     */
    private void utvidKapasitet() {
        Film[] utvidetArkiv = new Film[filmer.length + (filmer.length >> 1)];
        for (int i = 0; i < antall; i++) {
            utvidetArkiv[i] = filmer[i];
        }
        filmer = utvidetArkiv;
    }

    private Film[] trim(Film[] filmer, int antall) {
        Film[] trimmed = new Film[antall];
        for (int i = 0; i < antall; i++) {
            trimmed[i] = filmer[i];
        }
        return trimmed;
    }

    @Override
    public boolean slett(int filmNummer) {
        for (int i = 0; i < antall; i++) {
            if (filmer[i].getFilmNummer() == filmNummer) {
                antall--;
                filmer[i] = filmer[antall];
                filmer[antall] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Film[] sokTittel(String deltekst) {
        Film[] result = new Film[antall]; // maks størrelse
        int treff = 0;
        for (int i = 0; i < antall; i++) {
            if (filmer[i].getTittel().toLowerCase().contains(deltekst.toLowerCase())) {
                result[treff] = filmer[i];
                treff++;
            }
        }
        return trim(result, treff);
    }

    @Override
    public Film[] sokProdusent(String deltekst) {
        Film[] result = new Film[antall]; // maks størrelse
        int treff = 0;
        for (int i = 0; i < antall; i++) {
            if (filmer[i].getProdusent().toLowerCase().contains(deltekst.toLowerCase())) {
                result[treff] = filmer[i];
                treff++;
            }
        }
        return trim(result, treff);
    }

    @Override
    public int antallSjanger(Sjanger sjanger) {
        int antall = 0;
        for (int i = 0; i < this.antall; i++) {
            if (filmer[i].getSjanger() == sjanger) antall++;
        }
        return antall;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public String toString() {
        // bruker stringbuilder her pga for-loop. kompileren bruker stringbuilder automatisk, men ikke i loops
        StringBuilder sb = new StringBuilder("Filmarkiv{" +
                "antallFilmer=" + antall +
                "}\n");
        for (int i = 0; i < antall; i++) {
            sb.append(filmer[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
