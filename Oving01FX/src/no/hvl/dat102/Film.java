package no.hvl.dat102;
/*
Data:
 Et entydig dat102.Film-nummer (heltall, ingen krav om bestemte nummerserier e.l.)
 Navn på produsent(filmskaper)
 Tittel på dat102.Film
 År for lansering (heltall)
 dat102.Sjanger () av type enum. Se på slutten av oppgaven om enum.
 Filmselskap
 */

public class Film {
    private int filmNummer;
    private String produsent;
    private String tittel;
    private Sjanger sjanger;
    private int lansering;

    /*
    Konstruktører:
 opprette en "tom" dat102.Film
 opprette en ny dat102.Film med de data som er gitt ovenfor
     */

    public Film() {}

    public Film(int filmNummer, String produsent, String tittel, Sjanger sjanger, int lansering) {
        this.filmNummer = filmNummer;
        this.produsent = produsent;
        this.tittel = tittel;
        this.sjanger = sjanger;
        this.lansering = lansering;
    }

    public int getFilmNummer() {
        return filmNummer;
    }

    public String getProdusent() {
        return produsent;
    }

    public String getTittel() {
        return tittel;
    }

    public Sjanger getSjanger() {
        return sjanger;
    }

    public int getLansering() {
        return lansering;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmNummer=" + filmNummer +
                ", produsent='" + produsent + '\'' +
                ", tittel='" + tittel + '\'' +
                ", sjanger=" + sjanger +
                ", lansering=" + lansering +
                '}';
    }
}
