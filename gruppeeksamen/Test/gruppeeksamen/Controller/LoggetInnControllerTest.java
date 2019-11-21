package gruppeeksamen.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggetInnControllerTest {
    private LoggetInnController loggetInnController = new LoggetInnController();
    @Test
    public void f() {
        ObservableList listeForventet = FXCollections.observableArrayList();
        listeForventet.add("");
        loggetInnController.fyllListe();

    }
}