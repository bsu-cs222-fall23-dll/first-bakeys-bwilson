package WikipediaRevisionHistory;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WikipediaParser {
    private final List<List<Map<String,String>>> pages;

    public WikipediaParser(InputStream dataStream) {
        try {
            this.pages = JsonPath.read(dataStream, "$.query.pages.*.revisions");
        } catch (IOException error) {
            throw new RuntimeException(error.getLocalizedMessage());
        }
    }

    public List<Revision> getRevisions() {
        List<Revision> list = new ArrayList<>();
        this.pages.forEach(revisions ->
            revisions.forEach(revision ->
                list.add(new Revision(revision))
            )
        );
        return list;
    }

    public List<Redirect> getRedirects() {
        return null;
    }
}
