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

    @FXML
    private ListView<String> cup;
    @FXML
    private Button loggUt, leggTilArrangement;

    @FXML
    public void initialize() {
        //Sjekker om man trykker på en av elementene i listen
        cup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            //Ser hvilket element man trykker på og sender med parametere til senTilNyScene
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
                stagen.close();
                //Lager en string som består kun av navnet på arrangementet
                String[] datoArrangementOgType = newValue.split(": ");
                String[] arrangementOgType = datoArrangementOgType[1].split(" \\(");
                String scenen = arrangementOgType[0];
                //setter stagen som blir hentet i cup.fxml
                setStagen(arrangementOgType[0]);
                DataHandler.sendTilNyScene("../view/cup.fxml",scenen, 500,500);
            }
        });
        //Fyller listen med elementer den får fra fyllListe metoden
        cup.setItems(fyllListe());
    }

    private static ObservableList<String> fyllListe() {
        ObservableList<String> slettListe = FXCollections.observableArrayList();
        ArrayList loo = new ArrayList();
        //fyller listen med hele arrangementer.csv filen
        ObservableList<String> slettListen = DataHandler.hentDataHele("src/gruppeeksamen/arrangementer.csv", slettListe);
        //gjør om observablelist til arraylist
        ArrayList l = new ArrayList<>(slettListen);
        String utskrift;
        for (int i=0; i<slettListen.size();i++) {
            //Legger til dato, navn på arrangement og type arrangement i array
            ArrayList lo = (ArrayList) l.get(i);
            utskrift = lo.get(3) + ": " + lo.get(0) + " (" + lo.get(4) + ")";
            loo.add(utskrift);
        }
        //setter observablelisten lik arraylisten
        slettListen = FXCollections.observableArrayList(loo);

        //returnerere listen som skriver ut listen i listviewet
        return slettListen;
    }

    @FXML
    private void meldPaaLaget(ActionEvent event) {
        //Lukker nåværende vindu og sender til påmeding av lag
        Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
        stagen.close();
        DataHandler.sendTilNyScene("../view/meldPaaLag.fxml", "Meld på laget ditt", 500, 500);
    }

    @FXML
    private void sorterePaaDato(ActionEvent event) {
        ObservableList<String> forSorteringsListe = FXCollections.observableArrayList();
        DataHandler.hentDataHele("src/gruppeeksamen/arrangementer.csv", forSorteringsListe);
        ObservableList<String> sortertListe = FXCollections.observableArrayList();
        ArrayList la = new ArrayList<>(forSorteringsListe);
        ArrayList lo = (ArrayList) la.get(0);
        int aar = Integer.parseInt(lo.get(3).toString().split("\\.")[0]);
        int maaned = Integer.parseInt(lo.get(3).toString().split("\\.")[1]);
        int dato = Integer.parseInt(lo.get(3).toString().split("\\.")[2]);
        String utskrift = lo.get(3) + ": " + lo.get(0) + " (" + lo.get(4) + ")";
        for (int i=1; i<forSorteringsListe.size();i++) {
            //Legger til dato, navn på arrangement og type arrangement i array
            ArrayList loo = (ArrayList) la.get(i);
            int aaret = Integer.parseInt(loo.get(3).toString().split("\\.")[0]);
            int maaneden = Integer.parseInt(loo.get(3).toString().split("\\.")[1]);
            int datoen = Integer.parseInt(loo.get(3).toString().split("\\.")[2]);
            if (aar<=aaret) {
                if (maaned<=maaneden) {
                    if (dato<datoen) {
                        utskrift = loo.get(0).toString();
                    }
                }
            }
        }
        System.out.println(utskrift);
    }

    @FXML
    private void leggTilArrangement(ActionEvent event) {
        //Lukker nåværende vindu og sender til scenen hvor man kan legge til arrangement
        Stage stagen = (Stage) leggTilArrangement.getScene().getWindow();
        stagen.close();
        DataHandler.sendTilNyScene("../view/leggTilArrangement.fxml", "Legg til arrangement", 500, 500);
    }

    @FXML
    private void loggUtBruker(ActionEvent event) {
        //Lukker nåværende vindu og logger brukeren ut. Sender brukeren til innloggingen
        Stage stage = (Stage) loggUt.getScene().getWindow();
        stage.close();
        MainJavaFX.gaaTilHovedVisning();
    }

    public static String getStagen() {
        return stagen;
    }

    public void setStagen(String stagen) {
        this.stagen = stagen;
    }
}
