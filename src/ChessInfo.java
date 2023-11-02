import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ChessInfo implements Serializable {
    public Piece piece;
    public int u, v;
    public int x, y;

    public static boolean didPiecePromote;
    public static Image imageToPromote;
    public static char nameOfPromotion;
    public static PieceInfo pieceInfoPromoted;


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public static void setPromoteInfo(boolean b, Image i, char c, PieceInfo p){
        didPiecePromote = b;
        imageToPromote = i;
        nameOfPromotion = c;
        pieceInfoPromoted = p;
    }

    public void updateOpponentPieceToPromotion(Piece p){
        didPiecePromote = false;
        switch (nameOfPromotion){
            case 'Q' :
                p = new Queen(pieceInfoPromoted);
                p.pieceInfo.addPieceInicial("" + nameOfPromotion);
                p.pieceInfo.getPieceLabel().setIcon(new ImageIcon(imageToPromote));
                break;
            case 'B' :
                p = new Bishop(p.pieceInfo);
                p.pieceInfo.addPieceInicial("" + nameOfPromotion);
                p.pieceInfo.getPieceLabel().setIcon(new ImageIcon(imageToPromote));
                break;
            case 'N' :
                p = new Knight(p.pieceInfo);
                p.pieceInfo.addPieceInicial("" + nameOfPromotion);
                p.pieceInfo.getPieceLabel().setIcon(new ImageIcon(imageToPromote));
                break;
            case 'R' :
                p = new Rook(p.pieceInfo);
                p.pieceInfo.addPieceInicial("" + nameOfPromotion);
                p.pieceInfo.getPieceLabel().setIcon(new ImageIcon(imageToPromote));
                break;
        }


    }

}
