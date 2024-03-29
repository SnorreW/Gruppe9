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
    private ObservableList testListe2  = FXCollections.observableArrayList();

    @Test
    public void hentDataHeleTestTrue(){

        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        assertTrue(testListe.size() > testListe2.size());

    }
    @Test
    public void hentDataHeleTestFalse(){

        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        assertFalse(testListe.size() < testListe2.size());
    }
    @Test
    public void hentDataHeleTestHvorBeggeListeneFylles(){

        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe2);
        assertTrue(testListe.size() == testListe2.size());
    }

    @Test
    public void hentDataDelTestHvorBeggeListeneFylles(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe2);
        assertTrue(testListe.size() == testListe2.size());
    }
    @Test
    public void hentDataDelTestHvorBeggeListeneFyllesFalse(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        assertFalse(testListe.size() < testListe2.size());
    }
    @Test
    public void hentDataDelTestHvorBeggeListeneFyllesTrue(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        assertTrue(testListe.size() > testListe2.size());
    }

    @Test
    public void sjekkAtCatchFungerer(){

        //Test som skal sjekke at det blir kastet IOE exception
        /*File fil = new File("feilFil");
        {
            assertThrows(IOException.class,() -> DataHandler.lesFraCSVFilDel(new File(String.valueOf(fil)),1));
        }*/
    }

}