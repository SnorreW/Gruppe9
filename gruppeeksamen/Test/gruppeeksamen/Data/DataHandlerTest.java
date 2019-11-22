package gruppeeksamen.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {
    private ObservableList testListe  = FXCollections.observableArrayList();
    private ObservableList testListe2  = FXCollections.observableArrayList();

    //Krav: 10.2
    @Test
    public void hentDataHeleTestTrue(){
        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        assertTrue(testListe.size() > testListe2.size());
    }

    //Krav: 10.2
    @Test
    public void hentDataHeleTestFalse(){
        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        assertFalse(testListe.size() < testListe2.size());
    }

    //Krav: 10.2
    @Test
    public void hentDataHeleTestHvorBeggeListeneFylles(){
        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe);
        DataHandler.hentDataHele("src/main/java/gruppeeksamen/arrangementer.csv", testListe2);
        assertTrue(testListe.size() == testListe2.size());
    }

    //Krav: 10.2
    @Test
    public void hentDataDelTestHvorBeggeListeneFylles(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe2);
        assertTrue(testListe.size() == testListe2.size());
    }

    //Krav: 10.2
    @Test
    public void hentDataDelTestHvorBeggeListeneFyllesFalse(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        assertFalse(testListe.size() < testListe2.size());
    }

    //Krav: 10.2
    @Test
    public void hentDataDelTestHvorBeggeListeneFyllesTrue(){
        DataHandler.hentDataDel("src/main/java/gruppeeksamen/arrangementer.csv", 1, testListe);
        assertTrue(testListe.size() > testListe2.size());
    }
}