public class King extends Piece {

    public King(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay() {
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int temp1 = Math.abs(xPos - pieceInfo.getLastX());
        int temp2 = Math.abs(yPos - pieceInfo.getLastY());
        return temp1 * temp2 == 1 || temp1 * temp2 == 0;
    }


}
