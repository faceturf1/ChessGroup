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

public class AppWindow {
    public final JFrame frame= new JFrame();
    public final JTextPane console= new JTextPane();
    public  JTextField input = new JTextField();
    public  JScrollPane Scrollpane = new JScrollPane(console);
    public boolean trace = false;
    public boolean mainmen = true;
    public  StyledDocument document = console.getStyledDocument();
    public String Input;
    private static ChessBoard chessBoard = new ChessBoard();

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
           // if(mainmen){

             //   mainmen = false;

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


                Scrollpane.setOpaque(false);
                Scrollpane.getViewport().setOpaque(false);
                frame.add(input, BorderLayout.SOUTH);
                frame.add(Scrollpane, BorderLayout.CENTER);
                frame.getContentPane().setBackground(new Color(75, 75, 75));

                frame.setSize(660, 350);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
        println("Type Start! to begin.", false);



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
    public void print(String s, boolean trace, Color cooler){
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
    public void println(String s,boolean trace){
        println(s,trace, new Color(255,255,255));
    }
    public void println(String s, boolean trace, Color c){
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
        /*
        Place commands made

         */


            if(commands[0].equalsIgnoreCase("clear")){
                clear();
            }
            else if(commands[0].equalsIgnoreCase("Start!")){
                startgame();
            }
            else if(commands[0].equalsIgnoreCase(" Move:"+ "(\\w)(\\d)")){

            }
            else{
                print("Invalad Command\n"+sting,trace,new Color(255,155,155));
            }

    }
    public void startgame(){
        //CheckFile fill= new CheckFile();
        //Controller con = new Controller();
        System.out.println("Searching for game files.....");
        println("Searching for game files.....",false);
        try{
        Run();
        loadBoard();

        ChessBoard chess= new ChessBoard();
        chess.toString();


        }catch(IOException ex){
            System.out.println( ex );
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
            boolean isAnswered = false;
            Path file;
            do {

                file = Path.of(new AppWindow().getInput()+".txt");
                if (file.getFileName().equals("")){
                    isAnswered=true;
                }
            }while (isAnswered);

            Files.write(file, lines, StandardCharsets.UTF_8);

            println("File made",false);
        }
    }

    public  void loadBoard(){
        String fileName = ("Enter the name of your saved board: ");
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName + ".txt");
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
    public static void saveBoard(){
        String fileName = "Enter a name to save your board under: ";

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
