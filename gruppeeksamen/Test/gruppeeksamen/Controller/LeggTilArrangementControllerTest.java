package gruppeeksamen.Controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {

    @Test
    public void sjekkOmAlleInputErFyltUt() {
        TextField arrangementInput = null;
        DatePicker datoDatePicker = null;
        ComboBox idrettComboBox = null;
        arrangementInput.setText("");
        datoDatePicker.setValue(LocalDate.parse("2020-10-10"));
        idrettComboBox.setValue("l");
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(arrangementInput, datoDatePicker, idrettComboBox));
    }
}