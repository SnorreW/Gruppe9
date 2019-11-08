package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.Data.NyDataHandler;
import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Utover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggInnController {
    private static ObservableList<String> listeBrukenavn = FXCollections.observableArrayList();
    private static ObservableList<String> listePassord = FXCollections.observableArrayList();
    private boolean innlogginsFeil = false;

    @FXML
    private TextField inputBrukernavn;
    @FXML
    private PasswordField inputPassord;
    @FXML
    private Button btnLoggInn;
    @FXML
    private Button btnNyUtover;
    @FXML
    private ListView<String> utoverListView;

    @FXML
    private void initialize() {
        ObservableList<String> listeMedBrukenavn = FXCollections.observableArrayList();
        utoverListView.setItems(DataHandler.hentDataHele("src/gruppeeksamen/brukere.csv", listeMedBrukenavn));
    }

    @FXML
    public void btnNyUtoverClicked(ActionEvent actionEvent) {
        //Oppretter og instansierer et nytt brukerobjekt
        Utover nyUtover = new Utover();

        //Viser det nye vinduet, og sender objektet inn for å fylles med data, får tilbake true/false ettersom hvordan det gikk
        boolean nyUtoverVellyket = MainJavaFX.getInstance().visNyUtoverDialog(nyUtover);

        //Sjekker om den nye utoveren ble laget
        if(nyUtoverVellyket) {
            ObservableList<Utover> listeMedBrukenavn = FXCollections.observableArrayList();
            //Henter ut listen med utovere, og legger til den nye utoveren som ble laget
            NyDataHandler.hentUtoverData().add(nyUtover);
            //Setter at den nye utoveren er valgt
            utoverListView.setItems(DataHandler.hentDataHele("src/gruppeeksamen/brukere.csv", listeMedBrukenavn));
        }
    }

    //Når man trykker på logg inn knappen sender den input (brukernav og passord) videre til en sjekk
    @FXML
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
    }
    //Sjekke om brukernavn og passord stemmer
    private boolean gaarGjennomListe(String brukernavn, String passord, ActionEvent event) throws IOException {
        //Fyller to lister. En med brukernavn og en med passord
        ObservableList<String> brukernavnListe = DataHandler.hentDataDel("src/gruppeeksamen/brukere.csv", 0/*Brukernavn*/, listeBrukenavn);
        ObservableList<String> passordListe = DataHandler.hentDataDel("src/gruppeeksamen/brukere.csv", 1/*Passord*/, listePassord);
        //Går gjennom listene for å sjekke om brukernavn og passord stemmer med hverandre
        for (int i = 0; i < brukernavnListe.size(); i++) {
            if (brukernavnListe.get(i).equals(brukernavn) && passordListe.get(i).equals(passord)) {
                //Hvis brukernavn og passord stemmer overens, blir innloggingsFeil satt til false
                innlogginsFeil = false;
                Stage stage = (Stage) btnLoggInn.getScene().getWindow();
                stage.close();
                DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
                break;
            } else {
                //Hvis brukernavn og passord ikke stemmer overens, blir innloggingsfeil satt til true
                innlogginsFeil = true;
            }
        }
        //Hvis innloggingsfeil ble satt til true under iftesten over, blir brukeren sendt til neste vindu
        if (innlogginsFeil) {
            MainJavaFX.visAlertFeilmelding("Feil ved innlogging", "Brukernavn eller passord er feil.");
        }
        return innlogginsFeil;
    }
}
