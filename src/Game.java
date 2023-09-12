import javax.swing.*;
import java.awt.*;

public class Game {
    public Game(){
        JFrame frame = new JFrame("Loja");
        GameOver.frame = frame;
        Promote.gameFrame = frame;
        MovePieces.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        new DrawBoard(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}