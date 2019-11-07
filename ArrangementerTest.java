package gruppeeksamen.Modell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrangementerTest {

    @Test
    @DisplayName("Navn burde returnere navn")
    public void getNavn(){

        Arrangementer navn = new Arrangementer("Løpe turnering");
       assertEquals("Løpe turnering", navn.getNavn(), "Navn");
        }


    }
