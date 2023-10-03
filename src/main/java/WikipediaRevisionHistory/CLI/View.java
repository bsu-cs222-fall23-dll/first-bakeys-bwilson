package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.Revision;

import java.util.List;

class View {
    void requestTitle(){
        System.out.println("enter a Wikipedia title: ");

    }
    void showNoInputWarning(){
        System.out.println("I need an input stupid, provide Wiki article name");
    }

    void showRedirectMessage(String redirectDestination){
        System.out.println("Redirected to: " + redirectDestination + "." );
    }
    void showRevision(List<Revision> revisions){
        revisions.forEach(revision -> System.out.println(revision.toString()));
    }

    void showNoConnectionWarning() {
        System.out.println("Oh god, why’s it so quiet! I crave connection (to the internet).");
    }

    void showNoArticleWarning() {
        System.out.println("That doesn't exists... anywhere on the internet.. dummy");
    }
}
