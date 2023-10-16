import java.io.Serializable;

public class ChessInfo implements Serializable {
    public Piece piece;
    public int u, v;
    public int x, y;


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
