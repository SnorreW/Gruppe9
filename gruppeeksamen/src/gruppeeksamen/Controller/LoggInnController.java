package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.Data.NyDataHandler;
import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Aktivitet;
import gruppeeksamen.Modell.Bruker;
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
    private ListView<Utover> utoverListView;

    public void btnNyUtoverClicked(ActionEvent actionEvent) {
        //Oppretter og instansierer et nytt brukerobjekt
        Utover nyUtover = new Utover();

        //Viser det nye vinduet, og sender objektet inn for å fylles med data, får tilbake true/false ettersom hvordan det gikk
        boolean nyUtoverVellyket = MainJavaFX.getInstance().visNyUtoverDialog(nyUtover);

        //Sjekker om den nye utoveren ble laget
        if(nyUtoverVellyket) {
            //Henter ut listen med utovere, og legger til den nye utoveren som ble laget
            NyDataHandler.hentUtoverData().add(nyUtover);
            //Setter at den nye utoveren er valgt
            utoverListView.getSelectionModel().select(nyUtover);
        }
    }

    @FXML
    private void sjekkBruker(ActionEvent event) throws IOException {
        gaarGjennomListe(inputBrukernavn.getText(),inputPassord.getText(), event);
    }

    private boolean gaarGjennomListe(String brukernavn, String passord, ActionEvent event) throws IOException {
        ObservableList<String> brukernavnListe = DataHandler.hentDataDel("src/gruppeeksamen/brukere.csv", 0/*Brukernavn*/, listeBrukenavn);
        ObservableList<String> passordListe = DataHandler.hentDataDel("src/gruppeeksamen/brukere.csv", 1/*Passord*/, listePassord);
        for (int i = 0; i < brukernavnListe.size(); i++) {
            if (brukernavnListe.get(i).equals(brukernavn) && passordListe.get(i).equals(passord)) {
                innlogginsFeil = false;
                Stage stage = (Stage) btnLoggInn.getScene().getWindow();
                stage.close();
                DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
                break;
            } else {
                innlogginsFeil = true;
            }
        }
        if (innlogginsFeil) {
            MainJavaFX.visAlertFeilmelding("Feil ved innlogging", "Brukernavn eller passord er feil.");
        }
        return innlogginsFeil;
    }
}
