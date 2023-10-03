package WikipediaRevisionHistory.model;

public class NoInputException extends Exception {
    public NoInputException() {
        super("I need an input stupid, provide Wiki article name");
    }
}