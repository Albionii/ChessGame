import javax.swing.*;
import java.io.Serializable;

public class PieceInteraction implements Serializable{
    public PieceInfo[][] pieceInfos;
    public static char whiteOrBlackTurn = 'W';
    public JLabel[][] pieceLabels;
    public boolean kingGotChecked;

    public Piece pieceThatAttacked;
    public boolean pieceMoved2Times;
    public PieceInfo pieceThatMoved2Times;
    public static Piece [][] pieces;

    public static Piece lastPieceMoved;

    public static int lastU, lastV;




    public PieceInteraction(PieceInfo[][] pieceInfos, JLabel[][] pieceLabels) {
        this.pieceLabels = pieceLabels;
        this.pieceInfos = pieceInfos;
    }


    public PieceInfo[][] getPieceInfos() {
        return pieceInfos;
    }

    public boolean isThereAPiece(int x, int y) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (x == pieceInfos[i][j].getLastX() && y == pieceInfos[i][j].getLastY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public PieceInfo findPieceInThatPosition(int xPosition, int yPosition){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceInfos[i][j].getLastX() == xPosition && pieceInfos[i][j].getLastY() == yPosition){
                    return pieceInfos[i][j];
                }
            }
        }
        return null;
    }

    public PieceInfo findPieceInfoInArray(PieceInfo p) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieceInfos[i][j].equals(p)){
                    return pieceInfos[i][j];
                }
            }
        }
        return null;
    }

    public Piece returnPieceWithPieceInfo(PieceInfo p){
        for (Piece [] p1 : pieces){
            for (Piece p2 : p1){
                if (p2.pieceInfo.equals(p)){
                    return p2;
                }
            }
        }
        return null;
    }

}
