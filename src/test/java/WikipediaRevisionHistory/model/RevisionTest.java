package WikipediaRevisionHistory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class RevisionTest {
    @Test
    public void toStringTest() {
        Map<String, String> map = new HashMap<>();
        map.put("user", "Ken Gallager");
        map.put("timestamp", "2022-05-25T17:55:11Z");
        Revision revision = new Revision(map);
        assertEquals("2022-05-25T17:55:11Z Ken Gallager", revision.toString());
    }
}
