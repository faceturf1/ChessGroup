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
                firstPlayer = false;
                input.next();
            } else {
                System.out.println("Player 2's Turn:");
                /*can say firstplayer is white*/
                firstPlayer = true;
                input.next();
            }
        }
    }

    public boolean getCurrentPlayerTurn() {
        return firstPlayer;
    }

   /* private void inputChecker() {
        String invalid = input.next();
        if(invalid.equals(" ") || invalid.equals(null)) {
            throw new IllegalArgumentException("Input cannot be empty or invalid, please try again.");
        }
    }*/
}
