import javax.swing.*;

public class PieceInfo {
    private char pieceColor;
    private JLabel pieceLabel;
    private String name;
    private int xPosition, yPosition;
    public int i, j;
    public boolean isPieceDead;


    public PieceInfo setColor(char color){
        pieceColor = color;
        return this;
    }

    public int getLastX() {
        return xPosition;
    }

    public int getLastY() {
        return yPosition;
    }

    public char getColor() {
        return pieceColor;
    }

    public PieceInfo addLabel(JLabel label) {
        pieceLabel = label;
        return this;
    }

    public JLabel getPieceLabel() {
        return pieceLabel;
    }

    public PieceInfo addPieceInicial(String n) {
        name = n;
        return this;
    }

    public String getName() {
        return name;
    }

    public PieceInfo setPiecePosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        return this;
    }

    public boolean equals(Object o){
        if(o instanceof PieceInfo) {
            PieceInfo p = (PieceInfo) o;
            if (p.pieceLabel.equals(pieceLabel)){
                return true;
            }
        }
        return false;
    }

    public void saveIandJ(int i, int j){
        this.i = i;
        this.j = j;
    }

}
