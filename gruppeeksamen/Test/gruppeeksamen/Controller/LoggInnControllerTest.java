package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoggInnControllerTest{

    String inputBrukernavn;
    String inputPassord;
    String filstiBrukereCSV;
    private ListView<String> utoverListView;

    /*@Test
    private boolean gaarGjennomListe(){

    }
*/
   /* @Test
    void btnNyUtoverClicked() {
        if (inputBrukernavn.getText().isEmpty() && inputPassord.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler brukernavn og passord", "Fyll inn brukernavn og passord for å gå videre");
        } else if (inputBrukernavn.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler brukernavn", "Fyll inn brukernavn for å gå videre");
        } else if (inputPassord.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler passord", "Fyll inn passord for å gå videre");
        } else {
            //Hvis alle felter er fylt inn sendes den videre til neste sjekk
            gaarGjennomListe(inputBrukernavn.getText(),inputPassord.getText(), event);
        }

    }
    */

   /* @Test
    private void sjekkBruker(ActionEvent event) throws IOException {
        //Sjekker om alle felter er fylt inn
        if (inputBrukernavn.getText().isEmpty() && inputPassord.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler brukernavn og passord", "Fyll inn brukernavn og passord for å gå videre");
        } else if (inputBrukernavn.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler brukernavn", "Fyll inn brukernavn for å gå videre");
        } else if (inputPassord.getText().isEmpty()) {
            MainJavaFX.visAlertFeilmelding("Mangler passord", "Fyll inn passord for å gå videre");
        } else {
            //Hvis alle felter er fylt inn sendes den videre til neste sjekk
            gaarGjennomListe(inputBrukernavn.getText(),inputPassord.getText(), event);
        }
    }*/

    /*@Test
    private void initialize() {
        filstiBrukereCSV = "";
        ObservableList<String> listeMedBrukenavn = FXCollections.observableArrayList();
        utoverListView.setItems(DataHandler.hentDataHele(filstiBrukereCSV, listeMedBrukenavn));
    }*/



}