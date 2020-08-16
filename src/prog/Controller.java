package prog;

import java.util.Scanner;

public class Controller {
    boolean firstPlayer = true; //make firstplayer white here
    Scanner input = new Scanner(System.in);

    private void playerTurn() {
        while (true) {
            if (firstPlayer /*== white type */) {
                System.out.println("Player 1's Turn:");
                /*Make first player to black*/
                input.nextLine();
            } else {
                System.out.println("Player 2's Turn:");
                /*can say firstplayer is white*/
                input.nextLine();
            }
        }
    }

    public boolean getCurrentPlayerTurn() {
        return firstPlayer;
    }
}
