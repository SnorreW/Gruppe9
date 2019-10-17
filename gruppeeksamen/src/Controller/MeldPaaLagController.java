package Controller;

import Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MeldPaaLagController {
    private static ObservableList<Arrangementer> s  = FXCollections.observableArrayList();

    @FXML
    private ComboBox idretter;
    @FXML
    private TextField lag;


}
