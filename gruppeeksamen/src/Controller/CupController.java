package Controller;

import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CupController {
    private static ObservableList<String> listeMedLag = FXCollections.observableArrayList();
    private static ObservableList<String> listeMedLag1 = FXCollections.observableArrayList();

    @FXML
    private ListView<String> lagSomErMed;

    @FXML
    public void initialize() {
        String cup = "Skjeberg cuppen";
        listeMedLag = DataHandler.hentDataCupLag("src/arrangementer.csv",2,listeMedLag,cup);
        lagSomErMed.setItems(listeMedLag);
    }


}
