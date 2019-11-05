package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
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
        //Henter stagen som blir satt fra elementet man trykker på i listviewet i loggetinn.fxml
        String cup = LoggetInnController.getStagen();

        //sender til metode som fyller listview
        fyllListen(lagSomErMed, cup);
    }

    private void fyllListen(ListView<String> lagSomErMed, String cup) {
        //Tømmer listen, før den fyller listen med lag som er med i cupen
        listeMedLag.clear();
        listeMedLag = DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv",listeMedLag);
        ArrayList listeMedLagene = new ArrayList(listeMedLag);
        for (int i=0;i<listeMedLag.size();i++){
            ArrayList listeMedLageneLag = (ArrayList) listeMedLagene.get(i);
            if (listeMedLageneLag.get(0).equals(cup)) {
                String[] lagene = listeMedLageneLag.get(2).toString().split("\\|");
                listeMedLag.clear();
                for (int o=0;o<lagene.length;o++) {
                    listeMedLag.add(lagene[o]);
                }
            }
        }
        lagSomErMed.setItems(listeMedLag);
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) labelGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene("../../view/loggetInn.fxml", "Arrengementer", 500, 500);
    }

    @FXML
    private void slettLag(ActionEvent event) {
        String cup = LoggetInnController.getStagen();
        String lagetSomSkalSlettes = lagSomErMed.getSelectionModel().getSelectedItem();
        //Kjører metode som sletter bestemt lag
        slettBestemtLagICup(cup, lagetSomSkalSlettes);
    }

    private void slettBestemtLagICup(String cup, String lagetSomSkalSlettes) {
        File filSomLesesFra = new File("src/main/java/gruppeeksamen/arrangementer.csv");
        String nyLag = "";
        String nyLagLinje = "";
        int antallLag;

        //leser fra listen
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            //går gjennom listen så lenge en linje ikke er tom/null
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");
                //sjekker om deler[0] er lik navnet på cupen som blir sendt med inn i metoden
                if (deler[0].equals(cup)) {
                    //hvis if statmenten stemmer, lager string array fra deler[2]/lagene i arrangementet, hvor den blir splittet opp
                    String[] lagene = deler[2].split("\\|");
                    //lager en liste av string arrayen
                    List<String> lageneList = new ArrayList<String>(Arrays.asList(lagene));
                    //sletter laget som skal slettes
                    lageneList.remove(lagetSomSkalSlettes);
                    lagene = lageneList.toArray(new String[0]);
                    nyLag = Arrays.toString(lagene);
                    nyLag = nyLag.substring(1, nyLag.length()-1).replace(", ", "|");
                    //setter antall lag i arrangementet til det den var minus en
                    antallLag = Integer.parseInt(deler[1]) - 1;
                    //legger til den nye linjen
                    nyLagLinje += deler[0] + ";" + antallLag + ";" + nyLag + ";" + deler[3] + ";" + deler[4] + "\n";
                } else {
                    //hvis if statmenten ikke stemmer, legg til linje som var fra før
                    nyLagLinje += deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4] + "\n";
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            //skriver til arrangement filen med en oppdatert liste etter å ha fjernet laget
            FileWriter skriv = new FileWriter("src/main/java/gruppeeksamen/arrangementer.csv");
            BufferedWriter skrive = new BufferedWriter(skriv);
            skrive.write(nyLagLinje);
            skrive.flush();
            skrive.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //oppdaterer listview
        fyllListen(lagSomErMed, cup);
    }
}
