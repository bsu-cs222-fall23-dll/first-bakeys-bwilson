package WikipediaRevisionHistory;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WikipediaParserTest {
    InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("simple-test-data.json");
    InputStream edgeCaseDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("edge-case-test-data.json");

    @Test
    public void testInitialParse() {
        assertDoesNotThrow(() -> new WikipediaParser(testDataStream));
    }

    @Nested class getRevisions {
        @Test
        public void testParsing() {
            List<Revision> revisions =  new WikipediaParser(testDataStream).getRevisions();
            assertEquals(revisions.size(), 13);
            assertEquals(revisions.get(0).user, "Ken Gallager");
            assertEquals(revisions.get(0).timestamp, "2022-05-25T17:55:11Z");
        }

        @Test
        public void testSorting() {
            List<Revision> revisions =  new WikipediaParser(testDataStream).getRevisions();
            String lastTimestamp = revisions.get(0).timestamp;
            for (int i = 1; i < revisions.size(); i++) {
                String timestamp = revisions.get(i).timestamp;
                int diff = lastTimestamp.compareTo(timestamp);
                assertTrue(diff >= 0, "Revisions not sorted reverse-chronological order");
                lastTimestamp = timestamp;
            }
        }
    }

    @Nested class getRedirects {
        @Test
        public void testNoRedirect() {
            List<Redirect> redirects =  new WikipediaParser(testDataStream).getRedirects();
            assertNull(redirects);
        }

        @Test
        public void testParsing() {
            List<Redirect> redirects =  new WikipediaParser(edgeCaseDataStream).getRedirects();
            assertEquals(redirects.size(), 1);
            assertEquals(redirects.get(0).from, "UK");
            assertEquals(redirects.get(0).to, "United Kingdom");
        }
    }
}