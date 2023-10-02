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

        List<Boolean> isPageMissingList = context.read("$..pages[*].missing");
        if (!isPageMissingList.isEmpty() && isPageMissingList.get(0)) throw new NoArticleException();
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

    // TODO: Change to getLastRedirect
    public List<Redirect> getRedirects() {
        List<List<Map<String, String>>> redirectsList = context.read("$..redirects");
        if (redirectsList.isEmpty()) return null;
        List<Redirect> list = new ArrayList<>();
        redirectsList.forEach(redirects ->
                redirects.forEach(redirect ->
                        list.add(new Redirect(redirect))
                )
        );
        return list;
    }
}
