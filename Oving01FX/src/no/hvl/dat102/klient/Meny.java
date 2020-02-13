package no.hvl.dat102.klient;

import javafx.application.Application;
import no.hvl.dat102.adt.FilmarkivADT;

public class Meny {
    private Tekstgrensesnitt tekstgrensesnitt;
    private FilmarkivADT arkiv;

    public Meny(FilmarkivADT arkiv) {
        tekstgrensesnitt = new Tekstgrensesnitt();
        this.arkiv = arkiv;
    }

    public void start() {
        Application.launch(KlientFilmarkiv.class);
    }
}
