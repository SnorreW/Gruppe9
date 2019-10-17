import Controller.NyBrukerController;
import Modell.Bruker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
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

    public static Stage getStage() {
        return primaryStage;
    }

    public void gaaTilHovedVisning() {

        try {
            FXMLLoader fxmlInnlaster = new FXMLLoader();
            fxmlInnlaster.setLocation(getClass().getResource("view/sample.fxml"));
            Parent root = fxmlInnlaster.load();
            primaryStage.setTitle("Logg inn");
            primaryStage.setScene(new Scene(root, 500, 500));
            primaryStage.show();
        }
        catch (IOException ioe) {
            visAlertFeilmelding("I/O feil: ", ioe.getMessage());
        }
        catch (IllegalStateException ise) {
            visAlertFeilmelding("Feil 41\nFant ikke grensesnittdefinisjon", ise.getMessage());
        }
    }

    /*public boolean visNyBrukerVindu(Bruker nyBruker) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("View/nyBrukerVindu.fxml"));

            Parent dialogLayout = fxmlLoader.load();

            Stage dialogStage = new Stage();

            dialogStage.setTitle("Legg Til Ny Bruker");

            dialogStage.initModality(Modality.WINDOW_MODAL);

            dialogStage.initOwner(primaryStage);

            Scene dialogScene = new Scene(dialogLayout);

            dialogStage.setScene(dialogScene);

            NyBrukerController nyBrukerController = fxmlLoader.getController();

            nyBrukerController.setNyBruker(nyBruker);

            dialogStage.showAndWait();

            return NyBrukerController.erOkValgt();
        }
        catch (IOException ioe) {
            visAlertFeilmelding("I/O feil: ", ioe.getMessage());
        }
        catch (IllegalStateException ise) {
            visAlertFeilmelding("Feil 41\nFant ikke grensesnittdefinisjon", ise.getMessage());
        }
        return false;
    }*/

    private void visAlertFeilmelding(String overskrift, String melding) {
        Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
        exceptionAlert.setTitle("Feil");
        exceptionAlert.setHeaderText(overskrift);
        exceptionAlert.setContentText(melding);

        exceptionAlert.showAndWait();
    }

    public static MainJavaFX getInstance() {
        return minApplikasjon;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
