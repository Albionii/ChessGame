public abstract class Piece {
    public PieceInfo pieceInfo;
    public boolean everChecked;
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
                if (pieceInteraction.findPieceInThatPosition(xPos, yPos).getColor() != pieceInfo.getColor()){
                    pieceTakes();
                }else {
                    restartPreviousMove();
                    return;
                }
            }
            updatePosition();

        }else {
            restartPreviousMove();
        }
    }



    public void updatePosition(){
        pieceInfo.getPieceLabel().setLocation(xPos*100, yPos*100);
        pieceInfo.setPiecePosition(xPos, yPos);
        if (pieceInfo.getColor() == 'W')
            pieceInteraction.whiteOrBlackTurn = 'B';
        else
            pieceInteraction.whiteOrBlackTurn = 'W';
    }

    public boolean isLegalMove() {return isItYourTurn() && !isAnyPieceOnTheWay() && isMoveInPieceScope() && !ifPieceDidNotMove();}
    public boolean isItYourTurn() {
        return pieceInfo.getColor() == pieceInteraction.whiteOrBlackTurn;
    }
    public boolean ifPieceDidNotMove(){return pieceInfo.getLastX() == xPos && pieceInfo.getLastY() == yPos;}
    public  boolean kingChecked(){
        return false;
    };




    //* Metodat abstrakte
    public abstract void pieceTakes();
    public abstract boolean isAnyPieceOnTheWay();
    public abstract boolean isMoveInPieceScope();


}
