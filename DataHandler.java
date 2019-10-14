package Data;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    public static ObservableList<String> hentData(String filnavn, int del, ObservableList liste) {
        if (liste.isEmpty()) {
            liste.addAll(genererData(filnavn, del));
        }
        return liste;
    }

    private static ArrayList<String> genererData(String filnavn, int del) {
        File kilden = new File(filnavn);

        ArrayList<String> dataFraFil = lesFraCSVFil(kilden, del);

        return dataFraFil;
    }

    private static ArrayList<String> lesFraCSVFil(File filSomLesesFra, int del) {
        ArrayList<String> dataFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                dataFraFil.add(deler[del]);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return dataFraFil;
    }
}
