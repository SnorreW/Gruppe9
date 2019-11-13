package gruppeeksamen.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {
    private String navnPaaArrangement;
    private String datoPaaArrangement;
    private String typeIdrettPaaArrangement;

    @Test
    public void sjekkeOmManFaarTrueDersomAlleInputFelterErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void sjekkeOmManFaarFalseDersomAlleInputFelterIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = null;
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }
}