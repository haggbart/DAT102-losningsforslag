package no.hvl.dat102;

import java.io.*;

public class ButikkIO {

    static Butikk rema;
    public static void main(String args[]) {

        rema.skrivUtVarer();
//        rema.leggInnNyVare();
//        rema.skrivUtVarer();
//        rema.slettVare(2);


        saveButikk(rema);
    }

    static {
        try (ObjectInputStream butikkdata = new ObjectInputStream(new BufferedInputStream(new FileInputStream("butikk.dat")))) {
            rema = (Butikk)butikkdata.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Lager ny butikk");
            Butikk butikk = new Butikk("Rema 1000", 100);;
            rema = butikk;
        }
    }

    private static void saveButikk(Butikk butikk) {
        try (ObjectOutputStream butikkdata = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("butikk.dat")))) {

            butikkdata.writeObject(butikk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
