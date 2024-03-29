package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import gruppeeksamen.MainJavaFX;
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

public class ArrangementController {
    private static ObservableList<String> listeMedUtovere = FXCollections.observableArrayList();
    private String filstiArrangementerCSV = "src/main/java/gruppeeksamen/arrangementer.csv";
    private String filstiLoggetInnFXML = "../../view/loggetInn.fxml";

    @FXML
    private ListView<String> listeMedUtovereSomErMed;
    @FXML
    private Button btnGaaTilbake;

    @FXML
    public void initialize() {
        //Henter stagen som blir satt fra elementet man trykker på i listviewet i loggetinn.fxml
        String arrangementSomErValgt = LoggetInnController.getStagen();
        //sender til metode som fyller listview
        fyllListen(listeMedUtovereSomErMed, arrangementSomErValgt);
    }

    private void fyllListen(ListView<String> utovereSomErMed, String arrangement) {
        //Tømmer listen, før den fyller listen med utøvere som er med i arrangementet
        listeMedUtovere.clear();
        listeMedUtovere = DataHandler.hentDataHele(filstiArrangementerCSV, listeMedUtovere);
        ArrayList arrayListSomSkalFylleListen = new ArrayList(listeMedUtovere);
        hjelpTilOgFylleListe(arrayListSomSkalFylleListen, arrangement);
        utovereSomErMed.setItems(listeMedUtovere);
    }

    //Går tilbake til forrige vindu
    @FXML
    private void gaaTilbake(ActionEvent event) {
        Stage stage = (Stage) btnGaaTilbake.getScene().getWindow();
        stage.close();
        DataHandler.sendTilNyScene(filstiLoggetInnFXML, "Arrengementer", 500, 500);
    }

    @FXML
    private void slettUtover(ActionEvent event) {
        String arrangement = LoggetInnController.getStagen();
        String utoverSomSkalSlettes = listeMedUtovereSomErMed.getSelectionModel().getSelectedItem();
        //Kjører metode som sletter bestemt utøver
        boolean slettetUtover = slettBestemtUtoverIArrangement(arrangement, utoverSomSkalSlettes);
        if (slettetUtover) {
            //oppdaterer listview
            fyllListen(listeMedUtovereSomErMed, arrangement);
        } else {
            MainJavaFX.visAlertFeilmelding("Feil", "Feil");
        }
    }

    public void hjelpTilOgFylleListe(ArrayList liste, String arrangement) {
        for (int i = 0; i < liste.size(); i++) {
            ArrayList arrayListeMedUtoverene = (ArrayList) liste.get(i);
            if (arrayListeMedUtoverene.get(0).equals(arrangement)) {
                String[] utoverene = arrayListeMedUtoverene.get(2).toString().split("\\|");
                listeMedUtovere.clear();
                for (int o = 0; o < utoverene.length; o++) {
                    listeMedUtovere.add(utoverene[o]);
                }
            }
        }
    }

    public boolean slettBestemtUtoverIArrangement(String arrangement, String utoverenSomSkalSlettes) {
        File filSomLesesFra = new File(filstiArrangementerCSV);
        String nyUtover = "";
        String nyUtoverLinje = "";
        int antallUtovere;

        //leser fra listen
        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            String linje;
            //går gjennom listen så lenge en linje ikke er tom/null
            while( (linje = bufretLeser.readLine()) != null ){
                String[] deler = linje.split(";");
                //sjekker om deler[0] er lik navnet på cupen som blir sendt med inn i metoden
                if (deler[0].equals(arrangement)) {
                    //hvis if statmenten stemmer, lager string array fra deler[2]/utøverene i arrangementet, hvor den blir splittet opp
                    String[] utoverene = deler[2].split("\\|");
                    //lager en liste av string arrayen
                    List<String> utovereneList = new ArrayList<String>(Arrays.asList(utoverene));
                    //sletter utøveren som skal slettes
                    utovereneList.remove(utoverenSomSkalSlettes);
                    utoverene = utovereneList.toArray(new String[0]);
                    nyUtover = Arrays.toString(utoverene);
                    nyUtover = nyUtover.substring(1, nyUtover.length()-1).replace(", ", "|");
                    //setter antall utøvere i arrangementet til det den var minus en
                    if (Integer.parseInt(deler[1]) <= 0) {
                        antallUtovere = 0;
                    } else {
                        antallUtovere = Integer.parseInt(deler[1]) - 1;
                    }
                    //legger til den nye linjen
                    nyUtoverLinje += deler[0] + ";" + antallUtovere + ";" + nyUtover + ";" + deler[3] + ";" + deler[4] + "\n";
                } else {
                    //hvis if statmenten ikke stemmer, legg til linje som var fra før
                    nyUtoverLinje += deler[0] + ";" + deler[1] + ";" + deler[2] + ";" + deler[3] + ";" + deler[4] + "\n";
                }

            }
            oppdaterListe(filstiArrangementerCSV, nyUtoverLinje);
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public void oppdaterListe(String filstiArrangementerCSV, String nyUtoverLinje){
        try {
            //skriver til arrangement filen med en oppdatert liste etter å ha fjernet utøveren
            FileWriter skriver = new FileWriter(filstiArrangementerCSV);
            BufferedWriter skrive = new BufferedWriter(skriver);
            skrive.write(nyUtoverLinje);
            skrive.flush();
            skrive.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
