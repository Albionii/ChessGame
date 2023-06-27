public class Knight extends Piece{
    public Knight(PieceInfo pieceInfo) {
        super(pieceInfo);
    }


    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay(int x, int y) {
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int xDifference = Math.abs(pieceInfo.getLastX() - xPos);
        int yDifference = Math.abs(pieceInfo.getLastY() - yPos);
        return xDifference + yDifference == 3 && xDifference != 0 && yDifference!= 0;
    }
    public boolean isKingInPieceScope(int xKing, int yKing) {
        int xDifference = Math.abs(pieceInfo.getLastX() - xKing);
        int yDifference = Math.abs(pieceInfo.getLastY() - yKing);
        return xDifference + yDifference == 3 && xDifference != 0 && yDifference!= 0;
    }
}
