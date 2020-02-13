import no.hvl.dat102.Film;
import no.hvl.dat102.Filmarkiv;
import no.hvl.dat102.Filmarkiv2;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FilmarkivADTTest {

    Film avatarJamesCameron = new Film(1, "James Cameron", "Avatar", Sjanger.SCIFI, 2009);
    Film titanicJamesCameron = new Film(2, "James Cameron", "Titanic", Sjanger.DRAMA, 1997);
    Film theGrudgeNicolasPesce = new Film(3,"Nicolas Pesce", "The Grudge", Sjanger.ACTION, 2018);

    Film toyStoryOne = new Film(6,"John Lasseter", "Toy Story", Sjanger.ACTION, 1995);
    Film toyStoryTwo = new Film(8,"John Lasseter", "Toy Story 2", Sjanger.ACTION, 1999);
    Film toyStoryThree = new Film(12,"Lee Unkrich", "Toy Story 3", Sjanger.ACTION, 2010);

    @Test
    void leggTil() {
        FilmarkivADT filmarkiv = new Filmarkiv2();
        filmarkiv.leggTil(avatarJamesCameron);

        assertEquals(1, filmarkiv.antall());
    }

    @Test
    void slettNormal() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);

        assertTrue(filmarkiv.slett(3));
        assertEquals(1, filmarkiv.antall());
    }

    @Test
    void slettNotFound() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);

        assertFalse(filmarkiv.slett(23));
    }

    @Test
    void slettEmptyArchive() {
        Filmarkiv filmarkiv = new Filmarkiv();
        assertFalse(filmarkiv.slett(3));
    }

    @Test
    void slettEnFilm() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        assertTrue(filmarkiv.slett(1));
    }

    @Test
    void sokTittelsubStringSearch() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);

        Film[] answer = filmarkiv.sokTittel("ava");

        assertTrue(letEtterFilm(answer, avatarJamesCameron));
        assertEquals(1, answer.length);
    }

    @Test
    void sokTittelManyMovies() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(toyStoryOne);
        filmarkiv.leggTil(toyStoryThree);
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(toyStoryTwo);

        Film[] answer = filmarkiv.sokTittel("Toy Story");

        assertTrue(letEtterFilm(answer, toyStoryOne));
        assertTrue(letEtterFilm(answer, toyStoryTwo));
        assertTrue(letEtterFilm(answer, toyStoryThree));
        assertFalse(letEtterFilm(answer, avatarJamesCameron));
    }

    @Test
    void sokTittelNoAnswer() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(toyStoryOne);
        filmarkiv.leggTil(toyStoryThree);

        Film[] answer = filmarkiv.sokTittel("Avatar");

        assertEquals(0, answer.length);
    }

    @Test
    void sokTittelCaseSensitivity() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(toyStoryOne);
        filmarkiv.leggTil(avatarJamesCameron);

        Film[] answer = filmarkiv.sokTittel("AVATAR");

        assertEquals(1, answer.length);
        assertTrue(letEtterFilm(answer,avatarJamesCameron));
    }

    @Test
    void sokProdusentNoAnswer() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);

        Film[] answer = filmarkiv.sokProdusent("Sam Mendes");

        assertEquals(0, answer.length);
    }

    @Test
    void sokProdusentCaseSensitivity() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);
        filmarkiv.leggTil(titanicJamesCameron);

        Film[] answer = filmarkiv.sokProdusent("NICOLAS PESCE");

        assertEquals(1, answer.length);
        assertTrue(letEtterFilm(answer, theGrudgeNicolasPesce));
    }

    @Test
    void sokProdusentManyMovies() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(theGrudgeNicolasPesce);
        filmarkiv.leggTil(titanicJamesCameron);

        Film[] answer = filmarkiv.sokProdusent("James Cameron");

        assertEquals(2, answer.length);

        assertTrue(letEtterFilm(answer, avatarJamesCameron));
        assertFalse(letEtterFilm(answer, theGrudgeNicolasPesce));
        assertTrue(letEtterFilm(answer, titanicJamesCameron));
    }

    private boolean letEtterFilm(Film[] filmliste, Film film) {
        for (Film value : filmliste) {
            if (value == film) {
                return true;
            }
        }
        return false;
    }

    @Test
    void antallZeroMovies() {
        Filmarkiv filmarkiv = new Filmarkiv();
        assertEquals(0, filmarkiv.antall());
    }

    @Test
    void antallRegular() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(toyStoryOne);

        assertEquals(2, filmarkiv.antall());
    }

    @Test
    void antallOverCapacity() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(toyStoryOne);
        filmarkiv.leggTil(theGrudgeNicolasPesce);

        assertEquals(3,filmarkiv.antall());
    }

    @Test
    void hentFilmtabell() {
        Filmarkiv filmarkiv = new Filmarkiv();
        filmarkiv.leggTil(avatarJamesCameron);
        filmarkiv.leggTil(toyStoryTwo);
        filmarkiv.leggTil(theGrudgeNicolasPesce);


        Film[] answer = filmarkiv.hentFilmer();

        assertTrue(letEtterFilm(answer,toyStoryTwo));
        assertTrue(letEtterFilm(answer,avatarJamesCameron));
        assertTrue(letEtterFilm(answer,theGrudgeNicolasPesce));
    }
}