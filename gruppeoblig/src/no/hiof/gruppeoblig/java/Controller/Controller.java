package no.hiof.gruppeoblig.java.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import no.hiof.gruppeoblig.java.Data.DataHandler;
import no.hiof.gruppeoblig.java.Modell.Brukere;

public class Controller {
    @FXML
    private ComboBox<Brukere> listeMedSortering;

    @FXML
    public void initialize() {
        listeMedSortering.setItems(DataHandler.hentValutaData());
    }
}