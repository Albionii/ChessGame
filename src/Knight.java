public class Knight extends Piece{
    public Knight(PieceInfo pieceInfo) {
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
        int xDifference = Math.abs(pieceInfo.getLastX() - xPos);
        int yDifference = Math.abs(pieceInfo.getLastY() - yPos);
        return xDifference + yDifference == 3 && xDifference != 0 && yDifference!= 0;
    }

    @Override
    public boolean kingChecked() {

        for (int i = 1; i <= 2; i++) {
            for (int j = 1;j <= 2; j++) {
                if (Math.abs(i) + Math.abs(j) == 3) {
                    if (pieceInteraction.isThereAPiece(xPos - i, yPos - j)){
                        if (pieceInteraction.findPieceInThatPosition(xPos-i, yPos -j).getName().equals("K")
                                && pieceInteraction.findPieceInThatPosition(xPos-i, yPos -j).getColor() != pieceInfo.getColor()){
                            return true;
                        }
                    }

                    if (pieceInteraction.isThereAPiece(xPos + i, yPos + j)){
                        if (pieceInteraction.findPieceInThatPosition(xPos+i, yPos +j).getName().equals("K")
                                && pieceInteraction.findPieceInThatPosition(xPos+i, yPos +j).getColor() != pieceInfo.getColor()){
                            return true;
                        }
                    }

                    if (pieceInteraction.isThereAPiece(xPos + i, yPos - j)){
                        if (pieceInteraction.findPieceInThatPosition(xPos+i, yPos -j).getName().equals("K")
                                && pieceInteraction.findPieceInThatPosition(xPos+i, yPos -j).getColor() != pieceInfo.getColor()){
                            return true;
                        }
                    }

                    if (pieceInteraction.isThereAPiece(xPos - i, yPos + j)){
                        if (pieceInteraction.findPieceInThatPosition(xPos-i, yPos +j).getName().equals("K")
                                && pieceInteraction.findPieceInThatPosition(xPos-i, yPos +j).getColor() != pieceInfo.getColor()){
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }

}
