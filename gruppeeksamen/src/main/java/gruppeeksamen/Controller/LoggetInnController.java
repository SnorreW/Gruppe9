package gruppeeksamen.Controller;

import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Data.DataHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoggetInnController {
    private static String stagen = null;
    String filStiTilLeggTilArrangementFXML = "../../view/leggTilArrangement.fxml";
    String filStiTilMeldPaaUtoverFXML = "../../view/meldPaaUtover.fxml";
    String filstiTilArrangementFXML = "../../view/arrangement.fxml";
    private String filstiTilArrangementerCSV = "src/main/java/gruppeeksamen/arrangementer.csv";

    @FXML
    private ListView<String> listeMedArrangementer;
    @FXML
    private Button btnGaaTilbake, leggTilArrangement;

    @FXML
    public void initialize() {
        //Sjekker om man trykker på en av elementene i listen
        listeMedArrangementer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            //Ser hvilket element man trykker på og sender med parametere til senTilNyScene
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
                stagen.close();
                String scenen = setSceneen(newValue);
                DataHandler.sendTilNyScene(filstiTilArrangementFXML,scenen, 500,500);
            }
        });
        //Fyller listen med elementer den får fra fyllListe metoden
        listeMedArrangementer.setItems(fyllListe());
    }

    public String setSceneen(String scene) {
        //Lager en string som består kun av navnet på arrangementet
        String[] datoArrangementOgType = scene.split(": ");
        String[] arrangementOgType = datoArrangementOgType[1].split(" \\(");
        String scenen = arrangementOgType[0];
        setStagen(arrangementOgType[0]);
        return scenen;
    }

    public ObservableList<String> fyllListe() {
        ObservableList<String> listeSomSkalFylles = FXCollections.observableArrayList();
        ArrayList arrayListeSomSkalBliObservable = new ArrayList();
        //fyller listen med hele arrangementer.csv filen
        ObservableList<String> listeMedDataFraCSVFil = DataHandler.hentDataHele(filstiTilArrangementerCSV, listeSomSkalFylles);
        //gjør om observablelist til arraylist
        ArrayList arrayListeMedAltOmEtArrangement = new ArrayList<>(listeMedDataFraCSVFil);
        String utskrift;
        for (int i=0; i<listeMedDataFraCSVFil.size();i++) {
            //Legger til dato, navn på arrangement og type arrangement i array
            ArrayList arraListeMedEnTingOmEtArrangement = (ArrayList) arrayListeMedAltOmEtArrangement.get(i);
            utskrift = arraListeMedEnTingOmEtArrangement.get(3) + ": " + arraListeMedEnTingOmEtArrangement.get(0) + " (" + arraListeMedEnTingOmEtArrangement.get(4) + ")";
            arrayListeSomSkalBliObservable.add(utskrift);
        }
        //setter observablelisten lik arraylisten
        listeMedDataFraCSVFil = FXCollections.observableArrayList(arrayListeSomSkalBliObservable);

        //returnerere listen som skriver ut listen i listviewet
        return listeMedDataFraCSVFil;
    }

    @FXML
    private void meldPaaUtover(ActionEvent event) {
        //Lukker nåværende vindu og sender til påmeding av utøver
        Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
        stagen.close();
        DataHandler.sendTilNyScene(filStiTilMeldPaaUtoverFXML, "Meld på utøver", 500, 500);
    }

    @FXML
    private void leggTilArrangement(ActionEvent event) {
        //Lukker nåværende vindu og sender til scenen hvor man kan legge til arrangement
        Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
        stagen.close();
        DataHandler.sendTilNyScene(filStiTilLeggTilArrangementFXML, "Legg til arrangement", 500, 500);
    }

    @FXML
    private void gaaTilbake(ActionEvent event) {
        //Lukker nåværende vindu og logger brukeren ut. Sender brukeren til innloggingen
        Stage stage = (Stage) btnGaaTilbake.getScene().getWindow();
        stage.close();
        MainJavaFX.gaaTilHovedVisning();
    }

    public static String getStagen() {
        return stagen;
    }

    private void setStagen(String stagen) {
        this.stagen = stagen;
    }
}
