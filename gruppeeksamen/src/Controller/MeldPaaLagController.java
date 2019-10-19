package Controller;

import Data.DataHandler;
import Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MeldPaaLagController {
    private static ObservableList<Arrangementer> idrettListe = FXCollections.observableArrayList();
    private static ObservableList<Arrangementer> cuperListe  = FXCollections.observableArrayList();
    private static ObservableList<String> cupList  = FXCollections.observableArrayList();
    private static ObservableList<String> antallLagList  = FXCollections.observableArrayList();
    private static ObservableList<String> lagList  = FXCollections.observableArrayList();
    private static ObservableList<String> datoList  = FXCollections.observableArrayList();
    private static ObservableList<String> typeList  = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Arrangementer> idretter, cup;
    @FXML
    private TextField lag;
    @FXML
    private Button meldePaaKnapp;

    @FXML
    public void initialize() {
        if (idrettListe != null) {
            idrettListe.clear();
        }
        idrettListe.add(new Arrangementer("Ski"));
        idrettListe.add(new Arrangementer("Sykkel"));
        idrettListe.add(new Arrangementer("Loping"));
        idretter.setItems(idrettListe);
    }

    @FXML
    private void oppdaterCup(ActionEvent value) {
        if (cuperListe != null) {
            cuperListe.clear();
        }
        DataHandler.hentDataCuper("src/arrangementer.csv", 0/*CupNavn*/, cuperListe, idretter.getValue());
        cup.setItems(cuperListe);
    }

    @FXML
    private void sendVidereTilFil(ActionEvent value) throws IOException {
        ObservableList<String> cupListen = DataHandler.hentData("src/arrangementer.csv", 0, cupList);
        ObservableList<String> antallLagListen = DataHandler.hentData("src/arrangementer.csv",1, antallLagList);
        ObservableList<String> lagListen = DataHandler.hentData("src/arrangementer.csv",2, lagList);
        ObservableList<String> datoListen = DataHandler.hentData("src/arrangementer.csv",3, datoList);
        ObservableList<String> typeListen = DataHandler.hentData("src/arrangementer.csv",4, typeList);
        String gammelLinje = "";
        String nyLinje = "";
        String lagene = "";
        int antallLag = 0;
        if (cup.getValue() != null) {
            for (int k=0; k < cupListen.size(); k++) {
                if (cupListen.get(k).equals(String.valueOf(cup.getValue()))) {
                    antallLag = Integer.parseInt(antallLagListen.get(k)) + 1;
                    lagene = lagListen.get(k) + "|" + lag.getText();
                    gammelLinje = cupListen.get(k) + ";" + antallLagListen.get(k) + ";" + lagListen.get(k) + ";" + datoListen.get(k) + ";" + typeListen.get(k);
                    nyLinje = cupListen.get(k) + ";" + antallLag + ";" + lagene + ";" + datoListen.get(k) + ";" + typeListen.get(k);
                }
            }
        }
        endreCSVFil("src/arrangementer.csv", gammelLinje, nyLinje);

        Stage stage = (Stage) meldePaaKnapp.getScene().getWindow();
        stage.close();
    }

    private static ArrayList<String> endreCSVFil(String filenSomLesesFra, String gammelLinje, String nylinje) throws IOException {
        File filSomLesesFra = new File(filenSomLesesFra);
        ArrayList<String> dataFraFil = new ArrayList<>();
        String nyFil = "";

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            String linjen;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");
                linjen = deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4];
                if (linjen.equals(gammelLinje)) {
                    nyFil += nylinje + "\n";
                } else {
                    nyFil += linjen + "\n";
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        FileWriter skriv = new FileWriter(filenSomLesesFra);
        skriv.write(nyFil);
        skriv.flush();
        skriv.close();

        return dataFraFil;
    }
}
