package gruppeeksamen.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {
    @FXML
    private TextField arrangementInput;
    @FXML
    private DatePicker datoDatePicker;
    @FXML
    private ComboBox idrettComboBox;

    @Test
    public void sjekkOmAlleInputErFyltUt() {
        arrangementInput.setText("");
        datoDatePicker.setValue(null);
        idrettComboBox.setValue(null);
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(arrangementInput, datoDatePicker, idrettComboBox));
    }

}