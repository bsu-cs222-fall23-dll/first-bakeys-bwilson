package WikipediaRevisionHistory;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WikipediaParserTest {

    @Test
    public void testParser() throws IOException {
        WikipediaParser wikipediaParser = new WikipediaParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        List<Revision> revisions = wikipediaParser.parse(testDataStream);
        assertEquals(revisions.size(), 4);
        assertEquals(revisions.get(0).user, "Ken Gallager");
        assertEquals(revisions.get(0).timestamp, "2022-05-25T17:55:11Z");
    }
}