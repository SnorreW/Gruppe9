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
    private Button okButton;

    private Stage dialogStage;
    private Bruker nyBruker;
    private boolean okClicked = false;

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

    /*@FXML
    private void okValgt() {
        if (sjekkOmInputErGyldig()) {
            // Hvis de er det, fyller vi opp filmobjektet vårt med den nye dataen fra feltene
            filmSomRedigeres.setTittel(tittelTextField.getText());
            filmSomRedigeres.setBeskrivelse(beskrivelseTextArea.getText());
            if (utgivelsesdatoDatePicker.getValue() != null)
                filmSomRedigeres.setUtgivelsesdato(utgivelsesdatoDatePicker.getValue());
            filmSomRedigeres.setSpilletid(Integer.parseInt(spilletidTextField.getText()));

            // Setter at vi avsluttet ved å trykke OK
            okClicked = true;

            // Henter ut en referanse til Stage (vinduet) ved hjelp av en av komponentene vi har i grensesnittet
            dialogStage = (Stage)okButton.getScene().getWindow();
            // Lukker vinduet
            dialogStage.close();
        }
    }*/

}
