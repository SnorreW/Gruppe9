package Controller;

import Modell.Arrangementer;
import Data.DataHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggetInnController {
    private static ObservableList<Arrangementer> listeCuperNavn = FXCollections.observableArrayList();
    private static ObservableList<Arrangementer> listeCuperDato = FXCollections.observableArrayList();
    private static ObservableList<String> nyListe = FXCollections.observableArrayList();
    private static String stagen = null;

    @FXML
    private ListView<String> cup;

    @FXML
    public void initialize() {
        cup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    sendTilNyScene("../view/cup.fxml", newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        String l = "";
        ObservableList<String> cuperNavn = DataHandler.hentData("src/arrangementer.csv", 0/*Navn*/, listeCuperNavn);
        ObservableList<String> cuperDato = DataHandler.hentData("src/arrangementer.csv", 3/*Dato*/, listeCuperDato);
        for (int i=0; i < cuperNavn.size(); i++) {
            l = "";
            l += cuperDato.get(i) + ": " + cuperNavn.get(i);
            nyListe.add(l);
        }
        cup.setItems(nyListe);
    }

    private void sendTilNyScene(String fxml, String cup) throws IOException {
        String[] li = cup.split(": ");
        setStagen(li[1]);
        FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(li[1]);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1,500,500));
        stage.show();
    }

    @FXML
    private void meldPaaLaget(ActionEvent event) throws IOException {
        DataHandler.sendTilNyScene("../view/meldPaaLag.fxml", "Meld på laget ditt");
    }

    public static String getStagen() {
        return stagen;
    }

    public void setStagen(String stagen) {
        this.stagen = stagen;
    }
}
