package gruppeeksamen.Controller;

import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Arrangementer;
import gruppeeksamen.Data.DataHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LoggetInnController {
    private static ObservableList<String> nyListe = FXCollections.observableArrayList();
    private static ObservableList<String> slettListe = FXCollections.observableArrayList();
    private static String stagen = null;

    @FXML
    private ListView<String> cup;
    @FXML
    private Button loggUt;

    @FXML
    public void initialize() {
        nyListe.clear();
        cup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                sendTilNyScene("../view/cup.fxml", newValue);
            }
        });
        cup.setItems(fyllListe());
    }

    private ObservableList<String> fyllListe() {
        ArrayList loo = new ArrayList();
        ObservableList<String> slettListen = DataHandler.hentDataHele("src/gruppeeksamen/arrangementer.csv", slettListe);
        ArrayList l = new ArrayList<>(slettListen);
        String utskrift;
        for (int i=0; i<slettListen.size();i++) {
            ArrayList lo = (ArrayList) l.get(i);
            utskrift = lo.get(3) + ": " + lo.get(0);
            loo.add(utskrift);
        }
        slettListen = FXCollections.observableArrayList(loo);

        return slettListen;
    }

    private void sendTilNyScene(String fxml, String cup) {
        try {
            String[] li = cup.split(": ");
            setStagen(li[1]);
            FXMLLoader fxmlLoader = new FXMLLoader(DataHandler.class.getResource(fxml));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(li[1]);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1,500,500));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void meldPaaLaget(ActionEvent event) {
        DataHandler.sendTilNyScene("../view/meldPaaLag.fxml", "Meld på laget ditt", 500, 500);
    }

    @FXML
    private void leggTilArrangement(ActionEvent event) {
        DataHandler.sendTilNyScene("../view/leggTilArrangement.fxml", "Legg til arrangement", 500, 500);
    }

    @FXML
    private void loggUtBruker(ActionEvent event) {
        Stage stage = (Stage) loggUt.getScene().getWindow();
        stage.close();
        MainJavaFX.gaaTilHovedVisning();
    }

    public static String getStagen() {
        return stagen;
    }

    public void setStagen(String stagen) {
        this.stagen = stagen;
    }
}
