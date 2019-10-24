package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
import gruppeeksamen.Modell.Arrangementer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LeggTilArrangementController {
    @FXML
    private TextField arrangementInput;
    @FXML
    private DatePicker datoDatePicker;
    @FXML
    private ComboBox idrettComboBox;
    @FXML
    private Button meldePaaKnapp, labelGaaTilbake;

    @FXML
    public void initialize() {
        ObservableList<Arrangementer> idrettListe = FXCollections.observableArrayList();
        if (idrettListe != null) {
            idrettListe.clear();
        }
        idrettListe.add(new Arrangementer("Ski"));
        idrettListe.add(new Arrangementer("Sykkel"));
        idrettListe.add(new Arrangementer("Loping"));
        idrettComboBox.setItems(idrettListe);
    }

    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) labelGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
    }

    @FXML
    private void leggTilArrangement(ActionEvent event) {
        /*Må gjøres*/
            //slette selve arrangement fra csv-fil i loggetInn.fxml
            //fjerne arrangementer som har vært fra csv-fil
        if (sjekkOmAlleInputErFyltUt() && sjekkOmdagensDatoErMindreEnnDatePicker() && arrangementPaaSammeDatoIkkeFinnes()){
            String nyLinje = arrangementInput.getText() + ";0"/*antall lag*/ + ";" /*lag*/ + ";" + datoDatePicker.getValue().toString().replace("-",".") + ";" + idrettComboBox.getValue().toString() + "\n";
            try {
                FileWriter filen = new FileWriter("src/gruppeeksamen/arrangementer.csv", true);
                filen.append(nyLinje);
                filen.flush();
                filen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stagen = (Stage) meldePaaKnapp.getScene().getWindow();
            stagen.close();
            DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
        } else {
            MainJavaFX.visAlertFeilmelding("Mangler arrangement, dato eller idrett","Må fylle inn en av delene");
        }
    }

    private boolean arrangementPaaSammeDatoIkkeFinnes() {
        ObservableList<Arrangementer> listeMedCuper = FXCollections.observableArrayList();
        ObservableList<Arrangementer> listeMedDatoer = FXCollections.observableArrayList();
        ObservableList<Arrangementer> listeMedTyper = FXCollections.observableArrayList();
        ObservableList fyltListeMedCuper = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 0,listeMedCuper);
        ObservableList fyltListeMedDatoer = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 3,listeMedDatoer);
        ObservableList fyltListeMedTyper = DataHandler.hentDataDel("src/gruppeeksamen/arrangementer.csv", 4,listeMedTyper);
        for (int i=0;i<fyltListeMedCuper.size();i++) {
            if (fyltListeMedCuper.get(i).equals(arrangementInput.getText()) && fyltListeMedDatoer.get(i).equals(datoDatePicker.getValue().toString().replace("-",".")) && fyltListeMedTyper.get(i).equals(idrettComboBox.getValue().toString())) {
                System.out.println("hah");
                return false;
            }
        }
        return true;
    }

    private boolean sjekkOmdagensDatoErMindreEnnDatePicker() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        String dagensDato = year+"."+month+"."+day;
        String datePicker = datoDatePicker.getValue().toString().replace("-",".");
        String[] dagensDatoArray = dagensDato.split("\\.");
        String[] datePickerArray = datePicker.split("\\.");

        //sjekker om dagens dato er mindre eller lik datepicker dato
        if ((Integer.parseInt(dagensDatoArray[0]) <= Integer.parseInt(datePickerArray[0])) && (Integer.parseInt(dagensDatoArray[1]) <= Integer.parseInt(datePickerArray[1])) && (Integer.parseInt(dagensDatoArray[2]) <= Integer.parseInt(datePickerArray[2]))) {
            return true;
        }

        return false;
    }

    private boolean sjekkOmAlleInputErFyltUt() {
        //sjekker om alle felter er fylt ut
        if (!arrangementInput.getText().isEmpty() && datoDatePicker.getValue() != null && idrettComboBox.getValue() != null) {
            return true;
        }
        return false;
    }
}
