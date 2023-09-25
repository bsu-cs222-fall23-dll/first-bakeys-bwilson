package WikipediaRevisionHistory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnector {
    private final String title;

    public WikipediaConnector(String title) {
        this.title = title;
    }

    public InputStream getData() throws IOException {
        String urlString = "https://en.wikipedia.org/w/api.php?"
                + "action=query"
                + "&format=json"
                + "&prop=revisions"
                + "&rvprop=timestamp|user"
                + "&rvlimit=4"
                + "&redirects"
                + "&titles=" + URLEncoder.encode(this.title, Charset.defaultCharset());
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (bakeys@bsu.edu)");
            connection.connect();
            return connection.getInputStream();
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }
}