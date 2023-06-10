public class King extends Piece {
    public King(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {

    }

    @Override
    public boolean isAnyPieceOnTheWay() {
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        return false;
    }
}
