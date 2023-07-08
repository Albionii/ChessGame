public class Bishop extends Piece{
    public Bishop(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay(int x, int y) {
        int min = 0, max = 0;
        int k = pieceInfo.getLastX() - x; // 5
        if (y > pieceInfo.getLastY()){
            for (int i = pieceInfo.getLastY()+1; i < y; i++) {
                int temp = (x < pieceInfo.getLastX() && x != pieceInfo.getLastX() ? --k : ++k);
                if (pieceInteraction.isThereAPiece(x + temp, i)){
                    return true;
                }
            }
        }
        else if (y < pieceInfo.getLastY()){
            for (int i = pieceInfo.getLastY()-1; i > y; i--) {
                int temp = (x < pieceInfo.getLastX() && x != pieceInfo.getLastX() ? --k : ++k);// 4 6
                if (pieceInteraction.isThereAPiece(x + temp, i)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int k = pieceInfo.getLastX() - xPos;
        return yPos + k == pieceInfo.getLastY() || yPos - k == pieceInfo.getLastY();
    }
    public boolean isKingInPieceScope(int xKing, int yKing) {
        int k = pieceInfo.getLastX() - xKing;
        return !(pieceInfo.getLastX() == xKing && pieceInfo.getLastY() == yKing) && (yKing + k == pieceInfo.getLastY() || yKing - k == pieceInfo.getLastY());
    }


}
