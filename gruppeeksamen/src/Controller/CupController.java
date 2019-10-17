package Controller;

import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CupController {
    private static ObservableList<String> listeMedLag = FXCollections.observableArrayList();

    @FXML
    private ListView<String> lagSomErMed;

    @FXML
    public void initialize() {
        String cup = LoggetInnController.getStagen();
        String st = LoggetInnController.getStagen(); //skal bort
        System.out.println("Stage: ----- " + st); //skal bort

        listeMedLag.clear();
        listeMedLag = DataHandler.hentDataCupLag("src/arrangementer.csv",2/*lag*/,listeMedLag,cup);
        lagSomErMed.setItems(listeMedLag);
    }
}
