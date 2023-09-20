package WikipediaRevisionHistory;

import java.util.Map;

public class Revision {
    public String timestamp, user;

    public Revision(Map<String, String> revision) {
        this.timestamp = revision.get("timestamp");
        this.user = revision.get("user");
    }
}
