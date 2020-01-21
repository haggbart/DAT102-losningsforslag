package no.hvl.dat102;


// Vi skal nå lage en klasse for butikker. Hver butikk er kjennetegnet av et navn og en tabell med varer.
//Deloppgavene f- o angår klassen Butikk.
//Les gjennom alle deloppgavene før du begynner å svare.
//f) Sett opp objektvariable for klassen Butikk. De skal bare være synlige innenfor klassen.
// Butikk skal ha en referansetabell, varer for objekter av type Vare og en objektvariabel,
// antallTyper som angir antall ulike typer varer.

import java.io.Serializable;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Butikk implements Serializable {
    private static final long serialVersionUID = 1L;

    private int vareId = 0;
    private String navn;
    private Vare[] varer;
    private int antallTyper;


    // g) Lag en konstruktør med butikken sitt navn og maksimalt antall varer som parametre.
    public Butikk(String navn, int maksAntall) {
        this.navn = navn;
        varer = new Vare[maksAntall];
        int antallTyper = 0;
    }


    // h) Lag en metode finnVare(int varenr) som returnerer posisjonen (indeksen) i tabellen der
    // varen fins, eller -1 dersom varen ikke fins.
    public int finnVare(int vareNummer) {
        for (int i = 0; i < antallTyper; i++) {
            if (varer[i].getVareNummer() == vareNummer) return i;
        }
        return -1;
    }

    // i) Lag en metode erLedigPlass() som returnerer sann dersom det er ledig plass i
    // tabellen ellers usann.
    private boolean erLedigPlass() {
        return antallTyper < varer.length;
    }

    /*
    j) Lag en metode leggInnNyVare(int varenr) som legger en ny vare til butikken.
 Etter innsettingen skal tabellen varer være sortert etter stigende varenr.
 Lagerbeholdning for denne varen er 0 (kun klargjøring av vare).
 Dersom varen fins fra før eller lageret er fullt, skal dette meldes til brukeren.
 Prøv først selv. Hvis du ikke får det til, kan du se algoritmen på slutten.
     */

    public void leggInnNyVare(int vareNummer) {
        if (!erLedigPlass()) {
            showInputDialog(null, "Butikken er full, kunne ikke legge til ny vare.");
            return;
        }

        // sørge for unikt varnummer
        while (finnVare(vareNummer) != -1) {
            vareNummer++;
            vareId = vareNummer;
        }

        Vare nyVare = new Vare(vareNummer);

        // algoritme start
        int i = 0;
        while (i < antallTyper && vareNummer > varer[i].getVareNummer()) {
            i++;
        }

        int j = antallTyper;

        while (j > i) {
            varer[j] = varer[j-1];
            j--;
        }
        varer[i] = nyVare;
        nyVare.lesVare();
        antallTyper++;
        // algoritme slutt

        vareId++;
    }

    public void leggInnNyVare() {
        leggInnNyVare(vareId);
    }

    // k) Lag en metode slettVare(int varenr) som sletter en vare fra butikken
    // (varen trekkes tilbake for salg). Dersom varen ikke fins, skal dette meldes til brukeren.
    // Tabellen varer skal være sortert etter uttak av en vare. Prøv først selv.
    // Hvis du ikke får det til, kan du se algoritmen på slutten.
    public void slettVare(int vareNummer) {

        int i = finnVare(vareNummer);
        if (i == -1) {
            showMessageDialog(null, "Fant ikke varen.");
            return;
        }
        antallTyper--;
        while (i < antallTyper) {
            varer[i] = varer[i+1];
            i++;
        }
        varer[antallTyper] = null;
    }

    // l) Lag en metode detaljSalg(int varenr) som reduserer antall på denne varen med 1.
    // Dersom varen ikke er registrert, skal dette meldes til brukeren. Dersom det er 0 stykker igjen på
    // lager skal dette meldes til brukeren.
    public void detaljSalg(int vareNummer) {
        int index = finnVare(vareNummer);
        if (index == -1) {
            showMessageDialog(null, "Fant ikke varen.");
            return;
        }

        if (varer[index].getAntall() == 0) {
            showMessageDialog(null, "Det er ikke flere varer igjen.");
            return;
        }
        varer[index].setAntall(varer[index].getAntall()-1);
    }

    // m) Lag en metode grossInnkjop(int varenr, int ant) som øker antallet på
    // denne varen med parameteren ant. Dersom varen ikke fins, eller dersom ant ikke er positiv skal
    // dette meldes til brukeren.

    public void grossInnkjop(int vareNummer, int antall) {
        int index = finnVare(vareNummer);
        if (index == -1) {
            showMessageDialog(null, "Fant ikke varen.");
            return;
        }
        if (antall < 0) {
            showMessageDialog(null, "Antall varer må være positivt.");
            return;
        }
        varer[index].setAntall(varer[index].getAntall() + antall);
    }

    // n) Lag en metode salgsVerdi() som regner ut total salgsverdi av hele lageret.
    public double salgsVerdi() {
        double verdi = 0;
        for (int i = 0; i < antallTyper; i++) {

            verdi += varer[i].getPris() * varer[i].getAntall();
        }
        return verdi;
    }

    // o) Lag en en metode skrivUtVarer()som skriver opplysninger om alle varene.
    public void skrivUtVarer() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < antallTyper; i++) {
            sb.append(varer[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
