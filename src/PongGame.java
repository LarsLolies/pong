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
    int directionX=0;
    int speedX=0;
    int directionY;
    int speedY=0;

    int moveBallCounterX = 0;
    int moveBallCounterY = 0;

    Tile[] player1;
    int player1Speed=0;

    Tile[] player2;
    int player2Speed=0;

    int movePlayerCounter=0;






    Timer gameLoop;
    Random random;
    int delay =13;



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



        while(directionY == 0 ) {
            this.directionY=random.nextInt(-1,2);
        }
        speedY=random.nextInt(0,2);


//        this.directionX= random.nextInt(-3, 4);
        while (directionX==0){
            this.directionX= random.nextInt(-1,2);
        }
        speedX=random.nextInt(1,2);


        this.gameLoop = new Timer(delay, this);
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
        drawPlayer(g, player1);
        drawPlayer(g, player2);
    }

    public void drawPlayer(Graphics g, Tile[] player){
        for(int i =0; i<player.length; i++){
            g.fill3DRect(player[i].x*tileSize, player[i].y*tileSize, tileSize, tileSize, true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(moveBallCounterX % (6/speedX) == 0) {
            moveBallX();
        }
        moveBallCounterX++;

        if(speedY!=0){
            if(moveBallCounterY % (6/speedY) == 0) {
                moveBallY();
            }
            moveBallCounterY++;
        }

        if(movePlayerCounter%3==0){
            movePlayer(player1, player1Speed);
            movePlayer(player2, player2Speed);
        }
        movePlayerCounter ++;

        repaint();
    }

    public void bounceOfWall(){
        if(ball.y*tileSize<0 ||ball.y*tileSize>boardHeight ){
            directionY=-directionY;
        }

    }
    public boolean ballHitsPaddle(Tile[] paddle){
        if(ball.x==paddle[0].x) {
            for (int i = 0; i < paddle.length; i++) {
                if (ball.y == paddle[i].y) {
                    return true;
                }
            }
        }
        return false;
    }
    public void hitBall(Tile[] paddle){
        directionX = -directionX;
        speedX=random.nextInt(1,3);
        speedY=random.nextInt(0,2);
//        do{speedX += random.nextInt(-1,2);}
//        while (Math.abs(speedX)<=3 );
    }
    public void moveBallX(){
        ball.x += directionX;
        if(ballHitsPaddle(player1)){
            hitBall(player1);
        }
        if(ballHitsPaddle(player2)){
            hitBall(player2);
        }
    }

    public void moveBallY(){
        ball.y += directionY;
        bounceOfWall();
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
            player1Speed = -1;
        }
        else if(e.getKeyCode()== KeyEvent.VK_S ){
            player1Speed=1;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player2Speed=-1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            player2Speed=1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_T){
            delay=50;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W ){
            player1Speed = 0;
        }
        else if(e.getKeyCode()== KeyEvent.VK_S ){
            player1Speed=0;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            player2Speed=-0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            player2Speed=0;
        }

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
