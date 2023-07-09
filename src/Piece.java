public abstract class Piece {
    public PieceInfo pieceInfo;
    public boolean isKingInCheck;
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
        pieceInteraction.findPieceInThatPosition(p.getLastX(), p.getLastY()).isPieceDead = true;
//        pieceInteraction.findPieceInfoInArray(p).setPiecePosition(9 ,1);
//        pieceInteraction.findPieceInfoInArray(p).getPieceLabel().setLocation(900, 100);
//
        p.setPiecePosition(9, 1);
        p.getPieceLabel().setLocation(900, 100);
    }


    public void move(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
        if (isLegalMove()){
            if (pieceInteraction.isThereAPiece(xPos, yPos)){
                if (pieceInteraction.findPieceInThatPosition(xPos, yPos).getColor() != pieceInfo.getColor() && !kingChecked()){
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
        pieceInteraction.whiteOrBlackTurn = pieceInfo.getColor() == 'W' ? 'B' : 'W';
    }

    public boolean isLegalMove() {return isItYourTurn() && !isAnyPieceOnTheWay(xPos, yPos) && isMoveInPieceScope() && !ifPieceDidNotMove();}
    public boolean isItYourTurn() {
        return pieceInfo.getColor() == pieceInteraction.whiteOrBlackTurn;
    }
    public boolean ifPieceDidNotMove(){return pieceInfo.getLastX() == xPos && pieceInfo.getLastY() == yPos;}
    public boolean isKingInPieceScope(int xKing, int yKing) {return false;}
    public boolean kingChecked() {
        int x = pieceInteraction.getPieceInfos()[pieceInfo.getColor()=='B' ? 2:0][4].getLastX();
        int y = pieceInteraction.getPieceInfos()[pieceInfo.getColor()=='B' ? 2:0][4].getLastY();
        return isKingInPieceScope(x,y) && !isAnyPieceOnTheWay(x,y);
    }

    public void setXandY(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }



    //* Metodat abstrakte
    public abstract void pieceTakes();
    public abstract boolean isAnyPieceOnTheWay(int x, int y);
    public abstract boolean isMoveInPieceScope();


}
