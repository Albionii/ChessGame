import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Loja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        DrawBoard board = new DrawBoard(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}