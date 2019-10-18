package Controller;

import Data.DataHandler;
import Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MeldPaaLagController {
    private static ObservableList<Arrangementer> idrettListe = FXCollections.observableArrayList();
    private static ObservableList<Arrangementer> cuperListe  = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Arrangementer> idretter, cup;
    @FXML
    private TextField lag;

    @FXML
    public void initialize() {
        idrettListe.add(new Arrangementer("Ski"));
        idrettListe.add(new Arrangementer("Sykkel"));
        idrettListe.add(new Arrangementer("Loping"));
        idretter.setItems(idrettListe);
    }

    @FXML
    private void oppdaterCup(ActionEvent value) {
        DataHandler.hentDataCuper("src/arrangementer.csv", 0/*CupNavn*/, cuperListe, idretter.getValue());
        if (cup.getValue() != null) {
            cup.getItems().clear();
        }
        cup.setItems(cuperListe);
    }

    @FXML
    private void sendTilFil(ActionEvent value) {
        sendTilFilen(idretter.getValue(), lag.getText());
    }

    private void sendTilFilen(Arrangementer idretten, String klubben) {
        System.out.println(idretten + " - " + klubben);
    }
}
