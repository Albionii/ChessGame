import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MovePieces implements MouseListener, MouseMotionListener {
    private PieceInfo[][] pieceInfos;
    private JLabel boardLabel;
    private int u=0, v=0;
    private int xPosition=0, yPosition=0;
    private PieceInteraction pieceInteraction;
    private Piece [][] pieces;

    Point mouse;

    public MovePieces(PieceInfo[][] pieceInfos, JLabel boardLabel, PieceInteraction pi) {
        this.pieceInfos = pieceInfos;
        this.boardLabel = boardLabel;
        this.pieceInteraction = pi;
        Piece.pieceInteraction = pieceInteraction;
        addListenerToLabels();
        setPieceArrauy();
    }

    public PieceInteraction getPieceInteraction() {
        return pieceInteraction;
    }

    public void addListenerToLabels() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                pieceInfos[i][j].getPieceLabel().addMouseListener(this);
                pieceInfos[i][j].getPieceLabel().addMouseMotionListener(this);
            }
        }
        boardLabel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == pieceInfos[i][j].getPieceLabel()){
                    u = i;
                    v = j;
                }
            }
        }

        if (e.getSource() == boardLabel) {
            System.out.println("Nuk perka asnje figure");
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == pieceInfos[u][v].getPieceLabel()){
            mouse = boardLabel.getMousePosition();
            if (mouse != null) {
                pieceInfos[u][v].getPieceLabel().setLocation(mouse.x - 50, mouse.y - 50);
                xPosition = (int)Math.round((mouse.x-50)/100.0);
                yPosition = (int)Math.round((mouse.y-50)/100.0);
            }
        }
    }

    public void setPieceArrauy(){
        pieces = new Piece[4][8];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 8; j++){
                pieces[i][j] = PieceFactory.createPiece(pieceInfos[i][j]);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == pieceInfos[u][v].getPieceLabel()){
//            Piece piece = PieceFactory.createPiece(pieceInfos[u][v]);
//            piece.move(xPosition, yPosition);
//            piece = null;
            pieces[u][v].move(xPosition, yPosition);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
