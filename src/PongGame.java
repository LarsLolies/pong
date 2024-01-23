import java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;
import javax.swing.*;
public class PongGame extends JPanel implements ActionListener, KeyListener {

    int boardWidth;
    int boardHeight;
    int tileSize = 12;

    Tile ball;
    int velocityX=0;
    int velocityY;

    Tile[] player1;
    int player1Speed=0;

    Tile[] player2;
    int player2Speed=0;






    Timer gameLoop;
    Random random;



    PongGame(int boardWidth, int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        this.random=new Random();
        addKeyListener(this);
        setFocusable(true);


        this.ball= new Tile(50, 25);
        this.player1= new Tile[5];
        for (int i=0;i<player1.length; i++){
            player1[i]=new Tile(0, 23+i);
        }
        this.player2= new Tile[5];
        for (int i=0;i<player2.length; i++){
            player2[i]=new Tile(99, 23+i);
        }



        while(velocityX == 0) {
            this.velocityX = random.nextInt(-3, 4);
        }

        this.velocityY= random.nextInt(-3, 4);



        this.gameLoop = new Timer(150, this);
        this.gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //Ball
        g.setColor(Color.white);
        g.fill3DRect(ball.x*tileSize, ball.y*tileSize, tileSize, tileSize, true);

        //player1
        for(int i =0; i<player1.length; i++){
            g.fill3DRect(player1[i].x*tileSize, player1[i].y*tileSize, tileSize, tileSize, true);
        }
        //player2
        for(int i =0; i<player2.length; i++){
            g.fill3DRect(player2[i].x*tileSize, player2[i].y*tileSize, tileSize, tileSize, true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall();
        movePlayer(player1, player1Speed);
        movePlayer(player2, player2Speed);
        repaint();
    }

    public void bounceOfWall(){
        if(ball.y*tileSize<0 ||ball.y*tileSize>boardHeight ){
            velocityY=-velocityY;
        }

    }
    public boolean ballHitsPaddle(Tile[] paddle){
        if(ball.y==paddle[0].y) {
            for (int i = 0; i < paddle.length; i++) {
                if (ball.x == paddle[i].x) {
                    return true;
                }
            }
        }
        return false;
    }
    public void hitBall(Tile[] paddle){

    }
    public void moveBall(){
        ball.x += velocityX;
        ball.y += velocityY;
        bounceOfWall();
        if(ballHitsPaddle(player1)){

        }
    }

    public void movePlayer(Tile[] paddle, int speed){
        int minY = paddle[0].y;
        for(int i=0; i<paddle.length;i++){
            if((minY + speed  > 0) && (paddle[4].y + speed <= 50 )) {
                paddle[i].y += speed;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W ){
            player1Speed = -2;
        }
        else if(e.getKeyCode()== KeyEvent.VK_S ){
            player1Speed=2;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player2Speed=-2;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            player2Speed=2;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class Tile{
        int x;
        int y;
        Tile(int x, int y){
            this.x=x;
            this.y =y;

        }

    }




}
