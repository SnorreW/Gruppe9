package gruppeeksamen.Controller;

import gruppeeksamen.Modell.Bruker;
import gruppeeksamen.Modell.Utover;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NyBrukerController {
    @FXML
    private TextField BrukernavnTextField, PassordTextField, KlubbTextField, NavnTextField, EtternavnTextField;
    @FXML
    private Label feilmeldingLabel;
    @FXML
    private Button okButton;

    private Stage dialogStage;
    private Utover nyUtover;
    private static boolean okClicked = false;

    @FXML
    private void initialize() {
        okButton.setDefaultButton(true);
    }

    public void setNyUtover(Utover nyUtover) {
        this.nyUtover = nyUtover;

        if (nyUtover != null) {
            BrukernavnTextField.setText(nyUtover.getBrukenavn());
            PassordTextField.setText(nyUtover.getPassord());
            KlubbTextField.setText(nyUtover.getKlubb());
            NavnTextField.setText(nyUtover.getNavn());
            EtternavnTextField.setText(nyUtover.getEtternavn());
        }
    }

    public static boolean erOkValgt() {
        // Mulighet til å hente ut om vi avsluttet ved hjelp av OK eller ikke
        return okClicked;
    }

    @FXML
    private void okValgt() {
        if (sjekkOmInputErGyldig()) {
            // Hvis de er det, fyller vi opp utoverobjektet vårt med den nye dataen fra feltene
            nyUtover.setBrukernavn(BrukernavnTextField.getText());
            nyUtover.setPassord(PassordTextField.getText());
            nyUtover.setKlubb(KlubbTextField.getText());
            nyUtover.setNavn(NavnTextField.getText());
            nyUtover.setEtternavn(EtternavnTextField.getText());

            // Setter at vi avsluttet ved å trykke OK
            okClicked = true;

            // Henter ut en referanse til Stage (vinduet) ved hjelp av en av komponentene vi har i grensesnittet
            dialogStage = (Stage)okButton.getScene().getWindow();
            // Lukker vinduet
            dialogStage.close();
        }
    }

    private boolean sjekkOmInputErGyldig() {
        // Lager en string vi fyller opp med feilmeldinger for visning til brukeren
        String feilmelding = "";

        // Sjekker om det er noe innhold i brukernavnTekstFeltet
        if (BrukernavnTextField.getText() == null || BrukernavnTextField.getText().length() == 0) {
            // Hvis det ikke var satt noen brukernavn, legg til feilmelding
            feilmelding += "Brukernavn må settes!\n";
        }
        if (PassordTextField.getText() == null || PassordTextField.getText().length() == 0) {
            //Hvis det ikke var satt noe passord, legg til feilmelding
            feilmelding += "Passord må settes!\n";
        }
        if (KlubbTextField.getText() == null || KlubbTextField.getText().length() == 0) {
            //Hvis det ikke var satt noe klubb, legg til feilmelding
            feilmelding += "Klubb må settes!\n";
        }
        if (NavnTextField.getText() == null || NavnTextField.getText().length() == 0) {
            //Hvis det ikke var satt noe fornavn, legg til feilmelding
            feilmelding += "Fornavn må settes!\n";
        }
        if (EtternavnTextField.getText() == null || EtternavnTextField.getText().length() == 0) {
            //Hvis det ikke var satt noe etternavn, legg til feilmelding
            feilmelding += "Etternavn må settes!\n";
        }

        // Sjekker om vi har noen feilmelding eller ikke
        if (feilmelding.length() == 0) {
            // Returner true, som i at vi ikke har noen feilmeldinger og all input er gyldig
            return true;
        }
        else {
            // Skriv info til feilmeldingslabel som blir vist til brukeren
            feilmeldingLabel.setText("Vennligs rett følgende feil:\n" + feilmelding);
            // Returner false, som sier at input ikke er gyldig
            return false;
        }
    }
}
