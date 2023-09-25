package WikipediaRevisionHistory;

import WikipediaRevisionHistory.Redirect;

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
}
