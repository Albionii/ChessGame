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
        int k = pieceInfo.getLastX() - xPos;
        if (yPos > pieceInfo.getLastY()){

            for (int i = pieceInfo.getLastY()+1; i < yPos; i++) {
                int temp = (xPos < pieceInfo.getLastX() && xPos != pieceInfo.getLastX() ? --k : ++k);
                if (pieceInteraction.isThereAPiece(xPos + temp, i)){
                    System.out.println("X : " + (xPos + k));
                    System.out.println("Y : " + i);
                    return true;

                }
            }
        }
        else if (yPos < pieceInfo.getLastY()){
            for (int i = pieceInfo.getLastY()-1; i > yPos; i--) {
                int temp = (xPos < pieceInfo.getLastX() && xPos != pieceInfo.getLastX() ? --k : ++k);
                if (pieceInteraction.isThereAPiece(xPos + temp, i)){
                    System.out.println("X : " + (xPos + k));
                    System.out.println("Y : " + i);
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
}
