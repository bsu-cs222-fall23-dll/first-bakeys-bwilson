package WikipediaRevisionHistory.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorModal {
        @FXML
        private Label errorMessageLabel;
        @FXML
        private Button closeButton;

        public void setErrorMessage(String message) {
            errorMessageLabel.setText(message);
        }
        @FXML
        public void closeModal() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        }
    }

