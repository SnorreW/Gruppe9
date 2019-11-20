package gruppeeksamen.Controller;

import gruppeeksamen.Data.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.InitializationError;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {
    private String filStiTilArrangementer = "src/main/java/gruppeeksamen/arrangementer.csv";
    private String navnPaaArrangement;
    private String datoPaaArrangement;
    private String typeIdrettPaaArrangement;
    private LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
    private DataHandler dataHandler = new DataHandler();

    //Krav: 7.1.1, 7.1.2, 7.1.3
    @Test
    public void trueDersomAlleInputFelterErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav: 7.1.1, 7.1.2, 7.1.3
    @Test
    public void falseDersomAlleInputFelterIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = null;
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav: 7.1.1, 7.1.2, 7.1.3
    @Test
    public void falseDersomNavnPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav: 7.1.1, 7.1.2, 7.1.3
    @Test
    public void falseDersomDatoPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = "Ski";
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav: 7.1.1, 7.1.2, 7.1.3
    @Test
    public void faarFalseDersomTypeIdrettPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = null;
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav: 7.1.2.1
    @Test
    public void faarTrueDersomDatoPaaArrangementErEtterDagensDato() {
        datoPaaArrangement = "2020-11-10";
        assertTrue(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

    //Krav: 7.1.2.1
    @Test
    public void faarFalseDersomDatoPaaArrangementErForDagensDato() {
        datoPaaArrangement = "2010-11-10";
        assertFalse(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

    //Krav:
    @Test
    public void faarFalseDersomDatoPaaArrangementErSammeDagensDato() {
        Date iDag = new Date();
        LocalDate iDagLokal = iDag.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int aar = iDagLokal.getYear();
        int maaned = iDagLokal.getMonthValue();
        int dag = iDagLokal.getDayOfMonth();
        String dagensDato = aar+"-"+maaned+"-"+dag;
        LocalDate dagensDatoLocal = LocalDate.parse(dagensDato);
        datoPaaArrangement = dagensDatoLocal.toString();
        assertFalse(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

    //Krav:
    @Test
    public void faarFalseDersomArrangementPaaSammeDatoAlleredeFinnes() {
        navnPaaArrangement = "Hafslund cupen";
        datoPaaArrangement = "2019.10.30";
        typeIdrettPaaArrangement = "Loping";
        assertFalse(leggTilArrangementController.arrangementPaaSammeDatoIkkeFinnes(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav:
    @Test
    public void faarTrueDersomArrangementPaaSammeDatoAlleredeIkkeFinnes() {
        navnPaaArrangement = "Hafslund cupen";
        datoPaaArrangement = "2019.10.31";
        typeIdrettPaaArrangement = "Loping";
        assertTrue(leggTilArrangementController.arrangementPaaSammeDatoIkkeFinnes(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav:
    @Test
    public void faarTrueDersomArrangementIkkeFinnes() {
        navnPaaArrangement = "Arrangment som ikke finnes";
        datoPaaArrangement = "2020.10.31";
        typeIdrettPaaArrangement = "Loping";
        assertTrue(leggTilArrangementController.arrangementPaaSammeDatoIkkeFinnes(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //Krav:
    @Test
    public void faarTrueDersomStringMedForventetArrayErLikArrayFraController() {
        ObservableList listeForventet = FXCollections.observableArrayList();
        listeForventet.add("Ski");
        listeForventet.add("Sykkel");
        listeForventet.add("Loping");
        ObservableList listeFraController = FXCollections.observableArrayList();
        leggTilArrangementController.leggTilIdrett(listeFraController);
        boolean listeneErLike = listeForventet.sorted().equals(listeFraController.sorted());
        assertTrue(listeneErLike);
    }

    //Krav:
    @Test
    public void faarFalseDersomStringMedForventetArrayIkkeErLikArrayFraController() {
        ObservableList listeForventet = FXCollections.observableArrayList();
        listeForventet.add("Ski");
        listeForventet.add("Sykkel");
        ObservableList listeFraController = FXCollections.observableArrayList();
        leggTilArrangementController.leggTilIdrett(listeFraController);
        boolean listeneErIkkeLike = listeForventet.sorted().equals(listeFraController.sorted());
        assertFalse(listeneErIkkeLike);
    }

    //Krav:
    @Test
    public void faarTrueDersomLeggeTilArrangementErSuksess() throws IOException {
        navnPaaArrangement = "Test arrangement";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        boolean testen = leggTilArrangementController.leggeTilArrangementet(navnPaaArrangement, datoPaaArrangement, typeIdrettPaaArrangement, filStiTilArrangementer);
        assertTrue(testen);

        //fjernet linjen som er lagt til
        RandomAccessFile f = new RandomAccessFile(filStiTilArrangementer, "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10);
        f.setLength(length+1);
        f.close();
    }

    //Krav:
    @Test
    public void faarFalseDersomLeggeTilArrangementErIkkeErSuksess() {
        navnPaaArrangement = "Tistacupen";
        datoPaaArrangement = "2019-10-17";
        typeIdrettPaaArrangement = "Ski";
        boolean testen = leggTilArrangementController.leggeTilArrangementet(navnPaaArrangement, datoPaaArrangement, typeIdrettPaaArrangement, filStiTilArrangementer);
        assertFalse(testen);
    }
}