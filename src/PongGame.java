import java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;
import javax.swing.*;
public class PongGame extends JPanel implements ActionListener {

    int boardWidth;
    int boardHeight;
    int tileSize = 12;

    Tile ball;
    int velocityX=0;
    int velocityY;


    Timer gameLoop;
    Random random;



    PongGame(int boardWidth, int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        this.random=new Random();


        this.ball= new Tile(50, 25);
        while(velocityX == 0) {
            this.velocityX = random.nextInt(-3, 3);
        }

        this.velocityY= random.nextInt(-3, 3);



        this.gameLoop = new Timer(100, this);
        this.gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
//        Ball
        g.setColor(Color.white);
        g.fill3DRect(ball.x*tileSize, ball.y*tileSize, tileSize, tileSize, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall();
        repaint();
    }


    private class Tile{
        int x;
        int y;
        Tile(int x, int y){
            this.x=x;
            this.y =y;

        }

    }

    public void moveBall(){
        ball.x += velocityX;
        ball.y += velocityY;
    }
}
