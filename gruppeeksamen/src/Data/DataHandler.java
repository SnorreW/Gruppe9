package Data;

import Modell.Bruker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    private final static ObservableList<String> valutaListe = FXCollections.observableArrayList();
    public static ObservableList<String> hentValutaData() {
        if (valutaListe.isEmpty()) {
            valutaListe.addAll(genererValutaData());
        }
        return valutaListe;
    }

    private static ArrayList<String> genererValutaData() {
        File kilden = new File("src/brukere.csv");

        ArrayList<String> valutaerFraFiler = lesFraCSVFil(kilden);

        return valutaerFraFiler;
    }

    private static ArrayList<String> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<String> brukernavnFraFil = new ArrayList<>();
        ArrayList<String> passordFraFil = new ArrayList<>();
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                Bruker enValuta = new Bruker(deler[0], deler[1]);

                brukernavnFraFil.add(enValuta.getBrukenavn());
                passordFraFil.add(enValuta.getPassord());
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return brukernavnFraFil;
    }
}
