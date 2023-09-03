import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Promote implements ActionListener {
    // Queen, Rook, Knight, Bishop
    public static BufferedImage[][] pieceImages;
    public static JFrame gameFrame;
    public PieceInfo pawn;
    public PieceInteraction pieceInteraction;
    public static JLabel[][] labels;

    JFrame frame = new JFrame("Promotion");
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();

    Image image1;
    Image image2;
    Image image3;
    Image image4;


    public Promote(PieceInfo pawn, PieceInteraction pieceInteraction){
        this.pawn = pawn;
        this.pieceInteraction = pieceInteraction;
    }

    public void runBaby(){
        if (pieceInteraction.whiteOrBlackTurn == 'W'){
            image1 = pieceImages[0][0].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image2 = pieceImages[1][0].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image3 = pieceImages[2][0].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image4 = pieceImages[3][0].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        }else{
            image1 = pieceImages[0][1].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image2 = pieceImages[1][1].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image3 = pieceImages[2][1].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            image4 = pieceImages[3][1].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        }
        button1.setIcon(new ImageIcon(image1));
        button2.setIcon(new ImageIcon(image2));
        button3.setIcon(new ImageIcon(image3));
        button4.setIcon(new ImageIcon(image4));
        addAction();
        frame.setLayout(new GridLayout(1, 4));
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setSize(400, 80);
        frame.setLocation(100, 100);
        frame.toFront();
        frame.setLocationRelativeTo(gameFrame);
        frame.setUndecorated(true);
        frame.setVisible(true);

    }

    public void addAction(){
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1){
            PieceInteraction.pieces[3][4] = new Queen(pawn);
            pawn.addPieceInicial("Q");
            pawn.getPieceLabel().setIcon(new ImageIcon(image1));
            frame.dispose();
        }
    }
}
