import java.util.ArrayList;

public class ReturnMoves {
    Object[] objects;
    public ArrayList<Object[]> previousMoves = new ArrayList<>();
    public int currentIndex;

    public void saveMove(PieceInfo pieceInfo, int x, int y){
        objects = new Object[]{pieceInfo, x, y};

        previousMoves.add(objects);
        currentIndex = previousMoves.size();
    }

    public ArrayList<Object[]> getPreviousMoves(){
        return previousMoves;
    }

    public void changePosition(int num){
        if (currentIndex+num < previousMoves.size() && currentIndex+num >= 0){
            currentIndex = currentIndex + num;
            int x = (int)previousMoves.get(currentIndex)[1];
            int y = (int)previousMoves.get(currentIndex)[2];
            PieceInfo p = (PieceInfo) previousMoves.get(currentIndex)[0];
            p.setPiecePosition(x, y);
            p.getPieceLabel().setLocation(x*100, y*100);
            System.out.println("Name : " + p.getName());
            System.out.println("Color : " + p.getColor());
            System.out.println("x : " + x);
            System.out.println("y : " + y);

        }
        System.out.println("Current index : " + currentIndex);
    }

}
