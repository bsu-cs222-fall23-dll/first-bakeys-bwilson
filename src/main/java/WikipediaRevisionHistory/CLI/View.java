package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.Revision;

import java.util.List;

class View {
    void requestTitle() {
        System.out.println("enter a Wikipedia title: ");

    }

    void showRedirectMessage(String redirectDestination) {
        System.out.println("Redirected to: " + redirectDestination + ".");
    }

    void showRevision(List<Revision> revisions) {
        revisions.forEach(revision -> System.out.println(revision.toString()));
    }

    void showException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}