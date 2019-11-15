package gruppeeksamen.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {
    private DataHandler dataHandler = new DataHandler();
    private ObservableList<String> testListe  = FXCollections.observableArrayList();

    @Test
    public void falseDersomFilstiErFeilNaarManSkalLeseFraFilDel() {
        assertFalse((BooleanSupplier) dataHandler.hentDataDel("legitFilsti.csv", 0, testListe));
    }
}