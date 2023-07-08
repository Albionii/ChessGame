public class Queen extends Piece{
    public Queen(PieceInfo pieceInfo) {
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
            if (pieceInfo.getLastX() < x) {
                min = pieceInfo.getLastX() + 1;
                max = x - 1;
            } else if (pieceInfo.getLastX() > x) {
                min = x + 1;
                max = pieceInfo.getLastX() - 1;
            }

            for (int i = min; i <= max; i++) {
                if (pieceInteraction.isThereAPiece(i, y)) {
                    return true;
                }
            }
        }else {

            int k = pieceInfo.getLastX() - x;
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
                    int temp = (x < pieceInfo.getLastX() && x != pieceInfo.getLastX() ? --k : ++k);
                    if (pieceInteraction.isThereAPiece(x + temp, i)){
                        return true;

                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int k = pieceInfo.getLastX() - xPos;
        return xPos == pieceInfo.getLastX() || yPos == pieceInfo.getLastY() || yPos + k == pieceInfo.getLastY() || yPos - k == pieceInfo.getLastY();
    }

    public boolean isKingInPieceScope(int xKing, int yKing){
        int k = pieceInfo.getLastX() - xKing;
        return !(pieceInfo.getLastX() == xKing && pieceInfo.getLastY() == yKing) && (xKing == pieceInfo.getLastX() || yKing == pieceInfo.getLastY() || yKing + k == pieceInfo.getLastY() || yKing - k == pieceInfo.getLastY());
    }

}
