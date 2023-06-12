public class Pawn extends Piece{
    private boolean enPassant;
    public Pawn(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public boolean kingChecked() {
        return false;
    }

    @Override
    public void pieceTakes() {
        if (pieceInteraction.isThereAPiece(xPos, yPos)){
            if (pieceInfo.getColor() == 'W' && pieceInfo.getLastY() - yPos == 1 && Math.abs(pieceInfo.getLastX()-xPos) == 1){
                pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
            }
            if (pieceInfo.getColor() == 'B' && pieceInfo.getLastY() - yPos == -1 && Math.abs(pieceInfo.getLastX()-xPos) == 1){
                pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
            }

            // !Rasti enPassant me duhet me u merr me to pak ma vone

        }
    }

    /**
     * Nese nje figure gjendet ne mes te rruges se nje figure tjeter.
     * Kjo metode ekziston qe te mos kapercehen figurat.
     * @return Kthen vlere boolean.
     * True nese figura ekziston, dhe
     * false nese figura nuk ekziston.
     */
    @Override
    public boolean isAnyPieceOnTheWay() {
        if (pieceInfo.getLastX() == xPos){
            if (pieceInfo.getColor() == 'B'){
                for (int i = pieceInfo.getLastY()+1; i <= yPos; i++) {
                    if (pieceInteraction.isThereAPiece(xPos, i)){
                        return true;
                    }
                }
            }
            else {
                for (int i = pieceInfo.getLastY()-1; i >= yPos; i--) {
                    if (pieceInteraction.isThereAPiece(xPos, i)){
                        return true;
                    }
                }
            }

        }
        return false;
    }


    @Override
    public boolean isMoveInPieceScope() {
        if (pieceInfo.getColor() == 'W'){
            if (pieceInteraction.isThereAPiece(xPos, yPos))
                return Math.abs(pieceInfo.getLastX() - xPos) <= 1 && pieceInfo.getLastY() - yPos <= 1;
            else
                return Math.abs(pieceInfo.getLastX() - xPos) == 0 &&
                        (pieceInfo.getLastY() == 6
                            ? (pieceInfo.getLastY() - yPos <=2 && pieceInfo.getLastY() - yPos >=0)
                            : (pieceInfo.getLastY() - yPos <= 1 && pieceInfo.getLastY() - yPos >= 0));
        }
        else {
            if (pieceInteraction.isThereAPiece(xPos, yPos))
                return Math.abs(pieceInfo.getLastX() - xPos) <= 1 && pieceInfo.getLastY() - yPos >= -1;
            else
                return Math.abs(pieceInfo.getLastX() - xPos) == 0 &&
                        (pieceInfo.getLastY() == 1
                                ? (pieceInfo.getLastY() - yPos >=-2 && pieceInfo.getLastY() - yPos <=0)
                                : (pieceInfo.getLastY() - yPos <= 0 && pieceInfo.getLastY() - yPos >= -1)); //*Ky kod eshte me i zgjatur ne menyre qe piunat te mos kthehen pas
        }
    }
}
