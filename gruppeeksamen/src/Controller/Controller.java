package Controller;

import Data.DataHandler;
import Modell.Bruker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
    @FXML
    private ListView listen;
    @FXML
    private TextField inputBrukernavn, tja;
    @FXML
    private PasswordField inputPassord;

    @FXML
    private void sjekkBruker(ActionEvent event) {
        listen.setItems(DataHandler.hentValutaData());
        tja.setText(gaarGjennomListe(inputBrukernavn.getText(),inputPassord.getText()));
    }

    private String gaarGjennomListe(String brukernavn, String passord) {
        ObservableList<String> brukernavnListe = DataHandler.hentValutaData();
        ObservableList<String> passordListe = DataHandler.hentValutaData();
        String tja = "";
        for (int i = 0; i < brukernavnListe.size(); i++) {
            if (brukernavnListe.get(i).equals(brukernavn) && passordListe.get(i).equals(passord)) {
                //Brukeren skal bli sendt til neste scene
                tja = "Det funket";
                break;
            } else {
                //Brukeren skal få en feilmelding
                tja = "Dette gikk ikke så bra";
                break;
            }
        }
        return tja;
    }

    @FXML
    public void initialize() {

    }
}
