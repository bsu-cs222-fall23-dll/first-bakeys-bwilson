package WikipediaRevisionHistory.model;

import java.util.Map;

public class Revision {
    public final String timestamp, user;

    public Revision(Map<String, String> revision) {
        this.timestamp = revision.get("timestamp");
        this.user = revision.get("user");
    }

    @Override
    public String toString() {
        return timestamp + " " + user;


    }
}
