package WikipediaRevisionHistory;

import WikipediaRevisionHistory.model.NoInputException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {

    @Test
    public void testGetArticleTitle() throws NoInputException {
        Controller controller = new Controller();
        String title = "Arkansas Highway 327";
        setInput(title);
        assertEquals(title, controller.getTitle());

    }

    @Test
    public void testNoNoArticleTitleGiven() {
        Controller controller = new Controller();
        setInput("");
        assertThrows(NoInputException.class, controller::getTitle);
    }

    private void setInput(String text) {
        text += "\n";
        ByteArrayInputStream input = new ByteArrayInputStream(text.getBytes());
        System.setIn(input);
    }
}
