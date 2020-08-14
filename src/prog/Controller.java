package prog;

import java.util.Scanner;

public class Controller {
    boolean firstPlayer = true;
    Scanner input = new Scanner(System.in);

    public void playerTurn() {
        while (true) {
            if (firstPlayer) {
                System.out.println("Player 1's Turn:");
                input.nextLine();
            } else {
                System.out.println("Player 2's Turn:");
                input.nextLine();
            }
        }
    }
}
