package WikipediaRevisionHistory.GUI;

import WikipediaRevisionHistory.model.Redirect;

public class View {
    static String showRedirectGUI(Redirect redirect){
        return "Redirected to: " + redirect.to + ".";

    }
    static String showNoArticleWarningGUI(){
        return("That doesn't exists... anywhere on the internet.. dummy");
    }
}
