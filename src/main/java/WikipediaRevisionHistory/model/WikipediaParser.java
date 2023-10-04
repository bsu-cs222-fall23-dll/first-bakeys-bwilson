package WikipediaRevisionHistory.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WikipediaParser {
    private final DocumentContext context;

    public WikipediaParser(String jsonString) throws NoArticleException {
        Configuration config = Configuration.defaultConfiguration();
        config.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        this.context = JsonPath.using(config).parse(jsonString);

        if (isPageMissing()) throw new NoArticleException();
    }

    private boolean isPageMissing() {
        List<Object> isPageMissingList = context.read("$..pages[*].missing");
        if (isPageMissingList.isEmpty()) return false;
        Object isPageMissing = isPageMissingList.get(0);
        return isPageMissing instanceof String || (boolean) isPageMissing;
    }

    public List<Revision> getRevisions() {
        List<Revision> list = new ArrayList<>();
        List<List<Map<String, String>>> pageRevisions = context.read("$..revisions");
        pageRevisions.forEach(revisions ->
                revisions.forEach(revision ->
                        list.add(new Revision(revision))
                )
        );
        return list;
    }

    public String getLastRedirectDestination() {
        List<String> resultList = context.read("$..redirects[-1:].to");
        if (resultList == null || resultList.isEmpty()) return null;
        return resultList.get(0);
    }
}
