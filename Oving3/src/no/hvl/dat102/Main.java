package no.hvl.dat102;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Medlemmer medlemmer = new Medlemmer();

        // setter opp medlem1
        Medlem petter = new Medlem("Petter");
        petter.getHobbyer().leggTil(new Hobby("male"));
        petter.getHobbyer().leggTil(new Hobby("sykle"));
        medlemmer.leggTil(petter);

        // printer info om medlem1
        System.out.println(petter);


        // setter opp medlem2
        Medlem ole = new Medlem("Ole");
        ole.getHobbyer().leggTil(new Hobby("sykle"));
        ole.getHobbyer().leggTil(new Hobby("male"));
        medlemmer.leggTil(ole);


        // setter opp medlem3
        Medlem gunnar = new Medlem("Gunnar");
        gunnar.getHobbyer().leggTil(new Hobby("Skateboarding"));
        gunnar.getHobbyer().leggTil(new Hobby("male"));
        medlemmer.leggTil(gunnar);
        

        // tester om medlem1 finner medlem2
        medlemmer.finnPartnerFor(ole);
        System.out.println("Partner for ole: " + ole.getPartner());
    }
}
