package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeldPaaUtoverControllerTest {
    private String filStiTilArrangementer = "src/main/java/gruppeeksamen/arrangementer.csv";
    private MeldPaaUtoverController meldPaaUtoverController = new MeldPaaUtoverController();

    @Test
    public void faarTrueNaarManFyllerUtRiktigICombobox() {
        ObservableList<String> arrangementListe  = FXCollections.observableArrayList();
        ObservableList<String> arrangementerMedRiktigTypeIdrettListe  = FXCollections.observableArrayList();
        ObservableList<String> typeIdrettListe  = FXCollections.observableArrayList();
        DataHandler.hentDataDel(filStiTilArrangementer, 4/*CupNavn*/, typeIdrettListe);
        DataHandler.hentDataDel(filStiTilArrangementer, 0/*CupNavn*/, arrangementListe);
        String idrett = "Ski";
        ObservableList forventet = meldPaaUtoverController.fyllNyttArrangement(arrangementListe, arrangementerMedRiktigTypeIdrettListe, typeIdrettListe, idrett);
        ObservableList faktisk = meldPaaUtoverController.leggTilArrangement(idrett);
        assertEquals(forventet, faktisk);
    }
}