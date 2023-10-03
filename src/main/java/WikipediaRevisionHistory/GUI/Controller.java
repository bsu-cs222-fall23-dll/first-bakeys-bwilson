package WikipediaRevisionHistory.GUI;

import WikipediaRevisionHistory.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.List;

public class Controller {
    @FXML
    private TextField userInput;

    @FXML
    private TextFlow resultTextFlow;

    @FXML
    public void search(ActionEvent actionEvent) {
        resultTextFlow.getChildren().clear();
        try {
            String title = userInput.getText();
            if (title.isEmpty()) {
                throw new NoInputException();
            }
            WikipediaConnector wikiConnector = new WikipediaConnector(title);
            String data = wikiConnector.getData();
            WikipediaParser parser = new WikipediaParser(data);

            List<Revision> revisions = parser.getRevisions();

            for (Revision revision : revisions) {
                Text revisionText = new Text(revision.toString() + "\n");
                resultTextFlow.getChildren().add(revisionText);
            }
            String lastRedirectDestination = parser.getLastRedirectDestination();
            if (lastRedirectDestination != null) {
                Text TextRedirectGUI = new Text("Redirected to: " + lastRedirectDestination);
                resultTextFlow.getChildren().add(TextRedirectGUI);
            }

        } catch (NoInputException | NoArticleException | NoConnectionException exception) {
            Text NoArticleExceptionText = new Text(exception.getMessage());
            resultTextFlow.getChildren().add(NoArticleExceptionText);
        }
    }
}