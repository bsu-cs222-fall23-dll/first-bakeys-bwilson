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
            List<Redirect> redirects = parser.getRedirects();
            if (redirects != null && redirects.size() > 0) {
                View.showRedirectMessage(redirects.get(redirects.size() - 1));
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
