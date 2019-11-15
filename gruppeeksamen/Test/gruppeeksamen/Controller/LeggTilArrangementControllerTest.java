package gruppeeksamen.Controller;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LeggTilArrangementControllerTest {
    private String navnPaaArrangement;
    private String datoPaaArrangement;
    private String typeIdrettPaaArrangement;
    private LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();

    @Test
    public void trueDersomAlleInputFelterErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomAlleInputFelterIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = null;
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomNavnPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomDatoPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = "Ski";
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void faarFalseDersomTypeIdrettPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = null;
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //lol
    @Test
    public void faarTrueDersomDatoPaaArrangementErEtterDagensDato() {
        datoPaaArrangement = "2020-11-10";
        assertTrue(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

    @Test
    public void faarFalseDersomDatoPaaArrangementErForDagensDato() {
        datoPaaArrangement = "2010-11-10";
        assertFalse(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

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
}