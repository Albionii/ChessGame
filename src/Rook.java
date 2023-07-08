public class Rook extends Piece{
    public Rook(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay(int x, int y) {
        int min = 0, max = 0;
        if (pieceInfo.getLastX() == x) {
            if (pieceInfo.getLastY() < y){
                min = pieceInfo.getLastY() + 1;
                max = y - 1;
            }else if (pieceInfo.getLastY() > y) {
                min = y + 1;
                max = pieceInfo.getLastY() - 1;
            }
            for (int i = min; i <= max; i++) {
                if (pieceInteraction.isThereAPiece(x, i)){
                    return true;
                }
            }

        }else if (pieceInfo.getLastY() == y) {
            if (pieceInfo.getLastX() < x){
                min = pieceInfo.getLastX() + 1;
                max = x - 1;
            }else if (pieceInfo.getLastX() > x) {
                min = x + 1;
                max = pieceInfo.getLastX() - 1;
            }

            for (int i = min; i <= max; i++) {
                if(pieceInteraction.isThereAPiece(i, y)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        return pieceInfo.getLastX() == xPos || pieceInfo.getLastY() == yPos;
    }

    public boolean isKingInPieceScope(int xKing, int yKing) {
        return !(pieceInfo.getLastX() == xKing && pieceInfo.getLastY() == yKing)&&(pieceInfo.getLastX() == xKing || pieceInfo.getLastY() == yKing);
    }

}
