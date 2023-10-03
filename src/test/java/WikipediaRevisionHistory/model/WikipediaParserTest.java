package WikipediaRevisionHistory.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WikipediaParserTest {

    private final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private String getJson(String fileName) {
        try (InputStream dataStream = classLoader.getResourceAsStream(fileName)) {
            assert dataStream != null;
            return new String(dataStream.readAllBytes(), Charset.defaultCharset());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private final String testDataStream = this.getJson("simple-test-data.json");
    private final String edgeCaseDataStream = this.getJson("edge-case-test-data.json");
    private final String noPageDataStream = this.getJson("no-page.json");

    @Test
    public void testInitialParse() {
        assertDoesNotThrow(() -> new WikipediaParser(testDataStream));
    }

    @Nested
    class getRevisions {
        @Test
        public void testParsing() throws NoArticleException {
            List<Revision> revisions = new WikipediaParser(testDataStream).getRevisions();
            assertEquals(revisions.size(), 13);
            assertEquals(revisions.get(0).user, "Ken Gallager");
            assertEquals(revisions.get(0).timestamp, "2022-05-25T17:55:11Z");
        }

        @Test
        public void testSorting() throws NoArticleException {
            List<Revision> revisions = new WikipediaParser(testDataStream).getRevisions();
            String lastTimestamp = revisions.get(0).timestamp;
            for (int i = 1; i < revisions.size(); i++) {
                String timestamp = revisions.get(i).timestamp;
                int diff = lastTimestamp.compareTo(timestamp);
                assertTrue(diff >= 0, "Revisions not sorted reverse-chronological order");
                lastTimestamp = timestamp;
            }
        }
    }

    @Nested
    class getLastRedirectDestination {
        @Test
        public void testNoRedirect() throws NoArticleException {
            String destination = new WikipediaParser(testDataStream).getLastRedirectDestination();
            assertNull(destination);
        }

        @Test
        public void testParsing() throws NoArticleException {
            String destination = new WikipediaParser(edgeCaseDataStream).getLastRedirectDestination();
            assertEquals(destination, "Wikipedia:Double redirects");
        }
    }

    @Test
    public void testNoArticleForTitle() {
        assertThrows(NoArticleException.class, () -> new WikipediaParser(noPageDataStream));
    }
}