package Controller;

import Data.Arrangementer;
import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Comparator;


public class LoggetInnController {
    private static ObservableList<Arrangementer> listeArrangement = FXCollections.observableArrayList();

    @FXML
    private ListView c;
    @FXML
    private Button knappSortereNavn;

    @FXML
    private void sorterListview(ActionEvent event) {
        Comparator<Arrangementer> comparator = Comparator.comparing(Arrangementer::getNavn);
        listeArrangement.sort(comparator);
    }

    @FXML
    public void initialize() {
        //Dette skal bort
        c.setItems(DataHandler.hentData("src/arrangementer.csv", 0/*Brukernavn*/, listeArrangement));
    }
}
