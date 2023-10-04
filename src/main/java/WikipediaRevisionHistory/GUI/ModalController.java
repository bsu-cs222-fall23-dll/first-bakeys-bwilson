package WikipediaRevisionHistory.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ModalController {
    public void showErrorModal(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modal.fxml"));
            Parent root = loader.load();

            ErrorModal modalController = loader.getController();
            modalController.setErrorMessage(message);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Error");
            modalStage.setScene(new Scene(root));
            modalStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
