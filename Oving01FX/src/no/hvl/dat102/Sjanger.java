package no.hvl.dat102;

public enum Sjanger {
    ACTION, DRAMA, HISTORY, SCIFI, COMEDY, THRILLER, HORROR;


    public static Sjanger finnSjanger(String navn) {
        for (Sjanger sjanger : Sjanger.values()) {
            if (sjanger.toString().equals(navn.toUpperCase())) {
                return sjanger;
            }
        }
        return null;
    }

    public static String formatertSjanger(Sjanger sjanger) {
        return sjanger.toString().charAt(0) + sjanger.toString().substring(1).toLowerCase();
    }
}
