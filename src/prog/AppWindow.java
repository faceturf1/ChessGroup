package prog;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppWindow {
    public final JFrame frame= new JFrame();
    public static final JTextPane console= new JTextPane();
    public  JTextField input = new JTextField();
    public  JScrollPane ScrollPane = new JScrollPane(console);
    public boolean trace = false;

    public  static StyledDocument document = console.getStyledDocument();
    public String Input;
    private  ChessBoard chessBoard = new ChessBoard();
    private Controller controller = new Controller();
    private String invalidMove = "This move is invalid please try again.";



    public String getInput() {
        return Input;
    }

    public void setInput(String input) {
        Input = input;
    }

    public void makeWindow(){

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception ex) {
                System.out.println("Something went wrong");
            }
           // if(mainMen){

             //   mainMen = false;

            //else{
                frame.setTitle("Chess");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                this.console.setEditable(false);
                this.console.setOpaque(false);

                input.setEditable(true);
                input.setCaretColor(Color.BLACK);
                input.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        setInput(input.getText());
                        if (getInput().length() > 1) {
                            print(getInput() + "\n", false);
                            doCommand(getInput());
                            scrollBottom();
                            input.selectAll();

                        }
                    }
                });
                input.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent keyEvent) {
                    }

                    @Override
                    public void keyPressed(KeyEvent keyEvent) {
                    }

                    @Override
                    public void keyReleased(KeyEvent keyEvent) {
                    }
                });

                ScrollPane.setOpaque(false);
                ScrollPane.getViewport().setOpaque(false);
                frame.add(input, BorderLayout.SOUTH);
                frame.add(ScrollPane, BorderLayout.CENTER);
                frame.getContentPane().setBackground(new Color(75, 75, 75));

                frame.setSize(660, 350);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
        println("Welcome to our game of chess! Type: \nStart! \nforfeit  \nclear \n:to begin. \nAt any time during your game type Save to save your game.", false);

    }

    public void scrollTop(){
        console.setCaretPosition(0);
    }
    public void scrollBottom(){
        console.setCaretPosition(console.getDocument().getLength());
    }
    public void print(String s, boolean trace ){
        print(s,trace,new Color(255,255,255));

    }
    public static void print(String s, boolean trace, Color cooler){
        Style style= console.addStyle("style",null);
        StyleConstants.setForeground(style, cooler);
        if(trace){
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();
            s = caller +"->"+s;
        }
        try{
            document.insertString(document.getLength(),s,style);
        }
        catch(Exception ex){
            System.out.println(ex.getCause().toString());

        }
    }
    public static void println(String s, boolean trace){
        println(s,trace, new Color(255,255,255));
    }
    public static void println(String s, boolean trace, Color c){
        print(s+"\n",trace,c);
    }
    public void clear(){
        try{
            document.remove(0, document.getLength());
        }catch(Exception ex){
            System.out.println(ex+"\n");
        }
    }
    public void doCommand(String sting){
        final String[] commands = sting.split(" ");
        Pattern p = Pattern.compile("(\\w)(\\w)(\\d)"+"to"+"(\\w)(\\d)");
        Matcher m = p.matcher(commands[0]);
        /*
        Place commands made

         */
        /*  if(commands[0].equalsIgnoreCase(place name of command here)){
                clear();
            }*/

            if(commands[0].equalsIgnoreCase("clear")) {
            }
            //:"+"
            else if(commands[0].equalsIgnoreCase("Move")){
               println("Type in the piece you want to move \n(the letter for the piece and if it is knight =k or King= K)\nwith it's coordinates (both as numbers example p01),the word to, and the coordinates of the square you want to move it to(example p06to05)\n Top of board is Black and Bottom is White",false);
            }
            else if (m.find()){

                Character[] string1=new Character[8];
                string1[0]=commands[0].charAt(0);
                string1[1] = commands[0].charAt(1);
                string1[2]= commands[0].charAt(2);
                string1[3]= commands[0].charAt(5);
                string1[4]= commands[0].charAt(6);

                if(controller.firstPlayer){
                    println("Player 1's Turn:",false);
                    makingAMove(Integer.parseInt(String.valueOf(string1[1])),Integer.parseInt(String.valueOf(string1[2])),string1[0],Integer.parseInt(String.valueOf(string1[3])),Integer.parseInt(String.valueOf(string1[4])),controller.firstPlayer);
                    controller.firstPlayer=false;
                    println(chessBoard.toString()+"\n",false);
                }
                else{
                    println("PLayer 2's Turn",false);
                    makingAMove(Integer.parseInt(String.valueOf(string1[1])),Integer.parseInt(String.valueOf(string1[2])),string1[0],Integer.parseInt(String.valueOf(string1[3])),Integer.parseInt(String.valueOf(string1[4])),controller.firstPlayer);
                    controller.firstPlayer=true;
                    println(chessBoard.toString()+"\n",false);
                }
        }
            else if(commands[0].equalsIgnoreCase("Forfeit")){
                forfeit();
            }
            else if(commands[0].equalsIgnoreCase("Save")){
                saveBoard();
            }
            else if(commands[0].equalsIgnoreCase("Start!")){
                startGame();
                //controller.playerTurn();
            }
            else if(commands[0].equalsIgnoreCase("new-chess-game2")){
                try{
                    FileInputStream fileInputStream = new FileInputStream(getInput() + ".txt");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    chessBoard = (ChessBoard) objectInputStream.readObject();
                    println(chessBoard.toString(),false);
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (ClassNotFoundException cnfe){
                    cnfe.printStackTrace();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            else{
                print("Invalid Command\n"+sting,trace,new Color(255,155,155));
            }
    }

    public void startGame(){
        //CheckFile fill= new CheckFile();
        //Controller con = new Controller();
       // System.out.println("Searching for game files.....");
       // println("Searching for game files.....",false);
        //try{
        //Run();
        //loadBoard();

        //ChessBoard chess= new ChessBoard();
        //chess.toString();


        //}catch(IOException ex){
        //    System.out.println( ex );
        //}
        ChessBoard board = new ChessBoard();
        println(board.toString(),false);
        println("Now type Move for info on moving a piece",false);
    }

    public void makingAMove(int currentColumn, int currentRow, char piece ,int newColumn, int newRow, boolean isWhite){
        DetectCheckmate detectCheckmate = new DetectCheckmate();

        switch (piece) {
            case 'q':
                queenMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
                break;
            case 'K':
                kingMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
                break;
            case 'b':
                bishopMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
                break;
            case 'r':
                rookMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
                break;
            case 'k':
                knightMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
                break;
            case 'p':
                pawnMovement(currentColumn, currentRow, newRow, newColumn, isWhite);
        }
       detectCheckmate.detectCheckmate(chessBoard);
        if (detectCheckmate.isCheckMate()){
            println(detectCheckmate.getCheckMateMessage(), false);
        }
    }

    public void queenMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow) ||
                    (newRow == currentRow + 1 && newColumn == currentColumn) ||
                    (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Queen);
            }
        }else if(!controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow) ||
                    (newRow == currentRow + 1 && newColumn == currentColumn) ||
                    (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Queen);
            }
        }else {
            println(invalidMove, false);
        }
    }

    public void kingMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow) ||
                    (newRow == currentRow + 1 && newColumn == currentColumn) ||
                    (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.King);
            }
        }else if(!controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow) ||
                    (newRow == currentRow + 1 && newColumn == currentColumn) ||
                    (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.King);
            }
        }else {
            println(invalidMove, false);
        }
    }

    public void bishopMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Bishop);
            }

        }else if(!controller.firstPlayer){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                    (newColumn == currentColumn - 1 && newRow == currentRow + 1)
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Bishop);
            }

        }else {
            println(invalidMove, false);
        }
    }

    public void knightMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (
                    (newRow == currentRow + 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                    (newRow == currentRow - 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                    (newColumn == currentColumn + 2 && (newRow == currentRow - 1 || newRow == currentRow + 1)) ||
                    (newColumn == currentColumn - 2 && (newRow == currentRow - 1 || newRow == currentRow + 1))
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Nknight);
            }

        }else if(!controller.firstPlayer){
            if (
                    (newRow == currentRow + 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                    (newRow == currentRow - 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                    (newColumn == currentColumn + 2 && (newRow == currentRow - 1 || newRow == currentRow + 1)) ||
                    (newColumn == currentColumn - 2 && (newRow == currentRow - 1 || newRow == currentRow + 1))
            ) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Nknight);
            }

        }else {
            println(invalidMove, false);
        }
    }

    public void rookMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (newRow == currentRow) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Rook);
            } else if (newColumn == currentColumn) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Rook);
            }
        }else if(!controller.firstPlayer){
            if (newRow == currentRow) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Rook);
            } else if (newColumn == currentColumn) {
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Rook);
            }
        }else {
            println(invalidMove, false);
        }
    }

    public void pawnMovement(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(controller.firstPlayer){
            if (currentRow == 7){
                if(newRow == currentRow - 1 || newRow == currentRow - 2){
                    chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
                }
            }else if(newRow == currentRow - 1 && newColumn == currentColumn){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }
        }else if(!controller.firstPlayer){
            if(currentRow == 2 && newColumn == currentColumn){
                if (newRow == currentRow + 1 || newRow == currentRow + 2){
                    chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
                }
            }else if(newColumn == currentColumn && newRow == currentRow + 1){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }
        }else {
            pawnCapture(currentColumn, currentRow, newRow, newColumn, isWhite);
        }
    }

    public void pawnCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite){
        if(controller.firstPlayer && newRow == currentRow - 1){
            if(newColumn == currentColumn - 1){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }else if(newColumn == currentColumn + 1){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }
        }else if(!controller.firstPlayer && newRow == currentRow + 1){
            if(newColumn == currentColumn - 1){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }else if(newColumn == currentColumn + 1){
                chessBoard.updateBoard(newRow,newColumn,currentRow,currentColumn,isWhite,Type.Pawn);
            }
        }else{
            println(invalidMove, false);
        }

    }


    public  void Run() throws IOException {
        checkExistence("C:/temp/new-chess-game.txt");
    }


    public  void checkExistence(String filePath) throws IOException {
        Path path = Paths.get(filePath);


        if (Files.exists(path)) {
            println("A file has been found",false);
            loadBoard();

        }

        else {
            System.out.println("No file");
            List<String> lines = Arrays.asList("The first line", "The second line");
            println("Enter the name of your board: ", false);
            setInput(input.getText());

            boolean isAnswered = false;
            Path file;


                file = Path.of(getInput()+".txt");
                if (file.getFileName().equals("")){
                    isAnswered=true;
                }


            Files.write(file, lines, StandardCharsets.UTF_8);

            println("File made",false);
        }
    }

    public  void loadBoard(){
        println("Enter the name of your saved board:",false);
        setInput(input.getText());


        /*try{
            FileInputStream fileInputStream = new FileInputStream(getInput() + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            chessBoard = (ChessBoard) objectInputStream.readObject();
            println(chessBoard.toString(),false);
            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }*/
    }
    public  void saveBoard(){
        String fileName = "new-chess-game2 ";

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(chessBoard);


            objectOutputStream.close();
            fileOutputStream.close();




        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        println("File was save as"+fileName,false);
    }

    public  void forfeit(){

        if(controller.firstPlayer == true){
            controller.forfeitGame(true);
            println("Player 1 has forfeited!!, Player 2 wins", false);
        }
        else {
            controller.forfeitGame(false);
            println("Player 2 has forfeited!!, Player 1 wins", false);
        }
    }
}
