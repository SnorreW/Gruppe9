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
    String filStiTilLoggetInn = "../../View/loggetInn.fxml";
    String filStiTilArrangementer = "src/main/java/gruppeeksamen/arrangementer.csv";

    @FXML
    private TextField arrangementInput;
    @FXML
    private DatePicker datoDatePicker;
    @FXML
    private ComboBox idrettComboBox;
    @FXML
    private Button btnLeggTilArrangement, btnGaaTilbake;

    @FXML
    public void initialize() {
        //lager liste og legger til aktiviteter
        ObservableList<String> idrettListe = FXCollections.observableArrayList();
        leggTilIdrett(idrettListe);
        //legger til aktivitetene i comboboxen
        idrettComboBox.setItems(idrettListe);
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) btnGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene(filStiTilLoggetInn, "Arrengementer", 500, 500);
    }

    //legger til arrangement
    @FXML
    private void leggTilArrangement(ActionEvent event) {
        //sjekker om alle "forhåndsregler" er gjort for å kunne legge til et arrangement
        if (sjekkOmAlleInputErFyltUt(arrangementInput.getText(), String.valueOf(datoDatePicker.getValue()), String.valueOf(idrettComboBox.getValue())) && sjekkOmdagensDatoErMindreEnnDatePicker(datoDatePicker.getValue().toString()) && arrangementPaaSammeDatoIkkeFinnes(arrangementInput.getText(), String.valueOf(datoDatePicker.getValue()).replace("-","."), String.valueOf(idrettComboBox.getValue()))){
            //lager en ny linje med navnet på arrangementet, antall utøvere (som fra start skal være 0), utøvere (som fra start skal være tom), datoen (åååå.mm.dd), type idrett
            String nyttArrangement = arrangementInput.getText() + ";0"/*antall utøvere*/ + ";" /*utøvere*/ + ";" + datoDatePicker.getValue().toString().replace("-",".") + ";" + idrettComboBox.getValue().toString() + "\n";
            //prøver å legge til arrangementet på sisten av arrangementer.csv
            try {
                FileWriter filenSomSkalSkrivesTil = new FileWriter(filStiTilArrangementer, true);
                filenSomSkalSkrivesTil.append(nyttArrangement);
                filenSomSkalSkrivesTil.flush();
                filenSomSkalSkrivesTil.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //lukker nåværende vindu
            Stage stagen = (Stage) btnLeggTilArrangement.getScene().getWindow();
            stagen.close();
            DataHandler.sendTilNyScene(filStiTilLoggetInn, "Arrengementer", 500, 500);
        } else {
            MainJavaFX.visAlertFeilmelding("Mangler arrangement, dato eller idrett","Må fylle inn en av delene");
        }
    }

    //sjekker slik at man ikke kan legge til arrangement med samme dato (man kan legge til arrangement på forskjellige datoer)
    public boolean arrangementPaaSammeDatoIkkeFinnes(String arrangement, String dato, String idrett) {
        ObservableList<String> listeMedArrangementer = FXCollections.observableArrayList();
        ObservableList<String> listeMedDatoer = FXCollections.observableArrayList();
        ObservableList<String> listeMedTypeIdretter = FXCollections.observableArrayList();
        String datod = dato.replace("-",".");
        ObservableList fyltListeMedArrangementer = DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 0,listeMedArrangementer);
        ObservableList fyltListeMedDatoer = DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 3,listeMedDatoer);
        ObservableList fyltListeMedTypeIdretter = DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 4,listeMedTypeIdretter);
        for (int i=0;i<fyltListeMedArrangementer.size();i++) {
            if (fyltListeMedArrangementer.get(i).equals(arrangement) && fyltListeMedDatoer.get(i).equals(datod) && fyltListeMedTypeIdretter.get(i).equals(idrett)) {
                return false;
            }
        }
        return true;
    }

    //sjekker slik at man ikke skal kunne legge til et arrangement med en dato som har vært
    public boolean sjekkOmdagensDatoErMindreEnnDatePicker(String dato) {
        Date iDag = new Date();
        LocalDate iDagLokal = iDag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int aar = iDagLokal.getYear();
        int maaned = iDagLokal.getMonthValue();
        int dag = iDagLokal.getDayOfMonth();
        String dagensDato = aar+"-"+maaned+"-"+dag;
        LocalDate dagensDatoLocal = LocalDate.parse(dagensDato);
        LocalDate datePickerLocal = LocalDate.parse(dato);

        //sjekker om dagens dato er mindre eller lik datepicker dato
        if (dagensDatoLocal.compareTo(datePickerLocal) >= 0) {
            return false;
        } else {
            return true;
        }
    }

    //sjekker om alle felter er fylt ut
    public boolean sjekkOmAlleInputErFyltUt(String arrangement, String dato, String idrett) {
        if (!arrangement.isEmpty() && dato != null && idrett != null) {
            return true;
        }
        return false;
    }

    private void leggTilIdrett(ObservableList<String> liste){
        liste.add("Ski");
        liste.add("Sykkel");
        liste.add("Loping");
    }
}