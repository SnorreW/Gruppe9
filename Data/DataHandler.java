package no.hiof.gruppe9.Data;

import Modell.Bruker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    private final static ObservableList<Bruker> brukerListe = FXCollections.observableArrayList();
    public static ObservableList<Bruker> hentBrukerData() {
        if (brukerListe.isEmpty()) {
            brukerListe.addAll(genererBrukerData());
        }
        return brukerListe;
    }

    private static ArrayList<Bruker> genererBrukerData() {
        File kilden = new File("src/brukere.csv");

        ArrayList<Bruker> brukereFraFiler = lesFraCSVFil(kilden);

        return brukereFraFiler;
    }

    private static ArrayList<Bruker> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<Bruker> brukereFraFil = new ArrayList<>();
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                Bruker enBruker = new Bruker(deler[0], deler[1]);

                brukereFraFil.add(enBruker);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return brukereFraFil;
    }
}
