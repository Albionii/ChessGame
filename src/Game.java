import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Loja");
        GameOver.frame = frame;
        Promote.gameFrame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        new DrawBoard(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}