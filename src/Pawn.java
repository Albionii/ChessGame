public class Pawn extends Piece{
    public Pawn(PieceInfo pieceInfo) {
        super(pieceInfo);
    }

    @Override
    public void pieceTakes() {
        if (pieceInteraction.isThereAPiece(xPos, yPos)){
            if (pieceInfo.getColor() == PieceInteraction.whiteOrBlackTurn && pieceInfo.getLastY() - yPos == 1 && Math.abs(pieceInfo.getLastX()-xPos) == 1){
                pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
            }
//            else if (pieceInfo.getColor() == 'B' && pieceInfo.getLastY() - yPos == -1 && Math.abs(pieceInfo.getLastX()-xPos) == 1){
//                pieceKilled(pieceInteraction.findPieceInThatPosition(xPos, yPos));
//            }

        }
    }

    @Override
    public boolean isAnyPieceOnTheWay(int x, int y) {
        if (pieceInfo.getLastX() == x){
//            if (pieceInfo.getColor() == 'B'){
//                for (int i = pieceInfo.getLastY()+1; i <= y; i++) {
//                    if (pieceInteraction.isThereAPiece(x, i)){
//                        return true;
//                    }
//                }
//            }
//            else {
            if (pieceInfo.getColor() == PieceInteraction.whiteOrBlackTurn){
                for (int i = pieceInfo.getLastY()-1; i >= y; i--) {
                    if (pieceInteraction.isThereAPiece(x, i)){
                        return true;
                    }
                }
            }
//            }

        }
        return false;
    }


    @Override
    public boolean isMoveInPieceScope() {
        int howManySquareMoved = Math.abs(pieceInfo.getLastY() - yPos);
        if (pieceInfo.getColor() == PieceInteraction.whiteOrBlackTurn){
            if (pieceInteraction.pieceMoved2Times && pieceInfo.getLastY() == pieceInteraction.pieceThatMoved2Times.getLastY()){
                if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == -1 && xPos == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - yPos == 1){
                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }
                else if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == 1 && xPos == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - yPos == 1){
                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }else if (Math.abs(pieceInfo.getLastX() - xPos) == 0 && (pieceInfo.getLastY() - yPos == 1)){
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }
            }

            if (howManySquareMoved == 2){
                pieceInteraction.pieceMoved2Times = true;
                pieceInteraction.pieceThatMoved2Times = pieceInfo;
            }

            if (pieceInteraction.isThereAPiece(xPos, yPos)){
                 return Math.abs(pieceInfo.getLastX() - xPos) <= 1 && pieceInfo.getLastY() - yPos == 1;
            }
            else{
                return Math.abs(pieceInfo.getLastX() - xPos) == 0 &&
                        (pieceInfo.getLastY() == 6
                            ? (pieceInfo.getLastY() - yPos <=2 && pieceInfo.getLastY() - yPos >=0)
                            : (pieceInfo.getLastY() - yPos <= 1 && pieceInfo.getLastY() - yPos >= 0));
            }
        }
        return false;
