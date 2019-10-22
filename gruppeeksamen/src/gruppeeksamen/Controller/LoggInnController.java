package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
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
    private static ObservableList<String> listeMail = FXCollections.observableArrayList();
    private static ObservableList<String> listeSlett = FXCollections.observableArrayList();
    private static ObservableList<String> listeSlettOgsa = FXCollections.observableArrayList();

    @FXML
    //Dette skal bort
    private ListView brukerListen, passordListen, mailListen;
    @FXML
    private TextField inputBrukernavn, tja;
    @FXML
    private PasswordField inputPassord;
    @FXML
    private Button btnLoggInn;

    @FXML
    private void sjekkBruker(ActionEvent event) throws IOException {
        tja.setText(gaarGjennomListe(inputBrukernavn.getText(),inputPassord.getText(), event));
    }

    private String gaarGjennomListe(String brukernavn, String passord, ActionEvent event) throws IOException {
        ObservableList<String> brukernavnListe = DataHandler.hentData("src/gruppeeksamen/brukere.csv", 0/*Brukernavn*/, listeBrukenavn);
        ObservableList<String> passordListe = DataHandler.hentData("src/gruppeeksamen/brukere.csv", 1/*Passord*/, listePassord);
        String tja = "";
        for (int i = 0; i < brukernavnListe.size(); i++) {
            if (brukernavnListe.get(i).equals(brukernavn) && passordListe.get(i).equals(passord)) {
                tja = "Det gikk";
                Stage stage = (Stage) btnLoggInn.getScene().getWindow();
                stage.close();

                DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
                break;
            } else {
                //Brukeren skal få en feilmelding
                tja = "Dette gikk ikke så bra";
            }
        }
        return tja;
    }

    @FXML
    public void initialize() {
        System.out.println(DataHandler.hentDataHele("src/gruppeeksamen/brukere.csv",listeSlett));
        System.out.println(DataHandler.hentDataHele("src/gruppeeksamen/arrangementer.csv",listeSlettOgsa));
        //Dette skal bort
        brukerListen.setItems(DataHandler.hentData("src/gruppeeksamen/brukere.csv",0/*Brukernavn*/, listeBrukenavn));
        passordListen.setItems(DataHandler.hentData("src/gruppeeksamen/brukere.csv",1/*Passord*/, listePassord));
        mailListen.setItems(DataHandler.hentData("src/gruppeeksamen/brukere.csv",2/*Mail*/, listeMail));
    }
}
