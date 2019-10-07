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
    private final static ObservableList<Bruker> valutaListe = FXCollections.observableArrayList();
    public static ObservableList<Bruker> hentValutaData() {
        if (valutaListe.isEmpty()) {
            valutaListe.addAll(genererValutaData());
        }
        return valutaListe;
    }

    private static ArrayList<Bruker> genererValutaData() {
        File kilden = new File("src/brukere.csv");

        ArrayList<Bruker> valutaerFraFiler = lesFraCSVFil(kilden);

        return valutaerFraFiler;
    }

    private static ArrayList<Bruker> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<Bruker> valutaerFraFil = new ArrayList<>();
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                Bruker enValuta = new Bruker(deler[0], deler[1]);

                valutaerFraFil.add(enValuta);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return valutaerFraFil;
    }
}
