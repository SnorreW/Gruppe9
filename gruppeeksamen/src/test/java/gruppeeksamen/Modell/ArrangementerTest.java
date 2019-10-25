package gruppeeksamen.Modell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementerTest {

    @Test
    @DisplayName("Navn burde returnere navn")
    public void getNavn(){

        Arrangementer a = new Arrangementer("Hei og hå!");
       assertEquals("Hei og hå!", a.getNavn(), " hei hei");
        }
    }
