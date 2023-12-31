package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.*;

import java.util.List;

class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View();
        view.requestTitle();
        try {
            String title = controller.getTitle();
            WikipediaConnector wikiConnector = new WikipediaConnector(title);
            String json = wikiConnector.getData();
            WikipediaParser parser = new WikipediaParser(json);
            String redirectDestination = parser.getLastRedirectDestination();
            if (redirectDestination != null) {
                view.showRedirectMessage(redirectDestination);
            }
            List<Revision> revisions = parser.getRevisions();
            view.showRevision(revisions);
        } catch (NoInputException | NoArticleException | NoConnectionException exception) {
            view.showException(exception);
        }
    }

}