package gruppeeksamen.Modell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UtoverTest {
    @Test
    @DisplayName("Utøver")
    public void getNavn(){

        Utover utover = new Utover("SSK","Jon", "Fredriksen");
        assertEquals("Jon", utover.getNavn(), "utover");
    }

}
