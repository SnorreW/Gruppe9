package gruppeeksamen.Controller;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SjekkeInputFelter {
    private String navnPaaArrangement;
    private String datoPaaArrangement;
    private String typeIdrettPaaArrangement;

    @Test
    public void trueDersomAlleInputFelterErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertTrue(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomAlleInputFelterIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = null;
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomNavnPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = "Ski";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void falseDersomDatoPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = null;
        typeIdrettPaaArrangement = "Ski";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    @Test
    public void faarFalseDersomTypeIdrettPaaArrangementIkkeErFyltUt() {
        navnPaaArrangement = "Løp";
        datoPaaArrangement = "2020-10-10";
        typeIdrettPaaArrangement = null;
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmAlleInputErFyltUt(navnPaaArrangement,datoPaaArrangement,typeIdrettPaaArrangement));
    }

    //lol
    @Test
    public void faarTrueDersomDatoPaaArrangementErEtterDagensDato() {
        datoPaaArrangement = "2020-11-10";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertTrue(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }

    @Test
    public void faarFalseDersomDatoPaaArrangementErForDagensDato() {
        datoPaaArrangement = "2010-11-10";
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
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
        LeggTilArrangementController leggTilArrangementController = new LeggTilArrangementController();
        assertFalse(leggTilArrangementController.sjekkOmdagensDatoErMindreEnnDatePicker(datoPaaArrangement));
    }
}