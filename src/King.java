public class King extends Piece {
    CheckMateDetector checkMateDetector = new CheckMateDetector(pieceInteraction, this);
    public boolean canNotCastle = false;
    public King(PieceInfo pieceInfo) {
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
        int temp1 = Math.abs(xPos - pieceInfo.getLastX());
        int temp2 = Math.abs(yPos - pieceInfo.getLastY());
        boolean kingCastlePosition = pieceInfo.getColor() == 'W' ? (xPos == 6 && yPos == 7) || (xPos == 2 && yPos == 7) : (xPos == 6 && yPos == 0) || (xPos == 2 && yPos == 0);
        boolean condition1 = (temp1 * temp2 == 1 || (temp1 == 0 && temp2 == 1 || temp2 == 0 && temp1 == 1));
        boolean condition2 = condition1 || kingCastlePosition;

        if (canKingCastle() && !canNotCastle && condition2 && !kingStareDown()){
            pieceInteraction.findPieceInThatPosition((xPos == 6)?7:0, yPos).getPieceLabel().setLocation((xPos == 6)?500:300 , yPos*100);
            pieceInteraction.findPieceInThatPosition((xPos == 6)?7:0, yPos).setPiecePosition((xPos == 6)?5:3, yPos);
            canNotCastle = false;
//            pieceInteraction.whiteOrBlackTurn = pieceInfo.getColor() == 'W' ? 'B' : 'W';
            return true;
        }
        return condition1 && !kingStareDown();
    }

    public boolean kingStareDown() {
        PieceInfo temp = pieceInfo != pieceInteraction.getPieceInfos()[0][4] ? pieceInteraction.getPieceInfos()[0][4] : pieceInteraction.getPieceInfos()[2][4];
        boolean xDistance = Math.abs(xPos - temp.getLastX()) <= 1;
        boolean yDistance = Math.abs(yPos - temp.getLastY()) <= 1;
        return xDistance && yDistance;
    }

    public boolean isLegalMove() {
        if (isItYourTurn() && !isSquareUnderAttack() && !ifPieceDidNotMove() && isMoveInPieceScope()){
            canNotCastle = true;
            return true;
        }
        return false;
    }

    public boolean isSquareUnderAttack() {
        int tempXKing = 0;
        int tempYKing = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieceInfo.getColor() != pieceInteraction.pieceInfos[i][j].getColor()) && !pieceInteraction.pieceInfos[i][j].isPieceDead){
                    Piece piece = PieceFactory.createPiece(pieceInteraction.pieceInfos[i][j]);
                    piece.setXandY(pieceInteraction.pieceInfos[i][j].getLastX(), pieceInteraction.pieceInfos[i][j].getLastY());
                    tempYKing = pieceInfo.getLastY();
                    tempXKing = pieceInfo.getLastX();
                    pieceInfo.setPiecePosition(xPos, yPos);
                    pieceInfo.getPieceLabel().setLocation(xPos*100, yPos*100);
                    if (piece.kingChecked()){
                        System.out.println("tempXKing : " + tempXKing);
                        System.out.println("tempYKing : " + tempYKing);
                        System.out.println("Figura qe sulmon : " + pieceInteraction.pieceInfos[i][j].getName());
                        System.out.println("Ngjyra e fig : " + pieceInteraction.pieceInfos[i][j].getColor());
                        pieceInfo.setPiecePosition(tempXKing, tempYKing);
                        pieceInfo.getPieceLabel().setLocation(tempXKing*100, tempYKing*100);
                        canNotCastle = true;
                        return true;
                    }
                    else {
                        pieceInfo.setPiecePosition(tempXKing, tempYKing);
                        pieceInfo.getPieceLabel().setLocation(tempXKing*100, tempYKing*100);
                    }
                }
            }
        }
        pieceInteraction.whiteOrBlackTurn = pieceInfo.getColor() == 'W' ? 'B' : 'W';
        return false;
    }

    public boolean isSquareUnderAttack(int one) {
        int tempXKing = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieceInfo.getColor() != pieceInteraction.pieceInfos[i][j].getColor()) && !pieceInteraction.pieceInfos[i][j].isPieceDead){
                    Piece piece = PieceFactory.createPiece(pieceInteraction.pieceInfos[i][j]);
                    piece.setXandY(pieceInteraction.pieceInfos[i][j].getLastX(), pieceInteraction.pieceInfos[i][j].getLastY());
                    tempXKing = pieceInfo.getLastX();
                    pieceInfo.setPiecePosition((pieceInfo.getLastX()+one), pieceInfo.getLastY());
                    pieceInfo.getPieceLabel().setLocation((pieceInfo.getLastX()+one)*100, yPos*100);
                    if (piece.kingChecked()){
                        pieceInfo.getPieceLabel().setLocation(tempXKing*100, (pieceInfo.getLastY())*100);
                        return true;
                    }
                    else {
                        pieceInfo.setPiecePosition(tempXKing, (pieceInfo.getLastY()));
                        pieceInfo.getPieceLabel().setLocation(tempXKing*100, (pieceInfo.getLastY())*100);
                    }
                }
            }
        }
//        pieceInteraction.whiteOrBlackTurn = pieceInfo.getColor() == 'W' ? 'B' : 'W';
        return false;
    }

    public boolean canKingCastle() {
        if (!canNotCastle && ((xPos - pieceInfo.getLastX()) == 2 || (xPos - pieceInfo.getLastX()) == -2) && yPos == pieceInfo.getLastY()){
            for(int i = pieceInfo.getLastX()+1; i < xPos; i++){
                if (pieceInteraction.isThereAPiece(i, pieceInfo.getLastY()) || isSquareUnderAttack(1)){
                    return false;
                }
            }
            for(int i = xPos+1; i < pieceInfo.getLastX(); i++){
                if (pieceInteraction.isThereAPiece(i, pieceInfo.getLastY()) || isSquareUnderAttack(-1)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }



}
