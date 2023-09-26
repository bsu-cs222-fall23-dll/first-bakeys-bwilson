package WikipediaRevisionHistory;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WikipediaParser {
    private final List<Map<String,String>> revisions = new ArrayList<>();
    private final List<Map<String, String>> redirects;

    public WikipediaParser(InputStream dataStream) throws NoArticleException {
        try {
            Map<String, Object> query = JsonPath.read(dataStream, "$.query");
            List<Map<String, Object>> pages = (List<Map<String, Object>>) query.get("pages");

            Map<String, Object> page1 = pages.get(0);

            if (page1.containsKey("missing") && (boolean) page1.get("missing")){
              throw new NoArticleException();
            }

            this.revisions.addAll((List<Map<String, String>>) page1.get("revisions"));
            this.redirects = (List<Map<String, String>>) query.get("redirects");


        } catch (IOException error) {
            throw new RuntimeException(error.getLocalizedMessage());
        }
    }

    public List<Revision> getRevisions() {
        List<Revision> list = new ArrayList<>();
        this.revisions.forEach(revision ->
            list.add(new Revision(revision))
        );
        return list;
    }

    // TODO: Make method pass tests
    // TODO: Change to getLastRedirect
    public List<Redirect> getRedirects() {
        List<Redirect> list = new ArrayList<>();
        this.redirects.forEach(redirect -> {
            list.add(new Redirect(redirect));
        });
        return list;
    }
}
