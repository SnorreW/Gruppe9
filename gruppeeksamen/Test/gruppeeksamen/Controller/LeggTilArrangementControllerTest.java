package gruppeeksamen.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {
    @FXML
    private TextField arrangementInput;
    @FXML
    private DatePicker datoDatePicker;
    @FXML
    private ComboBox idrettComboBox;
    @FXML
    private Button btnLeggTilArrangement, btnGaaTilbake;

    @Test
    public void sjekkOmAlleInputErFyltUt() {
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        arrangementInput.setText("lol");
        datoDatePicker.setValue(LocalDate.parse("2020-10-10"));
        idrettComboBox.setValue("null");
        assertEquals(true, leggTilArrangementController.sjekkOmAlleInputErFyltUt());
    }
}