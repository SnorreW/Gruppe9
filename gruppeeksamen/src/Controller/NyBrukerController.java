package Controller;

import Modell.Bruker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NyBrukerController {
    @FXML
    private TextField BrukernavnTextField, PassordTextField, KlubbTextField;
    @FXML
    private Label feilmeldingLabel;
    @FXML
    private ListView<Bruker> brukerListView;
    @FXML
    private Button okButton;

    private Stage dialogStage;
    private Bruker nyBruker;
    private static boolean okClicked = false;

    @FXML
    private void initialize() {
        okButton.setDefaultButton(true);
    }

    public void setNyBruker(Bruker nyBruker) {
        this.nyBruker = nyBruker;

        if (nyBruker != null) {
            BrukernavnTextField.setText(nyBruker.getBrukenavn());
            PassordTextField.setText(nyBruker.getPassord());
            KlubbTextField.setText(nyBruker.getKlubb());
        }
    }

    private boolean sjekkOmInputErGyldig() {
        String feilmelding = "";

        if (BrukernavnTextField.getText() == null || BrukernavnTextField.getText().length() == 0) {
            feilmelding += "Brukernavn må settes!\n";
        }
        if (PassordTextField.getText() == null || PassordTextField.getText().length() == 0) {
            feilmelding += "Passord må settes!\n";
        }
        if (KlubbTextField.getText() == null || KlubbTextField.getText().length() == 0) {
            feilmelding += "Klubb må settes!\n";
        }

        if (feilmelding.length() == 0) {
            return true;
        }
        else {
            feilmeldingLabel.setText("Vennligs rett følgende feil:\n" + feilmelding);
            return false;
        }
    }

    @FXML
    private void okValgt() {
        if (sjekkOmInputErGyldig()) {
            nyBruker.setBrukenavn(BrukernavnTextField.getText());
            nyBruker.setPassord(PassordTextField.getText());
            nyBruker.setKlubb(KlubbTextField.getText());

            okClicked = true;

            dialogStage = (Stage) okButton.getScene().getWindow();

            dialogStage.close();
        }
    }

    @FXML
    private void avbrytValgt() {
        dialogStage = (Stage)okButton.getScene().getWindow();

        dialogStage.close();
    }

    public static boolean erOkValgt() {
        return okClicked;
    }

}
