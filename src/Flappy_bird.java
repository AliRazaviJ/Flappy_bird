import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Flappy_bird extends JPanel {
        int boardWidth=360;
        int boardHeight=640;

    public Flappy_bird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.blue);
    }
}
