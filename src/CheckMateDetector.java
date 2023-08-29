import java.util.Arrays;

public class CheckMateDetector {
    PieceInteraction pieceInteraction;
    PieceInfo king;
    static Piece[][] pieces;

    public CheckMateDetector(PieceInteraction pieceInteraction) {
        this.pieceInteraction = pieceInteraction;
        this.king = pieceInteraction.getPieceInfos()[pieceInteraction.whiteOrBlackTurn == 'B' ? 2 : 0][4];
        pieces = PieceInteraction.pieces;
    }

    public boolean isItCheckMate_Mate(){
        int [][] array = posOfAttackedSquares();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieceInteraction.pieceThatAttacked.pieceInfo.getColor() != pieceInteraction.pieceInfos[i][j].getColor()) && !pieceInteraction.pieceInfos[i][j].isPieceDead){
                    for (int [] setPos : array){
//                        System.out.println("pieces[i][j].isMoveInPieceScope(setPos[0], setPos[1]) : " + pieces[i][j].isMoveInPieceScope(setPos[0], setPos[1]));
//                        System.out.println("!pieces[i][j].isAnyPieceOnTheWay(setPos[0], setPos[1]) : " + !pieces[i][j].isAnyPieceOnTheWay(setPos[0], setPos[1]));
                        if(pieces[i][j].isMoveInPieceScope(setPos[0], setPos[1]) && !pieces[i][j].isAnyPieceOnTheWay(setPos[0], setPos[1])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int [][] posOfAttackedSquares(){
        int [][] array;

        int minX = Math.min(king.getLastX(), pieceInteraction.pieceThatAttacked.pieceInfo.getLastX());
        int maxX = Math.max(king.getLastX(), pieceInteraction.pieceThatAttacked.pieceInfo.getLastX());

        int minY = Math.min(king.getLastY(), pieceInteraction.pieceThatAttacked.pieceInfo.getLastY());
        int maxY = Math.max(king.getLastY(), pieceInteraction.pieceThatAttacked.pieceInfo.getLastY());

        int distance = minX - maxX == 0 ? Math.abs(minY-maxY) : Math.abs(minX - maxX);
        array = new int[distance-1][2];
        int countI = 0;



        if (minY - maxY != 0) {
            for (int i = minY+1; i < maxY; i++) {
                if (minX - maxX == 0){
                    if (pieceInteraction.pieceThatAttacked.isMoveInPieceScope(king.getLastX(), i)){
                        array[countI][0] = king.getLastX();
                        array[countI][1] = i;
                    }
                }
                else {
                    for (int j = minX+1; j < maxX; j++) {
                        if (pieceInteraction.pieceThatAttacked.isMoveInPieceScope(j, i)){
                            array[countI][0] = j;
                            array[countI][1] = i;
                        }
                    }
                }
                countI++;
            }
        }else {
            for (int j = minX+1; j < maxX; j++) {
                if (pieceInteraction.pieceThatAttacked.isMoveInPieceScope(j, king.getLastY())){
                    array[countI][0] = j;
                    array[countI][1] = king.getLastY();
                }
                countI++;
            }
        }

        return array;
    }

    public void print(){
        System.out.println(Arrays.deepToString(posOfAttackedSquares()));
    }
}
