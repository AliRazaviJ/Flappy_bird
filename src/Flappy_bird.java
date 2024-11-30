import java.awt.*;
import java.awt.event.*;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Flappy_bird extends JPanel implements ActionListener ,KeyListener {
        int boardWidth=360;
        int boardHeight=640;
        //Images
        Image backgroundImg;
        Image birdImg;
        Image topImg;
        Image bottomPipeImg;
    // Bird
    int birdx=boardWidth/8;
    int birdy=boardHeight/2;
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
    //pipe
    int pipex=boardWidth;
    int pipey=0;
    int pipewidth=64;
    int pipeheight=512;
    class  pipe{
        int x=pipex;
        int y=pipey;
        int width=pipewidth;
        int height=pipeheight;
        Image img;
        Boolean passed=false;
        pipe(Image img){
            this.img=img;
        }
    }
    //game logic
    Bird bird;
    int vocity=0;
    int velociytx=-4;
    Timer gameloop;
    Timer palce_pipe_timer;
    Boolean gameover=false;
    double scorre=0;
    int garvity=1;
    ArrayList<pipe>pipes;
    Random random=new Random();
    public Flappy_bird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.blue);

        setFocusable(true);
        addKeyListener(this);

        //load images
        backgroundImg=new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topImg=new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg=new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

       //bird
        bird=new Bird(birdImg);
        pipes=new ArrayList<pipe>();
        //pace pipe timer

        palce_pipe_timer=new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });
        palce_pipe_timer.start();
        //game timer
        gameloop=new Timer(1000/60,this);
        gameloop.start();
    }
public void placePipe(){
        int randome_pipeY= (int) (pipey-pipeheight/4-Math.random()*(pipeheight/2));
        int opening_Gap=boardHeight/4;
        pipe topepipe=new pipe(topImg);
        topepipe.y=randome_pipeY;
        pipes.add(topepipe);
        pipe bottomepipe=new pipe(bottomPipeImg);
        bottomepipe.y=topepipe.y+pipeheight+opening_Gap;
        pipes.add(bottomepipe);
}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //background
        g.drawImage(backgroundImg,0,0,boardWidth,boardHeight,null);
        g.drawImage(birdImg,bird.x,bird.y,birdWidth,birdHegiht,null);

        //pipe
        for (int i = 0; i <pipes.size() ; i++) {
            pipe p= pipes.get(i);
            g.drawImage(p.img,p.x,p.y,p.width,p.height,null);

        }
        //score
        g.setColor(Color.green);
        g.setFont(new Font("Arial",Font.PLAIN,32));
        if(gameover){
            g.drawString("Game Over"+String.valueOf((int)scorre),10,35);
        }
        else {
            g.drawString(String.valueOf((int)scorre),10,35);
        }

    }
    public void move(){
        //bird
        vocity+=garvity;
        bird.y+=vocity;
        bird.y=Math.max(bird.y,0);
        //pipe
        for (int i = 0; i <pipes.size() ; i++) {
            pipe pipe1=pipes.get(i);
            pipe1.x+=velociytx;
            if(!pipe1.passed&& bird.x>pipe1.x+pipe1.width){
                pipe1.passed=true;
                scorre+=0.5;
            }
            if(collision(bird,pipe1)){
                gameover=true;
            }
        }
        if(bird.y>boardHeight){
            gameover=true;
        }

    }

public boolean collision(Bird a,pipe b){
        return a.x<b.x+b.width&&
                a.x+a.width>b.x&&
                a.y<b.y+b.height&&
                a.y+a.height>b.y;
}



    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameover){
            palce_pipe_timer.stop();
            gameloop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            vocity=-9;
            if(gameover){
                    //restar the game
                bird.y=birdy;
                vocity=0;
                pipes.clear();
                scorre=0;
                gameover=false;
                gameloop.start();
                palce_pipe_timer.start();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
