package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.Redirect;
import WikipediaRevisionHistory.model.Revision;

import java.util.List;

public class View {
    static void requestTitle(){
        System.out.println("enter a Wikipedia title: ");

    }
    static void showNoInputWarning(){
        System.out.println("I need an input stupid, provide Wiki article name");
    }

    static void showRedirectMessage(Redirect redirect){
        System.out.println("Redirected to: " + redirect.to + "." );
    }
    static void showRevision(List<Revision> revisions){
        revisions.forEach(revision -> {
            System.out.println(revision.toString());
        });
    }

    public static void showNoConnectionWarning() {
        System.out.println("Oh god, why’s it so quiet! I crave connection (to the internet).");
    }

    public static void showNoArticleWarning() {
        System.out.println("That doesn't exists... anywhere on the internet.. dummy");
    }
}