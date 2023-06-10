public class Rook extends Piece{
    public Rook(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay() {
        int min = 0, max = 0;
        if (pieceInfo.getLastX() == xPos) {
            if (pieceInfo.getLastY() < yPos){
                min = pieceInfo.getLastY() + 1;
                max = yPos - 1;
            }else if (pieceInfo.getLastY() > yPos) {
                min = yPos + 1;
                max = pieceInfo.getLastY() - 1;
            }
            for (int i = min; i <= max; i++) {
                if (pieceInteraction.isThereAPiece(xPos, i)){
                    return true;
                }
            }

        }else if (pieceInfo.getLastY() == yPos) {
            if (pieceInfo.getLastX() < xPos){
                min = pieceInfo.getLastX() + 1;
                max = xPos - 1;
            }else if (pieceInfo.getLastX() > xPos) {
                min = xPos + 1;
                max = pieceInfo.getLastX() - 1;
            }

            for (int i = min; i <= max; i++) {
                if(pieceInteraction.isThereAPiece(i, yPos)){
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
}
