package no.hvl.dat102;


import java.io.Serializable;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Vare implements Serializable {
    private static final long serialVersionUID = 1L;

    // a) Instansvariabler
    private int vareNummer;
    private String navn;
    private double pris;
    private int antall;


    // b) Lag tre konstruktører, en uten parametre, en konstruktør med en parameter som skal
    // være varenr, og en konstruktør med fire parametre.

    public Vare() {}

    public Vare(int vareNummer) {
        this.vareNummer = vareNummer;
    }

    public Vare(int vareNummer, String navn, double pris, int antall) {
        this.vareNummer = vareNummer;
        this.navn = navn;
        this.pris = pris;
        this.antall = antall;
    }


    // c) Lag get -og set-metoder for hver objektvariabel.
    public int getVareNummer() {
        return vareNummer;
    }

    public void setVareNummer(int vareNummer) {
        this.vareNummer = vareNummer;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    // d) Lag en metode lesVare som spør brukeren etter navn og pris og leser inn verdier til disse
    // objektvariablene fra tastaturet. Ved forsøk på å tildele en negativ pris, skal brukeren bli varslet og
    // bedt om en ny pris.
    // Vare-objekt må opprettes før man kaller lesVare. Se leggInnNyVare i klassen Butikk
    //senere. Det kan gjøres på andre måter.

    public void lesVare() {
        navn = showInputDialog("Navn på vare:");
        double pris;

        do {
            System.out.print("Pris: ");
            pris = Double.parseDouble(showInputDialog("Pris:"));
            if (pris < 0) {
                showMessageDialog(null, "Pris kan ikke være negativ, prøv igjen.");
            }
        } while (pris < 0);

        this.pris = pris;
    }



    // e) Lag en metode toString()som lager og returnerer en streng basert på objektvariablene.
//    @Override
//    public String toString() {
//        return "Vare{" +
//                "vareNummer=" + vareNummer +
//                ", navn='" + navn + '\'' +
//                ", pris=" + pris +
//                ", antall=" + antall +
//                '}';
//    }

    @Override
    public String toString() {
        return String.format("%6d%15s%10.2f%6d", vareNummer, navn, pris, antall);
    }
}
