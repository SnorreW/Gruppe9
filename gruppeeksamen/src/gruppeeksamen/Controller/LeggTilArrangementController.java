package gruppeeksamen.Controller;

import gruppeeksamen.Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.awt.*;

public class LeggTilArrangementController {
    @FXML
    private TextField arrangementInput;
    @FXML
    private DatePicker datoDatePicker;
    @FXML
    private ComboBox idrettComboBox;

    @FXML
    public void initialize() {
        ObservableList<Arrangementer> idrettListe = FXCollections.observableArrayList();
        if (idrettListe != null) {
            idrettListe.clear();
        }
        idrettListe.add(new Arrangementer("Ski"));
        idrettListe.add(new Arrangementer("Sykkel"));
        idrettListe.add(new Arrangementer("Loping"));
        idrettComboBox.setItems(idrettListe);
    }

    public void leggTilArrangement() {
        if (sjekkOmAlleInputErFyltUt()){
            System.out.println("ha");
        }
    }

    private boolean sjekkOmAlleInputErFyltUt() {
        if (arrangementInput.getText().isEmpty() ) {
            return true;
        }
        return false;
    }
}
