package no.hiof.gruppe9.Controller;

import Data.DataHandler;
import Modell.Bruker;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Controller {
    @FXML
    private ComboBox<Bruker> listeMedSortering;

    @FXML
    public void initialize() {
        listeMedSortering.setItems(DataHandler.hentValutaData());
    }
}

public void nyButtonClicked(ActionEvent actionEvent) {
    // Oppretter og instansierer et nytt filmobjekt
     nyKlubb = new Bruker();

    // Viser det nye vinduet, og sender objektet inn for å fylles med data, får tilbake true/false avhengig av hvordan det gikk
    //boolean nyLagetVellyket = MainJavaFX.getInstance().visRedigerFilmDialog(nyFilm);
    boolean nyKlubbVellyket = MainJavaFX.getInstance().visRedigerBrukerDialog(nyKlubb);

    // Sjekker om den nye filmen ble laget
    if (nyBrukerLagetVellyket) {
        // Henter ut lista med filmer, og legger til den nye filmen som ble laget
        DataHandler.hentFilmData().add(nyFilm);
        // Setter at den nye filmen er valgt
        filmListView.getSelectionModel().select(nyFilm);
    }
}
