import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Flappy_bird extends JPanel {
        int boardWidth=360;
        int boardHeight=640;
        //Images
        Image backgroundImg;
        Image birdImg;
        Image topImg;
        Image bottomPipeImg;
    // Bird
    int birdx=boardWidth/2;
    int birdy=boardHeight/8;
    int birdWidth=34;
    int birdHegiht=24;

    class Bird {
        int x = birdx;
        int y = birdy;
        int width = birdWidth;
        int height = birdHegiht;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }
    //game logic
    Bird bird;

    public Flappy_bird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.blue);

        //load images
        backgroundImg=new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topImg=new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg=new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

       //bird
        bird=new Bird(birdImg);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //background
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        g.drawImage(birdImg,birdx,birdy,birdWidth,birdHegiht,null);
    }
}
