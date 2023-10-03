package WikipediaRevisionHistory.model;

public class NoArticleException extends Exception {
    NoArticleException() {
        super("That doesn't exists... anywhere on the internet.. dummy");
    }
}
