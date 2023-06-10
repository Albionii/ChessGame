public class Bishop extends Piece{
    public Bishop(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }


    @Override
    public boolean isAnyPieceOnTheWay() {
        int min = 0, max = 0;

        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int k = pieceInfo.getLastX() - xPos;
        return yPos + k == pieceInfo.getLastY() || yPos - k == pieceInfo.getLastY();
    }
}