//        else {
//            if (pieceInteraction.pieceMoved2Times && pieceInfo.getLastY() == pieceInteraction.pieceThatMoved2Times.getLastY()){
//                if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == -1 && xPos == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - yPos == -1){
//                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }
//                else if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == 1 && xPos == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - yPos == -1){
//                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }else if (Math.abs(pieceInfo.getLastX() - xPos) == 0 && (pieceInfo.getLastY() - yPos == -1)){
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }
//            }
//            if (howManySquareMoved == 2){
//                pieceInteraction.pieceMoved2Times = true;
//                pieceInteraction.pieceThatMoved2Times = pieceInfo;
//            }
//            if (pieceInteraction.isThereAPiece(xPos, yPos)){
//                return Math.abs(pieceInfo.getLastX() - xPos) <= 1 && pieceInfo.getLastY() - yPos == -1;
//            }
//            else{
//                return Math.abs(pieceInfo.getLastX() - xPos) == 0 &&
//                        (pieceInfo.getLastY() == 1
//                                ? (pieceInfo.getLastY() - yPos >=-2 && pieceInfo.getLastY() - yPos <=0)
//                                : (pieceInfo.getLastY() - yPos <= 0 && pieceInfo.getLastY() - yPos >= -1)); //*Ky kod eshte me i zgjatur ne menyre qe piunat te mos kthehen pas
//            }
//
//        }
    }

    @Override
    public boolean isMoveInPieceScope(int x, int y) {
        int howManySquareMoved = Math.abs(pieceInfo.getLastY() - y);
        if (pieceInfo.getColor() == PieceInteraction.whiteOrBlackTurn){
            if (pieceInteraction.pieceMoved2Times && pieceInfo.getLastY() == pieceInteraction.pieceThatMoved2Times.getLastY()){
                if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == -1 && x == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - y == 1){
                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }
                else if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == 1 && x == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - y == 1){
                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }else if (Math.abs(pieceInfo.getLastX() - x) == 0 && (pieceInfo.getLastY() - y == 1)){
                    pieceInteraction.pieceMoved2Times = false;
                    return true;
                }
            }

            if (howManySquareMoved == 2){
                pieceInteraction.pieceMoved2Times = true;
                pieceInteraction.pieceThatMoved2Times = pieceInfo;
            }

            if (pieceInteraction.isThereAPiece(x, y)){
                return Math.abs(pieceInfo.getLastX() - x) <= 1 && pieceInfo.getLastY() - y == 1;
            }
            else{
                return Math.abs(pieceInfo.getLastX() - x) == 0 &&
                        (pieceInfo.getLastY() == 6
                                ? (pieceInfo.getLastY() - y <=2 && pieceInfo.getLastY() - y >=0)
                                : (pieceInfo.getLastY() - y <= 1 && pieceInfo.getLastY() - y >= 0));
            }
        }
        return false;
//        else {
//            if (pieceInteraction.pieceMoved2Times && pieceInfo.getLastY() == pieceInteraction.pieceThatMoved2Times.getLastY()){
//                if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == -1 && x == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - y == -1){
//                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }
//                else if (pieceInfo.getLastX() - pieceInteraction.pieceThatMoved2Times.getLastX() == 1 && x == pieceInteraction.pieceThatMoved2Times.getLastX() && pieceInfo.getLastY() - y == -1){
//                    pieceKilled(pieceInteraction.pieceThatMoved2Times);
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }else if (Math.abs(pieceInfo.getLastX() - x) == 0 && (pieceInfo.getLastY() - y == -1)){
//                    pieceInteraction.pieceMoved2Times = false;
//                    return true;
//                }
//            }
//            if (howManySquareMoved == 2){
//                pieceInteraction.pieceMoved2Times = true;
//                pieceInteraction.pieceThatMoved2Times = pieceInfo;
//            }
//            if (pieceInteraction.isThereAPiece(x, y)){
//                return Math.abs(pieceInfo.getLastX() - x) <= 1 && pieceInfo.getLastY() - y == -1;
//            }
//            else{
//                return Math.abs(pieceInfo.getLastX() - x) == 0 &&
//                        (pieceInfo.getLastY() == 1
//                                ? (pieceInfo.getLastY() - y >=-2 && pieceInfo.getLastY() - y <=0)
//                                : (pieceInfo.getLastY() - y <= 0 && pieceInfo.getLastY() - y >= -1)); //*Ky kod eshte me i zgjatur ne menyre qe piunat te mos kthehen pas
//            }
//
//        }
    }



    public boolean isKingInPieceScope(int xKing, int yKing){
        return (pieceInfo.getLastY() - yKing == 1) && (pieceInfo.getLastX() - 1 == xKing || pieceInfo.getLastX() + 1 == xKing);
    }
}
