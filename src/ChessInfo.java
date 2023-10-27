import javax.swing.*;
import java.io.Serializable;

public class ChessInfo implements Serializable {
    public Piece piece;
    public int u, v;
    public int x, y;

    public boolean didPiecePromote;


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void changePieceImage(PieceInfo pawn){
        PieceInteraction.pieces[pawn.i][pawn.j] = new Queen(pawn);
        pawn.addPieceInicial("Q");
        pawn.getPieceLabel().setIcon(new ImageIcon(image1));
    }
}
