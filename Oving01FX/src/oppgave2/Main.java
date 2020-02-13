package oppgave2;

public class Main {


    public static long tid(long n) {

        long starttid = System.currentTimeMillis();
        long k = 0;
        for (long i = 1; i <= n; i++) {
            k = k + 5;
        }

        return System.currentTimeMillis() - starttid;
        //System.out.println("Utfoeringstid = " + n + " er " + (sluttid - starttid) + " ms");
    }

    public static void main(String[] args) {
        long[] snitt = new long[3];
        int antallMalinger = 20;

        for (int i = 0; i < antallMalinger; i++) {
            snitt[0] += tid(100000000L);
            snitt[1] += tid(1000000000L);
            snitt[2] += tid(10000000000L);
        }


        for (int i = 0; i < snitt.length; i++) {
            System.out.println("snitt for " + i + ": " + (snitt[i]/antallMalinger) + " ms");
        }
    }
}
