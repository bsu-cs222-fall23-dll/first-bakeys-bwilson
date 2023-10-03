package WikipediaRevisionHistory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;


import javax.swing.text.html.ListView;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Scanner;

public class Controller {
@FXML
    private TextField userInput;
@FXML
    private ListView revisionList;
@FXML
    private TextFlow resultTextFlow;
@FXML
    public void search(ActionEvent actionEvent) throws SocketTimeoutException, NoArticleException {
    resultTextFlow.getChildren().clear();
    String title = userInput.getText();
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

}

    public String getTitle () throws NoInputException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isBlank())
            throw new NoInputException();
        scanner.close();
        return input;
    }

}