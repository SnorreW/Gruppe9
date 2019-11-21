package gruppeeksamen.Data;

import gruppeeksamen.Modell.Utover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NyDataHandlerTest {
    @Test
    public void girTrueHvisFilstiErUlik() {
        File forventetRiktig = new File("src/main/java/gruppeeksamen/brukere.csv");
        File forventetIkkeRiktig = new File("src/main/java/gruppeeksamen/bruk.csv");

        Assert.assertNotEquals(forventetRiktig, forventetIkkeRiktig);
    }

    @Test
    public void gir() {
        Utover enUtover = NyDataHandler.hentUtoverData().get(0);

    }
}
