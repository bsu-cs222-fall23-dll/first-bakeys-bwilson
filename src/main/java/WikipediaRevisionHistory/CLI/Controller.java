package WikipediaRevisionHistory.CLI;

import WikipediaRevisionHistory.model.NoInputException;

import java.util.Scanner;

public class Controller {


    public String getTitle() throws NoInputException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isBlank())
            throw new NoInputException();
        scanner.close();
        return input;
    }
}