package prog;

import lib.ConsoleIO;

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
        }
    }

    public void loadBoard(){
        String fileName = ConsoleIO.promptForString("Enter the name of your saved board: ");
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
    public void saveBoard(){
        String fileName = ConsoleIO.promptForString("Enter a name to save your board under: ");
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
}
