package no.hvl.dat102.mengde.array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>{
    // Klasse som kan brukes til � g� gjennom alle elementer
// i et objekt av klasse Mengde n�r denne klassen Mengde er
// implementert vha tabell.
//
    private int antall;  // antall elementer i mengden
    private int index;    // posisjonen til aktuelt element
    private T[] mengde;


    public ArrayIterator(T[] elementer, int antall){
        // Gi startverdier til iteratoren
        this.mengde = elementer;
        this.antall = antall;
        index = 0;
    }

    @Override
    public boolean hasNext(){
        // Tester om det er flere elementer igjen
        return (index < antall);
    }

    @Override
    public T next(){
        // Returner med peker til neste aktuelle element
        if(!hasNext())
            throw new NoSuchElementException();
        index++;
        return mengde[index-1];
    }

    @Override
    public void remove(){

        antall--;
        mengde[index-1] = mengde[antall];
        mengde[antall] = null;
    }
}//class