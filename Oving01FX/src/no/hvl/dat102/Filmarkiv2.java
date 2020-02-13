package no.hvl.dat102;

import no.hvl.dat102.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {

    private LinearNode<Film> head;
    private int antall;

    public Filmarkiv2() {
        head = null;
        antall = 0;
    }

    @Override
    public Film[] hentFilmer() {
        Film[] filmer = new Film[antall];
        LinearNode<Film> node = head;
        int i = 0;
        for (; node != null; i++) {
            filmer[i] = node.getElement();
            node = node.getNext();
        }
        return trim(filmer, i);
    }

    @Override
    public void leggTil(Film film) {
        LinearNode<Film> node = new LinearNode<>(film);
        if (antall != 0) {
            node.setNext(head);
        }
        head = node;
        antall++;
    }

    @Override
    public boolean slett(int filmNummer) {
        LinearNode<Film> previous = new LinearNode<>();
        LinearNode<Film> current = head;
        while (current != null) {
            if (current.getElement().getFilmNummer() == filmNummer) {
                if (antall == 1)
                    head = null;
                else if (current == head)
                    head = current.getNext();
                else {
                    previous.setNext(current.getNext());
                }
                antall--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public Film[] sokTittel(String deltekst) {
        Film[] result = new Film[antall]; // maks størrelse
        LinearNode<Film> node = head;
        int treff = 0;
        while (node != null) {
            if (node.getElement().getTittel().toLowerCase().contains(deltekst.toLowerCase())) {
                result[treff] = node.getElement();
                treff++;
            }
            node = node.getNext();
        }
        return trim(result, treff);
    }

    @Override
    public Film[] sokProdusent(String deltekst) {
        Film[] result = new Film[antall]; // maks størrelse
        LinearNode<Film> node = head;
        int treff = 0;
        while (node != null) {
            if (node.getElement().getProdusent().toLowerCase().contains(deltekst.toLowerCase())) {
                result[treff] = node.getElement();
                treff++;
            }
            node = node.getNext();
        }
        return trim(result, treff);
    }

    private Film[] trim(Film[] filmer, int antall) {
        Film[] trimmed = new Film[antall];
        for (int i = 0; i < antall; i++) {
            trimmed[i] = filmer[i];
        }
        return trimmed;
    }

    @Override
    public int antallSjanger(Sjanger sjanger) {
        int antall = 0;
        LinearNode<Film> node = head;

        while (node != null) {
            if (node.getElement().getSjanger() == sjanger) {
                antall++;
            }
            node = node.getNext();
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
        LinearNode<Film> node = head;
        while (node != null) {
            sb.append(node.getElement().toString()).append("\n");
            node = node.getNext();
        }
        return sb.toString();
    }
}
