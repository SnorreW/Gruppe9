package Controller;

import Data.DataHandler;
import Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MeldPaaLagController {
    private static ObservableList<Arrangementer> idrett  = FXCollections.observableArrayList();

    @FXML
    private ComboBox idretter;
    @FXML
    private TextField lag;

    @FXML
    public void initialize() {
        ObservableList<String> idretterListen = DataHandler.hentData("src/brukere.csv", 0/*Brukernavn*/, idrett);
    }
}
