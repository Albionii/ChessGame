public class EnPassant {
    PieceInteraction pI;
    PieceInfo p;
    int xPos, yPos;

    public EnPassant(PieceInteraction pI, PieceInfo p, int xPos, int yPos) {
        this.pI = pI;
        this.p = p;
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
