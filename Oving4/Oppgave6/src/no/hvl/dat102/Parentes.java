package no.hvl.dat102;

public class Parentes {

 private char parentes;
    private int linje;
    private int posisjon;

    public Parentes(char parentes, int linje, int posisjon) {
        this.parentes = parentes;
        this.linje = linje;
        this.posisjon = posisjon;
    }

    public char getParentes() {
        return parentes;
    }

    public int getLinje() {
        return linje;
    }

    public int getPosisjon() {
        return posisjon;
    }

    @Override
    public String toString() {
        return String.valueOf(parentes);
    }
}
