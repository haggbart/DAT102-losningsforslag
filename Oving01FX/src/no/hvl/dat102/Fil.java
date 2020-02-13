package no.hvl.dat102;

import no.hvl.dat102.adt.FilmarkivADT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fil {
    private final static String SKILLE = "#";


    /**
     * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
     *  The try-with-resources statement ensures that each resource is closed at the end of the statement.
     *  Any object that implements java.lang.AutoCloseable,
     *  which includes all objects which implement java.io.Closeable, can be used as a resource.
     *
     * @param filmarkiv
     * @param filnavn
     * @return returnerer filmarkiv lest fra fil
     */
    public static FilmarkivADT lesFraFil(FilmarkivADT filmarkiv, String filnavn) {

        // fordi bufferedreader implementerer autocloseable og blir instansiert i try with resource,
        // blir den lukket automatisk uavhengig om try er vellykket eller ikke
        try (BufferedReader reader = new BufferedReader(new FileReader(filnavn))) {
            String line;
            filmarkiv = new Filmarkiv2();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SKILLE);
                int filmNummer = Integer.parseInt(data[0]);
                String produsent = data[1];
                String tittel = data[2];
                Sjanger sjanger = Sjanger.finnSjanger(data[3]);
                int lansering = Integer.parseInt(data[4]);
                Film film = new Film(filmNummer, produsent, tittel, sjanger, lansering);
                filmarkiv.leggTil(film);
                System.out.println(film);
            }
            return filmarkiv;
        } catch (IOException e) {
            System.out.println("Feil ved lasting av filmarkiv fra fil:\n");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean skrivTilFil(FilmarkivADT filmarkiv, String filnavn) {
        Film[] filmer = filmarkiv.hentFilmer();
        try (FileWriter writer = new FileWriter(filnavn)) {
//            writer.write(filmarkiv.antall() + "\n");
            for (int i = 0; i < filmarkiv.antall(); i++) {
                writer.write(filmer[i].getFilmNummer() + SKILLE + filmer[i].getProdusent() + SKILLE + filmer[i].getTittel() +
                        SKILLE + filmer[i].getSjanger() + SKILLE + filmer[i].getLansering() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Feil ved skriving av filmarkiv til fil:\n");
            e.printStackTrace();
            return false;
        }
    }
}
