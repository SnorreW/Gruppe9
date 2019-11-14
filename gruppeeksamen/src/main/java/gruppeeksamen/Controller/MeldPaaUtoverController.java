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

    String filStiTilArrangementer = "src/main/java/gruppeeksamen/arrangementer.csv";
    String filStiTilLoggetInn = "../../View/loggetInn.fxml";

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
        //fyller typeListe og typeIdrettlisteListe
        DataHandler.hentDataDel(filStiTilArrangementer, 4/*CupNavn*/, typeIdrettListe);
        DataHandler.hentDataDel(filStiTilArrangementer, 0/*CupNavn*/, arrangementListe);

        //Fyller inn arrangementer
        fyllNyttArrangement(arrangementListe, arrangementerMedRiktigTypeIdrettListe, typeIdrettListe);
    }

    private void fyllNyttArrangement(ObservableList<String> arrListe, ObservableList<String> riktigIdrett,ObservableList<String> typeIdrettListe ){

        for (int i=0;i<arrListe.size();i++) {

            if (typeIdrettListe.get(i).equals(idretterComboBox.getValue())) {
                riktigIdrett.add(arrListe.get(i));
            }
        }
        //legger til alle relevante arrangementer i
        arrangementerComboBox.setItems(riktigIdrett);
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
        DataHandler.sendTilNyScene(filStiTilLoggetInn, "Arrengementer", 500, 500);
    }

    //denne må nok refaktoreres
    private void leggTilUtover() {
        ObservableList<String> heleList  = FXCollections.observableArrayList();

        if (!fornavnInput.getText().equals("") && !etternavnInput.getText().equals("") && idretterComboBox.getValue() != null && arrangementerComboBox.getValue() != null) {
            //fyller listen
            ObservableList<String> heleListenMedArrangementer = DataHandler.hentDataHele(filStiTilArrangementer,heleList);
            leggerTilUtovereIArrangement(heleListenMedArrangementer);

            Stage stage = (Stage) meldePaaKnapp.getScene().getWindow();
            stage.close();
            DataHandler.sendTilNyScene(filStiTilLoggetInn, "Arrengementer",500, 500);
        }
        else {
            MainJavaFX.visAlertFeilmelding("Mangler idrett, cup eller utøver","Må fylle inn en av delene");
        }
    }
    private void leggerTilUtovereIArrangement(ObservableList<String> heleListenMedArrangementer){
        ArrayList ArrayListMedArrangmenter = new ArrayList<>(heleListenMedArrangementer);
        String utskrift;
        String gammelLinje = "";
        String nyLinje = "";
        String nyeUtover = "";
        for (int i=0; i<heleListenMedArrangementer.size();i++) {
            //Legger til dato, navn på arrangement og type arrangement i array
            ArrayList nyListeMedAlleArrangementer = (ArrayList) ArrayListMedArrangmenter.get(i);
            int nyeAntallLag = 0;
            if (arrangementerComboBox.getValue() != null) {
                for (int k=0; k < heleListenMedArrangementer.size(); k++) {
                    //hvis aktivitets-navnet fra listen stemmer med det man har valgt i gui
                    if (nyListeMedAlleArrangementer.get(0).equals(String.valueOf(arrangementerComboBox.getValue()))) {
                        //legger til +1 på antall utøvere
                        nyeAntallLag = Integer.parseInt((String) nyListeMedAlleArrangementer.get(1)) + 1;
                        //leger til utøver i utøverlistenlisten
                        if (nyListeMedAlleArrangementer.get(2).toString().isEmpty()) {
                            nyeUtover = fornavnInput.getText() + " " + etternavnInput.getText();
                        }
                        else {
                            nyeUtover = nyListeMedAlleArrangementer.get(2) + "|" + fornavnInput.getText() + " " + etternavnInput.getText();
                        }
                        gammelLinje = nyListeMedAlleArrangementer.get(0) + ";" + nyListeMedAlleArrangementer.get(1) + ";" + nyListeMedAlleArrangementer.get(2) + ";" + nyListeMedAlleArrangementer.get(3) + ";" + nyListeMedAlleArrangementer.get(4);
                        nyLinje = nyListeMedAlleArrangementer.get(0) + ";" + nyeAntallLag + ";" + nyeUtover + ";" + nyListeMedAlleArrangementer.get(3) + ";" + nyListeMedAlleArrangementer.get(4);
                    }
                }
            }
            utskrift = nyListeMedAlleArrangementer.get(0) + ";" + nyListeMedAlleArrangementer.get(1) + ";" + nyListeMedAlleArrangementer.get(2) + ";" + nyListeMedAlleArrangementer.get(3) + ";" + nyListeMedAlleArrangementer.get(4);
        }
        endreLinjeICSVFil(filStiTilArrangementer, gammelLinje, nyLinje);
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
