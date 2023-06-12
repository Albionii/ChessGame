public class King extends Piece {
    public boolean canNotCastle = false;
    public King(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
    }

    @Override
    public boolean isAnyPieceOnTheWay() {
        return false;
    }

    @Override
    public boolean isMoveInPieceScope() {
        int temp1 = Math.abs(xPos - pieceInfo.getLastX());
        int temp2 = Math.abs(yPos - pieceInfo.getLastY());
        return (temp1 * temp2 == 1 || (temp1 == 0 && temp2 == 1 || temp2 == 0 && temp1 == 1)) && !kingStareDown();
    }

    public boolean kingStareDown() {
        PieceInfo temp = pieceInfo != pieceInteraction.getPieceInfos()[0][4] ? pieceInteraction.getPieceInfos()[0][4] : pieceInteraction.getPieceInfos()[2][4];
        boolean xDistance = Math.abs(xPos - temp.getLastX()) <= 1;
        boolean yDistance = Math.abs(yPos - temp.getLastY()) <= 1;
        return xDistance && yDistance;
    }

    public void castle() {
        if (everChecked || canNotCastle) {
            canNotCastle = true;
        }


    }


}
