package gruppeeksamen.Data;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler {
    //henter alle deler fra csv fil
    public static ObservableList<String> hentDataHele(String filnavn, ObservableList liste) {
        if (liste.isEmpty()) {
            liste.addAll(genererDataHele(filnavn));
        }
        return liste;
    }
    //henter en del fra csv fil
    public static ObservableList<String> hentDataDel(String filnavn, int del, ObservableList liste) {
        if (liste.isEmpty()) {
            liste.addAll(genererDataDel(filnavn, del));
        }
        return liste;
    }

    private static ArrayList<ArrayList<String>> genererDataHele(String filnavn) {
        File kilden = new File(filnavn);

        ArrayList<ArrayList<String>> dataFraFil = lesFraCSVFilHele(kilden);

        return dataFraFil;
    }
    private static ArrayList<String> genererDataDel(String filnavn, int del) {
        File kilden = new File(filnavn);

        ArrayList<String> dataFraFil = lesFraCSVFilDel(kilden, del);

        return dataFraFil;
    }

    private static ArrayList<ArrayList<String>> lesFraCSVFilHele(File filSomLesesFra) {
        ArrayList<ArrayList<String>> dataFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                ArrayList<String> person = new ArrayList<>();
                String[] deler = linje.split(";");

                for (int i=0; i<deler.length;i++) {
                    person.add(deler[i]);
                }
                dataFraFil.add(person);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        return dataFraFil;
    }
    public static ArrayList<String> lesFraCSVFilDel(File filSomLesesFra, int del) {
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

    public static void sendTilNyScene(String fxml, String tittel, int bredde, int hoyde) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1,bredde,hoyde));
            stage.setTitle(tittel);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
