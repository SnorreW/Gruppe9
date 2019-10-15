package Data;

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

    public static void sendTilNyScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1,500,500));
        stage.setTitle("Logget Inn");
        stage.showAndWait();
    }
}
