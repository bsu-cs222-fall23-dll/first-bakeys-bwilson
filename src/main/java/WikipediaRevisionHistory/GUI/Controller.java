package WikipediaRevisionHistory.GUI;

import WikipediaRevisionHistory.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class Controller {
    @FXML
    private TextField userInput;

    @FXML
    private VBox responseBox;

    @FXML
    private GridPane revisionGrid;

    @FXML
    public void search() {
        responseBox.getChildren().clear();
        revisionGrid.getChildren().clear();
        try {
            String title = userInput.getText();
            if (title.isEmpty()) {
                throw new NoInputException();
            }
            WikipediaConnector wikiConnector = new WikipediaConnector(title);
            String data = wikiConnector.getData();
            WikipediaParser parser = new WikipediaParser(data);

            String lastRedirectDestination = parser.getLastRedirectDestination();
            if (lastRedirectDestination != null) {
                showMessage("Redirected to: " + lastRedirectDestination);
            }

            List<Revision> revisions = parser.getRevisions();
            populateRevisionList(revisions);

        } catch (NoInputException | NoArticleException | NoConnectionException exception) {
            ModalController modalController = new ModalController();
            modalController.showErrorModal(exception.getMessage());
        }
        responseBox.getScene().getWindow().sizeToScene();
    }

    private void populateRevisionList(List<Revision> revisions) {
        int i = 0;
        for (Revision revision : revisions) {
            revisionGrid.addRow(i++, new Label(revision.user), new Label(revision.timestamp));
        }
        responseBox.getChildren().add(revisionGrid);
    }

    private void showMessage(String message) {
        responseBox.getChildren().add(new Label(message));
    }

}