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
import java.io.IOException;

public class AppWindow {
    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane Scrollpane;
    public boolean trace = false;
    public StyledDocument document;
    public void makeWindow(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }catch(Exception ex) {
            System.out.println("Something went wrong");
        }
        frame =new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        console = new JTextPane();
        console.setEditable(false);
        console.setOpaque(false);

        document = console.getStyledDocument();

        input = new JTextField();
        input.setEditable(true);
        input.setCaretColor(Color.BLACK);
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = input.getText();
                if (text.length()>1){
                    print(text+"\n",false);
                    doCommand(text);
                    scrollBottom();
                    input.selectAll();
                }
            }
        });
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { }

            @Override
            public void keyPressed(KeyEvent keyEvent) { }

            @Override
            public void keyReleased(KeyEvent keyEvent) { }
        });

        Scrollpane = new JScrollPane(console);
        Scrollpane.setOpaque(false);
        Scrollpane.getViewport().setOpaque(false);
        frame.add(input, BorderLayout.SOUTH);
        frame.add(Scrollpane, BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(75,75,75));

        frame.setSize(660,350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
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
        Style style= console.addStyle("Style",null);
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
            System.out.println(ex);


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
            else{
                print("Invalad Command\n"+sting,trace,new Color(255,155,155));
            }

    }
    public void startgame(){
        CheckFile fill= new CheckFile();
        Controller con = new Controller();
        System.out.println("Searching for game files.....");
        println("Searching for game files.....",false);
        try{
        fill.Run();
        new ChessBoard();
        ChessBoard chess= new ChessBoard();
        chess.toString();
        //con.playerTurn();

        }catch(IOException ex){
            System.out.println( ex );
        }

    }
}
