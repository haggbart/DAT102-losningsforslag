package no.hvl.dat102;

import no.hvl.dat102.stack.ArrayStack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Balansering {

    private ArrayStack<Parentes> parenteser = new ArrayStack<>();

    private final char[] venstreParenteser = {'(', '[', '{'};
    private final char[] hoyreParenteser = {')', ']', '}'};

    private boolean passer(char aapent, char lukket) {

        for (int i = 0; i < venstreParenteser.length; i++) {
            if (venstreParenteser[i] == aapent)
                return hoyreParenteser[i] == lukket;
        }
        return false;
    }

    public void foretaBalansering(String streng, int linje) {
        char c;
        int lengde = streng.length();
        for (int i = 0; i < lengde; i++) {
            c = streng.charAt(i);
            if (parentesType(c) == 0) {
                parenteser.push(new Parentes(c, linje + 1, i + 1));
            } else if (parentesType(c) == 1) {
                if (parenteser.isEmpty()) {
                    System.out.println("Lukkesymbol " + c + " på linje " + (linje + 1) +
                            ", tegn nr " + (i + 1) + " mangler tilsvarende åpnesymbol.");
                    System.exit(0);
                }
                if (!passer(parenteser.pop().getParentes(), c)) {
                    System.out.println("Lukkesymbol " + c + " på linje " + (linje + 1) +
                            ", tegn nr " + (i + 1) + " har feil åpnesymbol.");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * @param tegn tegnet som sjekkes
     * @return returnerer parentestype:
     * 0: venstreparentes
     * 1: høyreparentes
     * -1: ugyldig parentes
     */
    private int parentesType(char tegn) {
        for (char c : venstreParenteser) {
            if (tegn == c) return 0;
        }
        for (char c : hoyreParenteser) {
            if (tegn == c) return 1;
        }
        return -1;
    }

    public void lesFraFil(String filnavn) {
        FileReader tekstFilLeser = null;
        try {
            tekstFilLeser = new FileReader(filnavn);
        } catch (FileNotFoundException unntak) {
            System.out.println("Finner ikke filen!");
            System.exit(-1);
        }

        BufferedReader tekstLeser = new BufferedReader(tekstFilLeser);
        String streng;
        int linje = 0;
        try {
            while ((streng = tekstLeser.readLine()) != null) {
                foretaBalansering(streng, linje);
                linje++;

            }

            if (!parenteser.isEmpty()) {
                if (parenteser.size() > 1) {
                    System.out.println("Flere lukkesymboler mangler:");
                }
                while (!parenteser.isEmpty()) {
                    Parentes parentes = parenteser.pop();
                    System.out.println("Åpnesymbol " + parentes + " på line " + parentes.getLinje() +
                            ", tegn nr " + (parentes.getPosisjon()) + " har ikke tilsvarende lukkesymbol.");
                }
            } else {
                System.out.println(filnavn + " er balansert.");
            }

        } catch (IOException unntak) {
            System.out.println("Feil ved innlesing!");
            System.exit(-1);
        }
        try {
            tekstFilLeser.close();
        } catch (IOException unntak) {
            System.out.println("Feil ved lukking av fil!");
        }
    }
}
