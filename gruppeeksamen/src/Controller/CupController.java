package Controller;

import Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @FXML
    private void slettLag(ActionEvent event) throws IOException {
        String cup = LoggetInnController.getStagen();
        String lagetSomSkalSlettes = lagSomErMed.getSelectionModel().getSelectedItem();
        slettBestemtLagICup("src/arrangementer.csv", cup, lagetSomSkalSlettes);
    }

    private void slettBestemtLagICup(String filenSomLesesFra, String cup, String lagetSomSkalSlettes) throws IOException {
        File filSomLesesFra = new File(filenSomLesesFra);
        ArrayList<String> dataFraFil = new ArrayList<>();
        String nyLag = "";
        String nyLagLinje = "";
        int antallLag;

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            String linjen;
            String etterSlettetLag;
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");
                linjen = deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4];
                if (deler[0].equals(cup)) {
                    String[] lagene = deler[2].split("\\|");
                    List<String> lageneList = new ArrayList<String>(Arrays.asList(lagene));
                    lageneList.remove(lagetSomSkalSlettes);
                    lagene= lageneList.toArray(new String[0]);
                    nyLag = Arrays.toString(lagene);
                    nyLag = nyLag.substring(1, nyLag.length()-1).replace(", ", "|");
                    antallLag = Integer.parseInt(deler[1]) - 1;
                    nyLagLinje += deler[0] + ";" + antallLag + ";" + nyLag + ";" + deler[3] + ";" + deler[4] + "\n";
                } else {
                    nyLagLinje += deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4] + "\n";
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        FileWriter skriv = new FileWriter(filenSomLesesFra);
        skriv.write(nyLagLinje);
        skriv.flush();
        skriv.close();

        listeMedLag.clear();
        listeMedLag = DataHandler.hentDataCupLag("src/arrangementer.csv",2/*lag*/,listeMedLag,cup);
        lagSomErMed.setItems(listeMedLag);
    }
}
