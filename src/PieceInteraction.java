import javax.swing.*;

public class PieceInteraction {
    public PieceInfo[][] pieceInfos;
    public char whiteOrBlackTurn = 'W';
    public JLabel[][] pieceLabels;


    public PieceInteraction(PieceInfo[][] pieceInfos, JLabel[][] pieceLabels) {
        this.pieceLabels = pieceLabels;
        this.pieceInfos = pieceInfos;
    }
    public PieceInfo[][] getPieceInfos() {
        return pieceInfos;
    }
    public void setPieceInfos(PieceInfo[][] pieceInfos) {
        this.pieceInfos = pieceInfos;
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
                if(pieceInfos[i][j].getLastX() == p.getLastX() && pieceInfos[i][j].getLastY() == p.getLastY()){
                    return pieceInfos[i][j];
                }
            }
        }
        return null;
    }
}
