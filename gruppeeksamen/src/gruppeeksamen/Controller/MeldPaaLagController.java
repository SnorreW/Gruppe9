package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class MeldPaaLagController {
    private static ObservableList<Arrangementer> idrettListe = FXCollections.observableArrayList();
    private static ObservableList<Arrangementer> cuperListe  = FXCollections.observableArrayList();
    private static ObservableList<String> cupList  = FXCollections.observableArrayList();
    private static ObservableList<String> antallLagList  = FXCollections.observableArrayList();
    private static ObservableList<String> lagList  = FXCollections.observableArrayList();
    private static ObservableList<String> datoList  = FXCollections.observableArrayList();
    private static ObservableList<String> typeList  = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Arrangementer> idretter;
    @FXML
    private ComboBox<Arrangementer> cup;
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
        DataHandler.hentDataCuper("src/gruppeeksamen/arrangementer.csv", 0/*CupNavn*/, cuperListe, idretter.getValue());
        cup.setItems(cuperListe);
    }

    @FXML
    private void sendVidereTilFil(ActionEvent value) {
        leggTilLag();
    }

    private void leggTilLag() {
        if (!lag.getText().equals("") && idretter.getValue() != null && cup.getValue() != null) {
            ObservableList<String> nyLagList  = FXCollections.observableArrayList();
            ObservableList<String> nyAntallLagList  = FXCollections.observableArrayList();
            nyAntallLagList.clear();
            nyLagList.clear();
            ObservableList<String> cupListen = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 0, cupList);
            ObservableList<String> antallLagListen = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv",1, antallLagList);
            ObservableList<String> lagListen = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv",2, lagList);
            ObservableList<String> datoListen = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv",3, datoList);
            ObservableList<String> typeListen = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv",4, typeList);
            String gammelLinje = "";
            String nyLinje = "";
            String nyeLagene = "";
            int nyeAntallLag = 0;
            if (cup.getValue() != null) {
                for (int k=0; k < cupListen.size(); k++) {
                    //hvis cupnavnet fra listen stemmer med det man har valgt i gui
                    if (cupListen.get(k).equals(String.valueOf(cup.getValue()))) {
                        //legger til +1 på antall lag
                        nyeAntallLag = Integer.parseInt(antallLagListen.get(k)) + 1;
                        //leger til lag i laglisten
                        if (lagListen.get(k).isEmpty()) {
                            nyeLagene = lag.getText();
                        } else {
                            nyeLagene = lagListen.get(k) + "|" + lag.getText();
                        }
                        gammelLinje = cupListen.get(k) + ";" + antallLagListen.get(k) + ";" + lagListen.get(k) + ";" + datoListen.get(k) + ";" + typeListen.get(k);
                        nyLinje = cupListen.get(k) + ";" + nyeAntallLag + ";" + nyeLagene + ";" + datoListen.get(k) + ";" + typeListen.get(k);
                        for(int i=0; i<lagListen.size();i++) {
                            if (lagListen.get(i).equals(lagListen.get(k))){
                                nyLagList.add(nyeLagene);
                                nyAntallLagList.add(String.valueOf(nyeAntallLag));
                            } else {
                                nyLagList.add(lagListen.get(i));
                                nyAntallLagList.add(antallLagListen.get(i));
                            }
                        }
                    }
                }
                lagList = nyLagList;
                antallLagList = nyAntallLagList;
            }
            endreLinjeICSVFil("src/gruppeeksamen/arrangementer.csv", gammelLinje, nyLinje);

            Stage stage = (Stage) meldePaaKnapp.getScene().getWindow();
            stage.close();
        } else {
            MainJavaFX.visAlertFeilmelding("Mangler idrett, cup eller utøver","Må fylle inn en av delene");
        }
    }

    private static void endreLinjeICSVFil(String filenSomLesesFra, String gammelLinje, String nylinje) {
        File filSomLesesFra = new File(filenSomLesesFra);
        StringBuilder nyFil = new StringBuilder();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linjeSomSkalBliDeler;
            String linjen;
            while( (linjeSomSkalBliDeler = bufretLeser.readLine()) != null ){
                String[] deler = linjeSomSkalBliDeler.split(";");
                linjen = deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4];
                if (linjen.equals(gammelLinje) || linjen.equals(nylinje)) {
                    String[] linjeArray = linjen.split(";");
                    String[] linjeArrayLag = linjeArray[2].split("\\|");
                    List<String> linjeListLag = Arrays.asList(linjeArrayLag);
                    String[] nyLinjeArray = nylinje.split(";");
                    String[] nyLinjeArrayLag = nyLinjeArray[2].split("\\|");
                    String sisteINyLinjeArray = nyLinjeArrayLag[nyLinjeArrayLag.length-1];
                    nyFil.append(nylinje).append("\n");
                } else {
                    nyFil.append(linjen).append("\n");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            FileWriter skriv = new FileWriter(filenSomLesesFra);
            BufferedWriter skrive = new BufferedWriter(skriv);
            skrive.write(nyFil.toString());
            skrive.flush();
            skrive.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
