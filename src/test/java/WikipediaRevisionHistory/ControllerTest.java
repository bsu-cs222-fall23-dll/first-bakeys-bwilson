package WikipediaRevisionHistory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {

    @Test
    public void testGetArticleTitle() {
        Controller controller = new Controller();
        String title = "Arkansas Highway 327";
        System.out.println(title);
        assertEquals(title, controller.getTitle());
    }

    @Test
    public void testNoNoArticleTitleGiven() {
        Controller controller = new Controller();
        System.out.println();
        assertThrows(NoInputException.class, controller::getTitle);
    }
}
