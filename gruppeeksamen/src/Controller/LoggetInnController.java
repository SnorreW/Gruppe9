package Controller;

import Data.Arrangementer;
import Data.DataHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggetInnController {
    private static ObservableList<Arrangementer> listeArrangement = FXCollections.observableArrayList();

    @FXML
    private ListView<String> c;

    @FXML
    public void initialize() {
        c.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    sendTilNyScene("../view/cup.fxml", newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //Dette skal bort
        c.setItems(DataHandler.hentData("src/arrangementer.csv", 0/*Brukernavn*/, listeArrangement));
    }

    private void sendTilNyScene(String fxml, String cup) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1,500,500));
        stage.setTitle(cup);
        stage.showAndWait();
    }
}
