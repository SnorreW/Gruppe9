package gruppeeksamen.Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementControllerTest {
    private ArrangementController arrangementController = new ArrangementController();

    @Test
    private void sjekkHjelpOgfylleListe(){

        String arrangementSomErValgt = "Tistacupen";
        ArrayList list = new ArrayList();
        list.add("Tistacupen;1;Ole;2019.10.17;Ski");
        //list.add("Skjeberg cupen;1;PEtter Nor;2019.10.28;Ski");
        //assertEquals(,arrangementController.hjelpTilOgFylleListe(list,arrangementSomErValgt));

    }


}