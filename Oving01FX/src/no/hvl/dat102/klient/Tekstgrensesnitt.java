package no.hvl.dat102.klient;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;

public class Tekstgrensesnitt {

    public Film lesFilm() {
        return null;
    } // bruker javafx i denne oppgaven for å lære noe nytt (brukt scanner i hele forrige semester)

    public void visFilm(Film film) {
        // se gui
    }

    public void skrivUtSokTittel(FilmarkivADT arkiv, String delstreng) {
        // se gui
    }

    public void skrivUtSokProdusent(FilmarkivADT arkiv, String delstreng) {
        // se gui
    }

    /**
     * Skriver ut en enkel statistikk som inneholder antall filmer totalt
     * og hvor mange det er i hver sjanger.
     * @param arkiv
     */
    public void skrivUtStatistikk(FilmarkivADT arkiv) {

        StringBuilder sb = new StringBuilder(
                "\n----------------" +
                "\nFilmer totalt: " + arkiv.antall() +
                "\n----------------\n");

        /**
         * Ineffektiv kode fordi den vil gå igjennom arkivet så mange ganger som det finnes sjangere, nøstet for-løkke.
         * Derfor brukes ikke antallSjanger-metoden her. O(n*antall_sjangre) = O(n*m) = O(n^2)
         */
//        for (Sjanger sjanger : Sjanger.values()) {
//            sb.append(sjanger.toString()).append(": ").append(arkiv.antallSjanger(sjanger)).append("\n");
//        }


        /**
         *  Oppretter array som holder orden på antall filmer i hver sjanger med samordnet index,
         *  går igjennom filmarkivet en gang uavhengig av antall typer sjanger. O(n)
         */
        Film[] filmer = arkiv.hentFilmer();
        int[] teller = new int[Sjanger.values().length];
        for (int i = 0; i < arkiv.antall(); i++) {
            teller[filmer[i].getSjanger().ordinal()]++;
        }

        // print
        for (int i = 0; i < teller.length; i++) {
            sb.append(Sjanger.formatertSjanger(Sjanger.values()[i])).append(": ").append(teller[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
