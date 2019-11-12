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

public class MeldPaaUtoverController {
    @FXML
    private ComboBox<String> idretterComboBox;
    @FXML
    private ComboBox<String> arrangementerComboBox;
    @FXML
    private TextField fornavnInput, etternavnInput;
    @FXML
    private Button meldePaaKnapp, btnGaaTilbake;

    @FXML
    public void initialize() {
        //lager liste og legger til aktiviteter
        ObservableList<String> idrettListe = FXCollections.observableArrayList();
        idrettListe.add("Ski");
        idrettListe.add("Sykkel");
        idrettListe.add("Loping");
        //legger til aktivitetene i comboboxen
        idretterComboBox.setItems(idrettListe);
    }

    //oppdaterer combobox med arrangementer som er av typen valgt i første combobox
    @FXML
    private void oppdaterCup(ActionEvent value) {
        ObservableList<String> arrangementListe  = FXCollections.observableArrayList();
        ObservableList<String> arrangementerMedRiktigTypeIdrettListe  = FXCollections.observableArrayList();
        ObservableList<String> typeIdrettListe  = FXCollections.observableArrayList();
        //fyller typeListe og cuperListe
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 4/*CupNavn*/, typeIdrettListe);
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 0/*CupNavn*/, arrangementListe);
        //fyller nyCuperListe med arrangementene som er av samme type valgt i første combobox i arrangement comboboxen
        for (int i=0;i<arrangementListe.size();i++) {
            if (typeIdrettListe.get(i).equals(idretterComboBox.getValue())) {
                arrangementerMedRiktigTypeIdrettListe.add(arrangementListe.get(i));
            }
        }

        //legger til alle relevante arrangementer i
        arrangementerComboBox.setItems(arrangementerMedRiktigTypeIdrettListe);
    }

    //sender videre til å legge til utøver
    @FXML
    private void sendVidereTilFil(ActionEvent value) {
        leggTilUtover();
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) btnGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene("../../view/loggetInn.fxml", "Arrengementer", 500, 500);
    }

    //denne må nok refaktoreres
    private void leggTilUtover() {
        ObservableList<String> heleList  = FXCollections.observableArrayList();
        if (!fornavnInput.getText().equals("") && !etternavnInput.getText().equals("") && idretterComboBox.getValue() != null && arrangementerComboBox.getValue() != null) {
            //fyller listen
            ObservableList<String> heleListen = DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv",heleList);
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
                if (arrangementerComboBox.getValue() != null) {
                    for (int k=0; k < heleListen.size(); k++) {
                        //hvis aktivitets-navnet fra listen stemmer med det man har valgt i gui
                        if (lo.get(0).equals(String.valueOf(arrangementerComboBox.getValue()))) {
                            //legger til +1 på antall utøvere
                            nyeAntallLag = Integer.parseInt((String) lo.get(1)) + 1;
                            //leger til utøver i utøverlistenlisten
                            if (lo.get(2).toString().isEmpty()) {
                                nyeLagene = fornavnInput.getText() + " " + etternavnInput.getText();
                            } else {
                                nyeLagene = lo.get(2) + "|" + fornavnInput.getText() + " " + etternavnInput.getText();
                            }
                            gammelLinje = lo.get(0) + ";" + lo.get(1) + ";" + lo.get(2) + ";" + lo.get(3) + ";" + lo.get(4);
                            nyLinje = lo.get(0) + ";" + nyeAntallLag + ";" + nyeLagene + ";" + lo.get(3) + ";" + lo.get(4);
                        }
                    }
                }
                utskrift = lo.get(0) + ";" + lo.get(1) + ";" + lo.get(2) + ";" + lo.get(3) + ";" + lo.get(4);
            }

            endreLinjeICSVFil("src/main/java/gruppeeksamen/arrangementer.csv", gammelLinje, nyLinje);

            Stage stage = (Stage) meldePaaKnapp.getScene().getWindow();
            stage.close();
            DataHandler.sendTilNyScene("../../view/loggetInn.fxml", "Arrengementer", 500, 500);
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
