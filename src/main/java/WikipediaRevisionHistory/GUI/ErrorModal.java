package WikipediaRevisionHistory.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorModal {
        @FXML
        private Label errorMessageLabel;

        public void setErrorMessage(String message) {
            errorMessageLabel.setText(message);
        }
    }

