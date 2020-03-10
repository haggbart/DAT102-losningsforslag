package no.hvl.dat102.stack;

public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException(String collection) {
        super("The " + collection + " is empty.");
    }
}
