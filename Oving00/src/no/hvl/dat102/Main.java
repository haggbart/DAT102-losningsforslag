package no.hvl.dat102;

public class Main {
    public static void main(String[] args) {
        Butikk b1 = new Butikk("Rema 1000", 100);
        b1.leggInnNyVare(12);
        b1.leggInnNyVare(5);
//        b1.leggInnNyVare(15);
//        b1.leggInnNyVare(16);
//        b1.leggInnNyVare(6);

//
//        System.out.println(b1);
//        b1.slettVare(16);
//        System.out.println(b1);
//
//        b1.slettVare(6);
//        System.out.println(b1);


        b1.grossInnkjop(12, 10);
        System.out.println("Salgsverdi: " + b1.salgsVerdi());
        b1.skrivUtVarer();
        b1.slettVare(5);
        b1.skrivUtVarer();
    }
}
