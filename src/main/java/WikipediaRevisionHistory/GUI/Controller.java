package WikipediaRevisionHistory.GUI;

import WikipediaRevisionHistory.model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.text.html.ListView;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.List;

public class Controller {
@FXML
    private TextField userInput;
@FXML
    private ListView revisionList;
@FXML
    private TextFlow resultTextFlow;
@FXML
    public void search(ActionEvent actionEvent) throws SocketTimeoutException {
    resultTextFlow.getChildren().clear();
    try {
        String title = userInput.getText();
        if (title.isEmpty()) {

        }
        WikipediaConnector wikiConnector = new WikipediaConnector(title);
        InputStream data = wikiConnector.getData();
        WikipediaParser parser = new WikipediaParser(data);

        List<Revision> revisions = parser.getRevisions();

        for (Revision revision : revisions) {
            Text revisionText = new Text(revision.toString() + "\n");
            resultTextFlow.getChildren().add(revisionText);
        }
        List<Redirect> redirects = parser.getRedirects();
        if (redirects != null && !redirects.isEmpty()) {
            String RedirectGUI = View.showRedirectGUI(redirects.get(redirects.size() - 1));
            Text TextRedirectGUI = new Text(RedirectGUI);

            resultTextFlow.getChildren().add(TextRedirectGUI);
        }


    } catch (NoArticleException exception){
        Text NoArticleExceptionText = new Text(View.showNoArticleWarningGUI());

        resultTextFlow.getChildren().add(NoArticleExceptionText);

    }
}
}