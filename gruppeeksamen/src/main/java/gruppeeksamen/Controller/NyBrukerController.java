package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Bruker;
import gruppeeksamen.Modell.Utover;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class NyBrukerController {
    @FXML
    private TextField BrukernavnTextField, PassordTextField, NavnTextField, EtternavnTextField, AlderTextField;
    @FXML
    private Label feilmeldingLabel;
    @FXML
    private Button okButton;
    @FXML
    private Button leggTilButton;

    private Stage dialogStage;
    private String feilmelding = "";
    private Utover nyUtover;
    private static boolean okClicked = false;

    @FXML
    private void initialize() {
        //Setter leggTilButton til defaultknapp
        leggTilButton.setDefaultButton(true);
    }

    @FXML
    public void leggTilUtover(ActionEvent event) {
        //Sjekker om input er gyldig først
        if(sjekkOmInputErGyldig(String.valueOf(BrukernavnTextField.getText()), String.valueOf(PassordTextField.getText()), String.valueOf(NavnTextField.getText()), String.valueOf(EtternavnTextField.getText()), String.valueOf(AlderTextField.getText()))) {
            //Hvis input er gyldig så hentes teksten fra alle input felter og lages om til en string seperert med ;
            String linje = BrukernavnTextField.getText() + ";" + PassordTextField.getText() + ";" + NavnTextField.getText() + ";" +
                    EtternavnTextField.getText() + ";" + Integer.parseInt(AlderTextField.getText()) + "\n";

            //Skriver til filen brukere.csv
            try {
                FileWriter file = new FileWriter("src/main/java/gruppeeksamen/brukere.csv", true);
                file.append(linje);
                file.flush();
                file.close();
            }
            //Skriver feilmeldig hvis det oppstår en feil
            catch (IOException e) {
                e.printStackTrace();
            }
            //Lukker vinduet
            Stage stagen = (Stage) leggTilButton.getScene().getWindow();
            stagen.close();
        }
        else {
            // Skriv info til feilmeldingslabel som blir vist til brukeren
            feilmeldingLabel.setText("Vennligs rett følgende feil:\n" + feilmelding);
            //Feilmelding
            MainJavaFX.visAlertFeilmelding("Mangler Brukernavn, passord, navn, etternavn eller alder","Må fylle inn en av delene");
        }
    }

    public void setNyUtover(Utover nyUtover) {
        this.nyUtover = nyUtover;

        //Hvis utover ikke er null så oppretter vi en utover
        if (nyUtover != null) {

            BrukernavnTextField.setText(nyUtover.getBrukenavn());
            PassordTextField.setText(nyUtover.getPassord());
            NavnTextField.setText(nyUtover.getNavn());
            EtternavnTextField.setText(nyUtover.getEtternavn());
            AlderTextField.setText(String.valueOf(nyUtover.getAlder()));
        }
    }

    public static boolean erOkValgt() {
        // Mulighet til å hente ut om vi avsluttet ved hjelp av OK eller ikke
        return okClicked;
    }

    @FXML
    private void avbrytValgt() {
        // Henter ut en referanse til Stage (vinduet) ved hjelp av en av komponentene vi har i grensesnittet
        dialogStage = (Stage)okButton.getScene().getWindow();
        // Lukker vinduet uten å gjøre noe mer (okClicked er fortsatt false)
        dialogStage.close();
    }

    public boolean sjekkOmInputErGyldig(String brukernavn, String passord, String fornavn, String etternavn, String alder) {
        feilmelding = "";

        // Sjekker om det er noe innhold i brukernavnTekstFeltet
        if (brukernavn.equals("") || brukernavn.length() == 0) {
            // Hvis det ikke var satt noen brukernavn, legg til feilmelding
            feilmelding += "Brukernavn må settes!\n";
        }
        if (passord.equals("") || passord.length() == 0) {
            //Hvis det ikke var satt noe passord, legg til feilmelding
            feilmelding += "Passord må settes!\n";
        }
        if (fornavn.equals("") || fornavn.length() == 0) {
            //Hvis det ikke var satt noe fornavn, legg til feilmelding
            feilmelding += "Fornavn må settes!\n";
        }
        if (etternavn.equals("") || etternavn.length() == 0) {
            //Hvis det ikke var satt noe etternavn, legg til feilmelding
            feilmelding += "Etternavn må settes!\n";
        }
        if (Integer.valueOf(alder) == 0) {
            //Hvis det ikke var satt noe alder, legg til feilmelding
            feilmelding += "Alder må settes!\n";
        }
        if (Integer.valueOf(alder) < 13) {
            //Hvis personen ikke er over 13 år får de ikke bruke appen, legg til feilmelding
            feilmelding += "Du må være over 13 år!\n";
        }
        if (Integer.valueOf(alder) > 130) {
            //Hvis personen skriver for høy alder får de ikke bruke appen, legg til feilmelding
            feilmelding += "Du må skrive inn korrekt alder!\n";
        }

        // Sjekker om vi har noen feilmelding eller ikke
        if (feilmelding.length() == 0) {
            // Returner true, som i at vi ikke har noen feilmeldinger og all input er gyldig
            return true;
        }
        else {
            // Returner false, som sier at input ikke er gyldig
            return false;
        }
    }


}
