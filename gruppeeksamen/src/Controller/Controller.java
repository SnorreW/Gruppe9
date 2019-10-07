package Controller;

import Data.DataHandler;
import Modell.Bruker;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Controller {
    @FXML
    private ComboBox<Bruker> listeMedSortering;

    @FXML
    public void initialize() {
        listeMedSortering.setItems(DataHandler.hentValutaData());
    }
}
