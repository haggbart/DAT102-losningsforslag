import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.array.ArrayMengde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class MengdeADTTest {


    private MengdeADT<Integer> mengde;
    private MengdeADT<Integer> mengde2;
    private MengdeADT<Integer> mengde3;

    protected abstract MengdeADT<Integer> reset();

    @BeforeEach
    public void setup() {
        mengde = reset();
        mengde2 = reset();
        mengde3 = reset();
    }

    @Test
    void antall() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(5);
        assertEquals(3, mengde.antall());
        mengde.fjern(2);
        assertEquals(2, mengde.antall());
    }

    @Test
    void erTom() {
        assertTrue(mengde.erTom());
        mengde.leggTil(1);
        mengde.leggTil(2);
        assertFalse(mengde.erTom());
        mengde.fjern(1);
        mengde.fjern(2);
        assertTrue(mengde.erTom());
    }

    @Test
    void leggTil() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        assertTrue(mengde.inneholder(2));
        assertFalse(mengde.inneholder(3));
    }

    @Test
    void littAvHvert() {
        var mengde = new ArrayMengde<Integer>();
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);
        mengde.leggTil(4);
        mengde.fjern(2);
    }

    @Test
    void fjernTilfeldig() {
        mengde.leggTil(1);
        mengde.leggTil(5);
        var T = mengde.fjernTilfeldig();
        assertNotNull(T);
        assertFalse(mengde.inneholder(T));

    }

    @Test
    void fjern() {
        mengde.leggTil(1);
        mengde.leggTil(4);
        mengde.leggTil(5);
        mengde.leggTil(99);
        mengde.leggTil(6);
        assertEquals(1, mengde.fjern(1));
        assertEquals(6, mengde.fjern(6));
        assertNull(mengde.fjern(10));
        assertEquals(5, mengde.fjern(5));
    }

    @Test
    void inneholder() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);
        mengde.leggTil(4);
        mengde.leggTil(5);
        assertTrue(mengde.inneholder(2));
        assertFalse(mengde.inneholder(6));
    }

    @Test
    void testEquals() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);

        mengde2.leggTil(2);
        mengde2.leggTil(3);
        mengde2.leggTil(1);

        assertEquals(mengde, mengde2);

        mengde2.leggTil(5);
        assertNotEquals(mengde, mengde2);
        mengde.leggTil(6);
        assertNotEquals(mengde, mengde2);
    }

    @Test
    void leggTilAlle() {
    }

    @Test
    void union() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde2.leggTil(2);
        mengde2.leggTil(3);
        mengde2.leggTil(4);
        var mengde3 = mengde.union(mengde2);
        var mengde4 = mengde2.union(mengde);
        assertEquals(mengde3, mengde4);
        this.mengde3.leggTil(1);
        this.mengde3.leggTil(2);
        this.mengde3.leggTil(3);
        this.mengde3.leggTil(4);
        assertEquals(this.mengde3, mengde3);
    }

    @Test
    void snitt() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);

        mengde2.leggTil(2);
        mengde2.leggTil(3);
        mengde2.leggTil(4);

        mengde3.leggTil(2);
        mengde3.leggTil(3);

        assertEquals(mengde3, mengde.snitt(mengde2));
        assertEquals(mengde3, mengde2.snitt(mengde));
    }

    @Test
    void komplement() {
        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);
        mengde.leggTil(4);
        mengde.leggTil(5);

        mengde2.leggTil(3);
        mengde2.leggTil(4);

        mengde3.leggTil(1);
        mengde3.leggTil(2);
        mengde3.leggTil(5);
        assertEquals(mengde.komplement(mengde2), mengde3);
    }

    @Test
    void delmengde() {
    }

    @Test
    void iterator() {
    }

    @Test
    void testToString() {
    }
}