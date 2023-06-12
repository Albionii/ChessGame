public class Queen extends Piece{
    public Queen(PieceInfo pieceInfo) {
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
            if (pieceInfo.getLastX() < xPos) {
                min = pieceInfo.getLastX() + 1;
                max = xPos - 1;
            } else if (pieceInfo.getLastX() > xPos) {
                min = xPos + 1;
                max = pieceInfo.getLastX() - 1;
            }

            for (int i = min; i <= max; i++) {
                if (pieceInteraction.isThereAPiece(i, yPos)) {
                    return true;
                }
            }
        }else {

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
                        System.out.println("qekjo");
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

    @Override
    public boolean kingChecked() {

        // Kur x konst

        for (int i = yPos+1; i <= 7; i++) {
            if (pieceInteraction.isThereAPiece(xPos, i)) {
                if (pieceInteraction.findPieceInThatPosition(xPos, i).getName().equals("K") && pieceInteraction.findPieceInThatPosition(xPos, i).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }
        for (int i = yPos-1; i >= 0; i--) {
            if (pieceInteraction.isThereAPiece(xPos, i)){
                if (pieceInteraction.findPieceInThatPosition(xPos, i).getName().equals("K") && pieceInteraction.findPieceInThatPosition(xPos, i).getColor() != pieceInfo.getColor()) {
                    return true;
                }
                break;
            }
        }


        // Kur y konst
        for (int i = xPos+1; i <= 7; i++) {
            if (pieceInteraction.isThereAPiece(i, yPos)){
                if (pieceInteraction.findPieceInThatPosition(i, yPos).getName().equals("K") && pieceInteraction.findPieceInThatPosition(i, yPos).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }


        for (int i = xPos-1; i >= 0; i--) {
            if (pieceInteraction.isThereAPiece(i, yPos)){
                if (pieceInteraction.findPieceInThatPosition(i, yPos).getName().equals("K") && pieceInteraction.findPieceInThatPosition(i, yPos).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }

        for (int i = yPos + 1; i <= 7; i++) {
            if (pieceInteraction.isThereAPiece(xPos + (yPos-i), i)){
                if (pieceInteraction.findPieceInThatPosition(xPos + (yPos-i), i).getName().equals("K")
                        && pieceInteraction.findPieceInThatPosition(xPos + (yPos-i), i).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }

        for (int i = yPos + 1; i <= 7; i++) {
            if (pieceInteraction.isThereAPiece(xPos - (yPos-i), i)){
                if (pieceInteraction.findPieceInThatPosition(xPos - (yPos-i), i).getName().equals("K")
                        && pieceInteraction.findPieceInThatPosition(xPos - (yPos-i), i).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }


        for (int i = yPos - 1; i >=0; i--) {
            if (pieceInteraction.isThereAPiece(xPos + (yPos-i), i)){
                if (pieceInteraction.findPieceInThatPosition(xPos + (yPos-i), i).getName().equals("K")
                        && pieceInteraction.findPieceInThatPosition(xPos + (yPos-i), i).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }

        for (int i = yPos - 1; i >=0; i--) {
            if (pieceInteraction.isThereAPiece(xPos -(yPos-i), i)){
                if (pieceInteraction.findPieceInThatPosition(xPos - (yPos-i), i).getName().equals("K")
                        && pieceInteraction.findPieceInThatPosition(xPos - (yPos-i), i).getColor() != pieceInfo.getColor()){
                    return true;
                }
                break;
            }
        }


        return false;
    }

}
