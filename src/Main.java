//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        int boardWidth=360;
        int boardHeight=640;

        JFrame frame= new JFrame("Flappy Bird");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Flappy_bird flappyBird=new Flappy_bird();
        frame.add(flappyBird);
        frame.pack();
        frame.setVisible(true);
    }
}