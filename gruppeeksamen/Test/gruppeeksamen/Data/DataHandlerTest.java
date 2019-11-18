package gruppeeksamen.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {
    private DataHandler dataHandler = new DataHandler();
    private ObservableList testListe  = FXCollections.observableArrayList();

    @Test
    public void falseDersomFilstiErFeilNaarManSkalLeseFraFilDel() throws IOException {
        dataHandler.lesFraCSVFilDel(new File("tja"), 1);
    }
}