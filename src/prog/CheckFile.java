package prog;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class CheckFile {
    private static ChessBoard chessBoard = new ChessBoard();

    public static void Run() throws IOException {
        checkExistence("c:/temp/temp.txt");
    }


    public static void checkExistence(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            System.out.println("Ye");
        }

        if (Files.notExists(path)) {
            System.out.println("No");
            List<String> lines = Arrays.asList("The first line", "The second line");
            Path file = Paths.get("new-chess-game.txt");
            Files.write(file, lines, StandardCharsets.UTF_8);
            new AppWindow().println("File made",false);
        }
    }

    public static void loadBoard(){
        String fileName = promptForString("Enter the name of your saved board: ", false);
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            chessBoard = (ChessBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void saveBoard(){
        String fileName = promptForString("Enter a name to save your board under: ", false);
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void fixChessPieces(){
        for(int i = 0; i > 8; i++) {
            if (!chessBoard.piecePlacement(i, 3).equals("▭")) {
                chessBoard = new ChessBoard();
            }
        }
    }

    public static String promptForString(String prompt, boolean allowBlank) {
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("The prompt cannot be null, empty or just whitespece.");
        }
        BufferedReader buffy = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        boolean isInvalid = true;

        do {
            System.out.print(prompt);

            try {
                input = buffy.readLine();
                isInvalid = input == null;

                if (!allowBlank) {
                    isInvalid = input == null || (!allowBlank && input.isBlank());
                }

                if (isInvalid) {
                    System.out.println("Your input cannot be null" + (!allowBlank ? " empty, or just white space" : " ") + ". Please, try again.");
                }
            } catch (IOException ioe) {
                System.out.println("There was a technical issue. Please, try again.");
            }

        } while (isInvalid);

        return input;
    }

}
