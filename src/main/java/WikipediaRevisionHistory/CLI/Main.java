package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.*;

import java.util.List;
import java.net.SocketTimeoutException;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        View.requestTitle();
        try {
            String title = controller.getTitle();
            WikipediaConnector wikiConnector = new WikipediaConnector(title);
            String json = wikiConnector.getData();
            WikipediaParser parser = new WikipediaParser(json);
            String redirectDestination = parser.getLastRedirectDestination();
            if (redirectDestination != null) {
                View.showRedirectMessage(redirectDestination);
            }
            List<Revision> revisions = parser.getRevisions();
            View.showRevision(revisions);
        } catch (NoInputException exception) {
            View.showNoInputWarning();
        } catch (SocketTimeoutException exception) {
            View.showNoConnectionWarning();
        } catch (NoArticleException exception) {
            View.showNoArticleWarning();
        }
    }

}
