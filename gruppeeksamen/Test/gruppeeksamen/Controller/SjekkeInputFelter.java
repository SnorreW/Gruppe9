package gruppeeksamen.Controller;

import org.junit.jupiter.api.Test;


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
}