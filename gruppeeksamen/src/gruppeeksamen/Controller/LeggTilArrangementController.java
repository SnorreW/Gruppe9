package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
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
import java.util.Date;

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
        //lager liste og legger til aktiviteter
        ObservableList<String> idrettListe = FXCollections.observableArrayList();
        idrettListe.add("Ski");
        idrettListe.add("Sykkel");
        idrettListe.add("Loping");
        //legger til aktivitetene i comboboxen
        idrettComboBox.setItems(idrettListe);
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) labelGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
    }

    //legger til arrangement
    @FXML
    private void leggTilArrangement(ActionEvent event) {
        //sjekker om alle "forhåndsregler" er gjort for å kunne legge til et arrangement
        if (sjekkOmAlleInputErFyltUt() && sjekkOmdagensDatoErMindreEnnDatePicker() && arrangementPaaSammeDatoIkkeFinnes()){
            //lager en ny linje med navnet på arrangementet, antall lag (som fra start skal være 0), lagene (som fra start skal være tom), datoen (åååå.mm.dd), type idrett
            String nyLinje = arrangementInput.getText() + ";0"/*antall lag*/ + ";" /*lag*/ + ";" + datoDatePicker.getValue().toString().replace("-",".") + ";" + idrettComboBox.getValue().toString() + "\n";
            //prøver å legge til arrangementet på sisten av arrangementer.csv
            try {
                FileWriter filen = new FileWriter("src/gruppeeksamen/arrangementer.csv", true);
                filen.append(nyLinje);
                filen.flush();
                filen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //lukker nåværende vindu
            Stage stagen = (Stage) meldePaaKnapp.getScene().getWindow();
            stagen.close();
            //åpner loggetinn.fxml
            DataHandler.sendTilNyScene("../view/loggetInn.fxml", "Arrengementer", 500, 500);
        } else {
            MainJavaFX.visAlertFeilmelding("Mangler arrangement, dato eller idrett","Må fylle inn en av delene");
        }
    }

    //sjekker slik at man ikke kan legge til arrangement med samme dato (man kan legge til arrangement på forskjellige datoer)
    private boolean arrangementPaaSammeDatoIkkeFinnes() {
        ObservableList<String> listeMedCuper = FXCollections.observableArrayList();
        ObservableList<String> listeMedDatoer = FXCollections.observableArrayList();
        ObservableList<String> listeMedTyper = FXCollections.observableArrayList();
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

    //sjekker slik at man ikke skal kunne legge til et arrangement med en dato som har vært
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

    //sjekker om alle felter er fylt ut
    private boolean sjekkOmAlleInputErFyltUt() {
        if (!arrangementInput.getText().isEmpty() && datoDatePicker.getValue() != null && idrettComboBox.getValue() != null) {
            return true;
        }
        return false;
    }
}
