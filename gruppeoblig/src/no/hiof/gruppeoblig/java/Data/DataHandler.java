package no.hiof.gruppeoblig.java.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import no.hiof.gruppeoblig.java.Modell.Brukere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    private final static ObservableList<Brukere> valutaListe = FXCollections.observableArrayList();
    public static ObservableList<Brukere> hentValutaData() {
        if (valutaListe.isEmpty()) {
            valutaListe.addAll(genererValutaData());
        }
        return valutaListe;
    }

    private static ArrayList<Brukere> genererValutaData() {
        File kilden = new File("src/brukere.csv");

        ArrayList<Brukere> valutaerFraFiler = lesFraCSVFil(kilden);

        return valutaerFraFiler;
    }

    private static ArrayList<Brukere> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<Brukere> valutaerFraFil = new ArrayList<>();
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                Brukere enValuta = new Brukere(deler[0], deler[1]);

                valutaerFraFil.add(enValuta);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return valutaerFraFil;
    }
}