package WikipediaRevisionHistory.model;

import java.util.Map;

public class Redirect {
    public String from, to;

    public Redirect(Map<String,String> redirect) {
        this.from = redirect.get("from");
        this.to = redirect.get("to");
    }
}
