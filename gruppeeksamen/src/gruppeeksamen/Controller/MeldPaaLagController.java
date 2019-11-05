package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class MeldPaaLagController {
    @FXML
    private ComboBox<String> idretter;
    @FXML
    private ComboBox<String> arrangementer;
    @FXML
    private TextField fornavn, etternavn;
    @FXML
    private Button meldePaaKnapp, labelGaaTilbake;

    @FXML
    public void initialize() {
        //lager liste og legger til aktiviteter
        ObservableList<String> idrettListe = FXCollections.observableArrayList();
        idrettListe.add("Ski");
        idrettListe.add("Sykkel");
        idrettListe.add("Loping");
        //legger til aktivitetene i comboboxen
        idretter.setItems(idrettListe);
    }

    //oppdaterer combobox med arrangementer som er av typen valgt i første combobox
    @FXML
    private void oppdaterCup(ActionEvent value) {
        ObservableList<String> cuperListe  = FXCollections.observableArrayList();
        ObservableList<String> nyCuperListe  = FXCollections.observableArrayList();
        ObservableList<String> typeListe  = FXCollections.observableArrayList();
        //fyller typeListe og cuperListe
        DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 4/*CupNavn*/, typeListe);
        DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 0/*CupNavn*/, cuperListe);
        //fyller nyCuperListe med arrangementene som er av samme type valgt i første combobox i arrangement comboboxen
        for (int i=0;i<cuperListe.size();i++) {
            if (typeListe.get(i).equals(idretter.getValue())) {
                nyCuperListe.add(cuperListe.get(i));
            }
        }

        //legger til alle relevante arrangementer i
        arrangementer.setItems(nyCuperListe);
    }

    //sender videre til å legge til lag
    @FXML
    private void sendVidereTilFil(ActionEvent value) {
        leggTilLag();
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) labelGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
    }

    //denne må nok refaktoreres
    private void leggTilLag() {
        ObservableList<String> heleList  = FXCollections.observableArrayList();
        if (!fornavn.getText().equals("") && !etternavn.getText().equals("") && idretter.getValue() != null && arrangementer.getValue() != null) {
            //fyller listen
            ObservableList<String> heleListen = DataHandler.hentDataHele("src/gruppeeksamen/arrangementer.csv",heleList);
            //gjør om observablelist til arraylist
            ArrayList l = new ArrayList<>(heleListen);
            String utskrift;
            String gammelLinje = "";
            String nyLinje = "";
            String nyeLagene = "";
            for (int i=0; i<heleListen.size();i++) {
                //Legger til dato, navn på arrangement og type arrangement i array
                ArrayList lo = (ArrayList) l.get(i);
                int nyeAntallLag = 0;
                if (arrangementer.getValue() != null) {
                    for (int k=0; k < heleListen.size(); k++) {
                        //hvis cupnavnet fra listen stemmer med det man har valgt i gui
                        if (lo.get(0).equals(String.valueOf(arrangementer.getValue()))) {
                            //legger til +1 på antall lag
                            nyeAntallLag = Integer.parseInt((String) lo.get(1)) + 1;
                            //leger til lag i laglisten
                            if (lo.get(2).toString().isEmpty()) {
                                nyeLagene = fornavn.getText() + " " + etternavn.getText();
                            } else {
                                nyeLagene = lo.get(2) + "|" + fornavn.getText() + " " + etternavn.getText();
                            }
                            gammelLinje = lo.get(0) + ";" + lo.get(1) + ";" + lo.get(2) + ";" + lo.get(3) + ";" + lo.get(4);
                            nyLinje = lo.get(0) + ";" + nyeAntallLag + ";" + nyeLagene + ";" + lo.get(3) + ";" + lo.get(4);
                        }
                    }
                }
                utskrift = lo.get(0) + ";" + lo.get(1) + ";" + lo.get(2) + ";" + lo.get(3) + ";" + lo.get(4);
            }

            endreLinjeICSVFil("src/gruppeeksamen/arrangementer.csv", gammelLinje, nyLinje);

            Stage stage = (Stage) meldePaaKnapp.getScene().getWindow();
            stage.close();
            DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
        } else {
            MainJavaFX.visAlertFeilmelding("Mangler idrett, cup eller utøver","Må fylle inn en av delene");
        }
    }

    //komentar kommer
    private static void endreLinjeICSVFil(String filenSomLesesFra, String gammelLinje, String nylinje) {
        File filSomLesesFra = new File(filenSomLesesFra);
        StringBuilder nyFil = new StringBuilder();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linjeSomSkalBliDeler;
            String linjen;
            while( (linjeSomSkalBliDeler = bufretLeser.readLine()) != null ){
                String[] deler = linjeSomSkalBliDeler.split(";");
                linjen = deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4];
                if (linjen.equals(gammelLinje)) {
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
