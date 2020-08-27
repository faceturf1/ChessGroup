package prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Controller {
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


    boolean firstPlayer = true; //make firstPlayer white here
    Scanner input = new Scanner(System.in);

    public void playerTurn() {
        while (true) {
            if (firstPlayer /*== white type */) {
                System.out.println("Player 1's Turn:");
                new AppWindow().println("Player 1's Turn:",false);
                /*Make first player to black*/
                firstPlayer = false;

            } else {
                System.out.println("Player 2's Turn:");
                /*can say firstPlayer is white*/
                firstPlayer = true;

            }
        }
    }

    public boolean getCurrentPlayerTurn() {
        return firstPlayer;
    }



    public int getUserInput(int min, int max) throws IOException {
        while(true) {
            String rawInput = in.readLine();
            try {
                int input = Integer.parseInt(rawInput);
                if (input < min || input > max) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException ex) {
                System.err.println(String.format("You must enter an integer between %d and %d", min, max));
            }
        }

    }
    public static void forfeitGame(boolean playerDecider){
        if(playerDecider == true){
            System.out.println("Player 1 has forfeited!!, Player 2 wins");
        }
        else{
            System.out.println("Player 2 has forfeited!!, Player 1 wins");
        }
    }


}
