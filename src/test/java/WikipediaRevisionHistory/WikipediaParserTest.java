package WikipediaRevisionHistory;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WikipediaParserTest {
    InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");

    @Test
    public void testInitialParse() {
        assertDoesNotThrow(() -> new WikipediaParser(testDataStream));
    }

    @Test
    public void testGetRevisions() {
        WikipediaParser wikipediaParser = new WikipediaParser(testDataStream);
        List<Revision> revisions = wikipediaParser.getRevisions();
        assertEquals(revisions.size(), 4);
        assertEquals(revisions.get(0).user, "Ken Gallager");
        assertEquals(revisions.get(0).timestamp, "2022-05-25T17:55:11Z");
    }
}