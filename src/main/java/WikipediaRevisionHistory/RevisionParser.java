package WikipediaRevisionHistory;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public List<Revision> parse(InputStream dataStream) throws IOException {
        List<Revision> list = new ArrayList<>();
        List<List<Map<String,String>>> pages = JsonPath.read(dataStream, "$.query.pages.*.revisions");
        pages.forEach(revisions -> {
            revisions.forEach(revision -> {
                list.add(new Revision(revision));
            });
        });
        return list;
    }
}
