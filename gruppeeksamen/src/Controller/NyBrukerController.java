package Controller;

import Modell.Bruker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NyBrukerController {
    @FXML
    private TextField BrukernavnTextField, spilletidTextField;
    @FXML
    private TextArea beskrivelseTextArea;
    @FXML
    private Label feilmeldingLabel;
    @FXML
    private DatePicker utgivelsesdatoDatePicker;
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
            tittelTextField.setText(nyBruker.getTittel());
            beskrivelseTextArea.setText(nyBruker.getBeskrivelse());
            if (!nyBruker.getUtgivelsesdato().equals(LocalDate.MIN))
                utgivelsesdatoDatePicker.setValue(nyBruker.getUtgivelsesdato());
            spilletidTextField.setText(String.valueOf(nyBruker.getSpilletid()));
        }
    }
}
