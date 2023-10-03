package WikipediaRevisionHistory.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikipediaConnector {
    private final String title;

    public WikipediaConnector(String title) {
        this.title = title;
    }

    public String getData() throws SocketTimeoutException {
        String urlString = "https://en.wikipedia.org/w/api.php?"
                + "action=query"
                + "&format=json"
                + "&prop=revisions"
                + "&rvprop=timestamp|user"
                + "&rvlimit=13"
                + "&redirects"
                + "&titles=" + URLEncoder.encode(this.title, Charset.defaultCharset());
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (bakeys@bsu.edu)");
            connection.connect();
            InputStream dataStream = connection.getInputStream();
            return new String(dataStream.readAllBytes(), Charset.defaultCharset());
        } catch (SocketTimeoutException noConnectionException) {
            throw noConnectionException;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}