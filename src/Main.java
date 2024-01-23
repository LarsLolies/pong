import javax.swing.*;

public class Main {
    static int boardWidth= 1200;
    static int boardHight =600;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        setFrame(frame);
        PongGame pongGame =new PongGame(boardWidth, boardHight);
        frame.add(pongGame);
        frame.pack();
        pongGame.requestFocus();


    }
    static public void setFrame(JFrame frame){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}