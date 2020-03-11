package no.hvl.dat102;

public class Hobby {
    private String navn;

    public Hobby(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {

        return navn;
    }

    public boolean equals(Object object){
        if (!(object instanceof Hobby)) {
            return false;
        }
        return navn.equals(((Hobby) object).navn);
    }
}