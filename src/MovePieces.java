import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovePieces implements MouseListener, MouseMotionListener, KeyListener {
    private PieceInfo[][] pieceInfos;
    private JLabel boardLabel;
    private int u=0, v=0;
    private int xPosition=0, yPosition=0;
    private int previousXPosition = 0, previousYPosition = 0;
    private PieceInteraction pieceInteraction;
    private Piece [][] pieces;
    public ReturnMoves returnMoves = new ReturnMoves();
    public static JFrame frame;

    Point mouse;

    public MovePieces(PieceInfo[][] pieceInfos, JLabel boardLabel, PieceInteraction pi) {
        this.pieceInfos = pieceInfos;
        this.boardLabel = boardLabel;
        this.pieceInteraction = pi;
        Piece.pieceInteraction = pieceInteraction;
        ReturnMoves.pieceInteraction = pieceInteraction;
        addListenerToLabels();
        addKeyListener();
        setPieceArray();
    }

    public void addKeyListener(){
        frame.addKeyListener(this);
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

    public void setPieceArray(){
        pieces = new Piece[4][8];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 8; j++){
                pieces[i][j] = PieceFactory.createPiece(pieceInfos[i][j]);
                pieceInfos[i][j].saveIandJ(i ,j);
            }
        }
        PieceInteraction.pieces = pieces;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == pieceInfos[u][v].getPieceLabel()){
            previousXPosition = pieceInfos[u][v].getLastX();
            previousYPosition = pieceInfos[u][v].getLastY();
            pieces[u][v].move(xPosition, yPosition);
            if (Piece.didPieceMove){
                returnMoves.saveMove(pieceInfos[u][v] , previousXPosition, previousYPosition, xPosition, yPosition);
                PieceInteraction.lastPieceMoved = pieces[u][v];
                PieceInteraction.lastU = u;
                PieceInteraction.lastV = v;

                if(pieceInfos[u][v].didThisMovePieceTake){
                    returnMoves.saveMove(pieceInfos[u][v].lastPieceKilled, xPosition, yPosition, 9, 1);
                }
            }
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            //*Code for previous moves
            returnMoves.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            //*Code for previous moves
            returnMoves.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            //*Code for previous moves
            returnMoves.printAllMoves();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
