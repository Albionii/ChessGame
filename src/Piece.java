public abstract class Piece {
    public PieceInfo pieceInfo;
    public static PieceInteraction pieceInteraction;
    public int xPos, yPos;

    public Piece(PieceInfo pieceInfo) {
        this.pieceInfo = pieceInfo;
    }

    public void restartPreviousMove(){
        pieceInfo.setPiecePosition(pieceInfo.getLastX(), pieceInfo.getLastY());
        pieceInfo.getPieceLabel().setLocation(pieceInfo.getLastX()*100, pieceInfo.getLastY()*100);
    }

    public void pieceKilled(PieceInfo p) {
        p.setPiecePosition(9, 1);
        p.getPieceLabel().setLocation(900, 100);
    }

    public void move(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
        if (isLegalMove()){
            if (pieceInteraction.isThereAPiece(xPos, yPos)){
                pieceTakes();
            }
            updatePosition();

        }else {
            restartPreviousMove();
        }
    }



    public void updatePosition(){
        pieceInfo.getPieceLabel().setLocation(xPos*100, yPos*100);
        pieceInfo.setPiecePosition(xPos, yPos);
        if (pieceInfo.getColor() == 'W') {
            pieceInteraction.whiteOrBlackTurn = 'B';
        }
        else {
            pieceInteraction.whiteOrBlackTurn = 'W';
        }
    }

    public boolean isLegalMove() {
        if (isItYourTurn() && !isAnyPieceOnTheWay() && isMoveInPieceScope()){
            return true;
        }
        return false;
    }

    public abstract void pieceTakes();
    public abstract boolean isAnyPieceOnTheWay();
    public abstract boolean isItYourTurn();
    public abstract boolean isMoveInPieceScope();


}
