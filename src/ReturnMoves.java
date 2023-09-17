import java.util.ArrayList;
import java.util.Arrays;

public class ReturnMoves {
    Object[] objects;
    public ArrayList<Object[]> previousMoves = new ArrayList<>();
    public int currentIndex;
    static PieceInteraction pieceInteraction;
    public boolean firstLeft = true;
    public boolean firstRight = true;

    public void saveMove(PieceInfo pieceInfo, int previousX, int previousY, int currentX, int currentY){
        objects = new Object[]{pieceInfo, previousX, previousY, currentX, currentY};
        previousMoves.add(objects);
        currentIndex = previousMoves.size()-1;
    }

    public void moveLeft(){
        if (currentIndex-1 < previousMoves.size() && currentIndex >= 0){
            if (currentIndex != 0 && !firstLeft){
                currentIndex -= 1;
            }
            int x = (int)previousMoves.get(currentIndex)[1];
            int y = (int)previousMoves.get(currentIndex)[2];
            int checkXOutOfBound = (int)previousMoves.get(currentIndex)[3];
            PieceInfo p = (PieceInfo)previousMoves.get(currentIndex)[0];
            p.setPiecePosition(x, y);
            p.getPieceLabel().setLocation(x*100, y*100);
            System.out.println("Name : " + p.getName());
            System.out.println("Color : " + p.getColor());
            System.out.println("x : " + x);
            System.out.println("y : " + y);
            firstLeft = false;
            firstRight = true;
            if (checkXOutOfBound == 9){
                moveLeft();
            }
        }
        System.out.println("Current index : " + currentIndex);
    }

    public void moveRight(){
        if (currentIndex+1 <= previousMoves.size() && currentIndex+1 >= 0){
            if (currentIndex+1 != previousMoves.size() && !firstRight){
                currentIndex += 1;
            }
            int x = (int)previousMoves.get(currentIndex)[3];
            int y = (int)previousMoves.get(currentIndex)[4];
            int checkXOutOfBound = 0;
            if (currentIndex+1 < previousMoves.size()){
                checkXOutOfBound =  (int)previousMoves.get(currentIndex+1)[3];
            }
            PieceInfo p = (PieceInfo)previousMoves.get(currentIndex)[0];
            p.setPiecePosition(x, y);
            p.getPieceLabel().setLocation(x*100, y*100);
            System.out.println("Name : " + p.getName());
            System.out.println("Color : " + p.getColor());
            System.out.println("x : " + x);
            System.out.println("y : " + y);
            firstRight = false;
            firstLeft = true;
            if (checkXOutOfBound == 9){
                moveRight();
            }
        }
        System.out.println("Current index : " + currentIndex);
    }

    public void getAllPiecePos(){
        PieceInfo p = pieceInteraction.pieceInfos[0][1];
        int countP = 0;
        for (int countDown = previousMoves.size()-1; countDown >= 0; countDown--){
            PieceInfo pTest = (PieceInfo) previousMoves.get(countDown)[0];
            if (pTest.equals(p)){
                countP++;
            }
        }
        int [][] posXandY = new int[countP][2];
        int countTemp = 0;
        for (int countDown = 0; countDown <= previousMoves.size()-1; countDown++){
            PieceInfo pTest = (PieceInfo) previousMoves.get(countDown)[0];
            if (pTest.equals(p)){
                int x = (int)previousMoves.get(countDown)[1];
                int y = (int)previousMoves.get(countDown)[2];
                posXandY[countTemp][0] = x;
                posXandY[countTemp][1] = y;
                countTemp++;
            }
        }
        System.out.println(Arrays.deepToString(posXandY));
    }

    public void printAllMoves(){
        Object [][] obj = new Object[previousMoves.size()][6];
        int count = 0;
        for (Object[] o : previousMoves){
            obj[count][0] = ((PieceInfo) o[0]).getName();
            obj[count][1] = ((PieceInfo) o[0]).getColor();
            obj[count][2] = o[1];
            obj[count][3] = o[2];
            obj[count][4] = o[3];
            obj[count][5] = o[4];
            count++;
        }
        for (Object[] o : obj){
            System.out.println("("+o[0]+", " + o[1] + ") : (" + o[2] + ", " + o[3] + ") -> (" + o[4] + ", " + o[5] + ")");
        }
    }

}
