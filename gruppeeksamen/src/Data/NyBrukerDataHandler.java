package Data;

import Modell.Bruker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class NyBrukerDataHandler {
    public static ObservableList<Bruker> hentBrukerData() {
        File kilde = new File("brukere.csv");

        ArrayList<Bruker> brukereFraFil = lesFraCSVFil(kilde);

        return FXCollections.observableArrayList(brukereFraFil);
    }

    private static ArrayList<Bruker> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<Bruker> brukereFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;

            while ((linje = bufretLeser.readLine()) !=null) {
                //Index 0 -> Brukernavn
                //Index 1 -> Passord
                //Index 2 -> Klubb
                String[] deler = linje.split(";");

                Bruker enBruker = new Bruker(deler[0], deler[1], deler[2]);

                brukereFraFil.add(enBruker);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        //Returnerer listen med filmer
        return brukereFraFil;
    }

    private static void skrivTilCSVFil(ArrayList<Bruker> brukere, File filSomSkrivesTil) {
        try (BufferedWriter bufretSkriver = new BufferedWriter(new FileWriter(filSomSkrivesTil))) {

            for(Bruker enBruker: brukere) {
                bufretSkriver.write(enBruker.getBrukenavn() + ";" + enBruker.getPassord() + ";" + enBruker.getKlubb());

                bufretSkriver.newLine();
            }
        }

        catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
        catch (IOException ioexc) {
            System.out.println(ioexc.getLocalizedMessage());
        }
    }

}