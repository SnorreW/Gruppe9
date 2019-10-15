package Controller;

import Data.Arrangementer;
import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LoggetInnController {
    private static ObservableList<String> listeArrangement = FXCollections.observableArrayList();

    @FXML
    private ListView c;


    @FXML
    public void initialize() {
        //Dette skal bort
        c.setItems(DataHandler.hentData("src/arrangementer.csv", 0/*Brukernavn*/, listeArrangement));
    }
}
