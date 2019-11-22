package gruppeeksamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainJavaFX extends Application {
    private static Stage primaryStage;
    private static MainJavaFX minApplikasjon;

    public MainJavaFX() {
        minApplikasjon = this;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        gaaTilHovedVisning();
    }

    public static void gaaTilHovedVisning() {

        try {
            FXMLLoader fxmlInnlaster = new FXMLLoader();
            fxmlInnlaster.setLocation(MainJavaFX.class.getResource("../View/loggInn.fxml"));
            Parent root = fxmlInnlaster.load();
            primaryStage.setTitle("Logg inn");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();
        }
        catch (IOException ioe) {
            visAlertFeilmelding("I/O feil: ", ioe.getMessage());
            System.out.println(ioe.getMessage());
        }
        catch (IllegalStateException ise) {
            visAlertFeilmelding("Feil 41\nFant ikke grensesnittdefinisjon", ise.getMessage());
        }
    }

    public static void visAlertFeilmelding(String overskrift, String melding) {
        Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
        exceptionAlert.setTitle("Feil");
        exceptionAlert.setHeaderText(overskrift);
        exceptionAlert.setContentText(melding);

        exceptionAlert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
