package no.hiof.aleksaps.SE;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<User> listeMedBrukere = new ArrayList<>();

	    listeMedBrukere.add(new User(1, "Aleksander", "Passord123", "Passord123", "Skiforeningen"));



        System.out.println("*********Brukere Sortert*************");
        for (User enBruker : listeMedBrukere) {
            System.out.println(enBruker);
        }
        System.out.println("*************************************");




    }
}
