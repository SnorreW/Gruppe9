package Data;

import Modell.Arrangementer;
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
    public static ObservableList<String> hentData(String filnavn, int del, ObservableList liste) {
        if (liste.isEmpty()) {
            liste.addAll(genererData(filnavn, del));
        }
        return liste;
    }
    public static ObservableList<String> hentDataCupLag(String filnavn, int del, ObservableList liste, String cup) {
        if (liste.isEmpty()) {
            liste.addAll(genererDataCupLag(filnavn, del, cup));
        }
        return liste;
    }
    public static ObservableList<String> hentDataCuper(String filnavn, int del, ObservableList liste, Arrangementer cup) {
        if (liste.isEmpty()) {
            liste.addAll(genererDataCuper(filnavn, del, cup));
        }
        return liste;
    }

    private static ArrayList<String> genererData(String filnavn, int del) {
        File kilden = new File(filnavn);

        ArrayList<String> dataFraFil = lesFraCSVFil(kilden, del);

        return dataFraFil;
    }
    private static ArrayList<String> genererDataCupLag(String filnavn, int del, String cup) {
        File kilden = new File(filnavn);

        ArrayList<String> dataFraFil = lesFraCSVFilCupLag(kilden, del, cup);

        return dataFraFil;
    }
    private static ArrayList<String> genererDataCuper(String filnavn, int del, Arrangementer cup) {
        File kilden = new File(filnavn);

        ArrayList<String> dataFraFil = lesFraCSVFilCuper(kilden, del, cup);

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
    private static ArrayList<String> lesFraCSVFilCupLag(File filSomLesesFra, int del, String cup) {
        ArrayList<String> dataFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                if (deler[0].equals(cup)) {
                    String[] delerLag = deler[2].split("\\|");
                    for (int i=0; i<delerLag.length; i++) {
                        dataFraFil.add(delerLag[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return dataFraFil;
    }
    private static ArrayList<String> lesFraCSVFilCuper(File filSomLesesFra, int del, Arrangementer cup) {
        ArrayList<String> dataFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");

                if (deler[4].equals(cup.toString())) {
                    dataFraFil.add(deler[0]);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return dataFraFil;
    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    public static void sendTilNyScene(String fxml, String tittel, int bredde, int hoyde) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1,bredde,hoyde));
        stage.setTitle(tittel);
        stage.showAndWait();
    }
}
