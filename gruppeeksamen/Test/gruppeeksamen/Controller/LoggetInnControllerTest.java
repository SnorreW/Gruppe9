package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoggetInnControllerTest {
    private LoggetInnController loggetInnController = new LoggetInnController();
    private String filStiTilArrangementer = "src/main/java/gruppeeksamen/arrangementer.csv";

    @Test
    public void faarTrueDersomListeneErLike() {
        ObservableList listeForventet = FXCollections.observableArrayList();
        ObservableList listeForventet2 = FXCollections.observableArrayList();
        DataHandler.hentDataHele(filStiTilArrangementer, listeForventet);
        for (int i = 0;i<listeForventet.size();i++) {
            ArrayList listeForventet1 = (ArrayList) listeForventet.get(i);
            listeForventet2.add(listeForventet1.get(3) + ": " + listeForventet1.get(0) + " (" + listeForventet1.get(4) + ")");
        }
        boolean ikkeLikeLister = (listeForventet2.equals(loggetInnController.fyllListe()));
        assertTrue(ikkeLikeLister);
    }

    @Test
    public void faarFalseDersomListeneIkkeErLike() {
        ObservableList listeForventet = FXCollections.observableArrayList();
        ObservableList listeForventet2 = FXCollections.observableArrayList();
        DataHandler.hentDataHele(filStiTilArrangementer, listeForventet);
        for (int i = 0;i<listeForventet.size();i++) {
            ArrayList listeForventet1 = (ArrayList) listeForventet.get(i);
            listeForventet2.add(listeForventet1.get(3) + ": " + listeForventet1.get(0) + " (" + listeForventet1.get(4) + ")");
        }
        listeForventet2.add("tja");
        boolean ikkeLikeLister = (listeForventet2.equals(loggetInnController.fyllListe()));
        assertFalse(ikkeLikeLister);
    }

    @Test
    public void faarTrueDersomSceneNavnErRiktig() {
        String linjeFraListView = "2020.10.10: Tistacuppen (Ski)";
        String forventet = "Tistacuppen";
        String sceneFraFunksjon = loggetInnController.setSceneen(linjeFraListView);
        assertEquals(forventet, sceneFraFunksjon);
    }

    @Test
    public void faarTrueDersomSceneNavnErFeil() {
        String linjeFraListView = "2020.10.10: Tistacuppen (Ski)";
        String forventet = "Ikke Tistacuppen";
        String sceneFraFunksjon = loggetInnController.setSceneen(linjeFraListView);
        boolean ikkeLike = forventet.equals(sceneFraFunksjon);
        assertFalse(ikkeLike);
    }

    @Test
    public void faarTrueDersomLinjenIkkeDelesOpp() {
        String linjeFraListView = "2020.10.10: Tistacuppen (Ski)";
        String sceneFraFunksjon = loggetInnController.setSceneen(linjeFraListView);
        boolean ikkeLike = linjeFraListView.equals(sceneFraFunksjon);
        assertFalse(ikkeLike);
    }
}