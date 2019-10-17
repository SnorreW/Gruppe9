package Controller;

import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class CupController {
    private static ObservableList<String> listeMedLag = FXCollections.observableArrayList();

    @FXML
    private ListView<String> lagSomErMed;
    @FXML
    private Button labelGaaTilbake;

    @FXML
    public void initialize() {
        String cup = LoggetInnController.getStagen();

        listeMedLag.clear();
        listeMedLag = DataHandler.hentDataCupLag("src/arrangementer.csv",2/*lag*/,listeMedLag,cup);
        lagSomErMed.setItems(listeMedLag);
    }

    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) labelGaaTilbake.getScene().getWindow();
        stage.close();
    }
}
