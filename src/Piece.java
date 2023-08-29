public abstract class Piece {
    public PieceInfo pieceInfo;
    public static PieceInteraction pieceInteraction;
    public int xPos, yPos;


    public Piece(PieceInfo pieceInfo) {
        this.pieceInfo = pieceInfo;
    }


    /**
     * Rikthen poziten e figures nese ndonje levizje nuk eshte legale.
     */
    public void restartPreviousMove(){
        pieceInfo.setPiecePosition(pieceInfo.getLastX(), pieceInfo.getLastY());
        pieceInfo.getPieceLabel().setLocation(pieceInfo.getLastX()*100, pieceInfo.getLastY()*100);
    }
    public void restartMove(int x, int y) {
        pieceInfo.setPiecePosition(x, y);
        pieceInfo.getPieceLabel().setLocation(x*100, y*100);
    }


    /**
     * Kur merret nje figure me ane te nje figure tjeter, ajo vendoset jashte dritares.
     * @param p figura qe do te "vritet".
     */
    public void pieceKilled(PieceInfo p) {
        pieceInteraction.findPieceInfoInArray(p).isPieceDead = true;
        p.setPiecePosition(9, 1);
        p.getPieceLabel().setLocation(900, 100);
    }

    /**
     * Ben levizjen e figures ne destinacionin e caktuar. Levizja behet vetem nese eshte e lejueshme.
     * @param xPosition eshte integer nga 0 deri ne 9.
     * @param yPosition eshte integer nga 0 deri ne 9.
     */
    public void move(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
        if (isLegalMove()){
            if (pieceInteraction.isThereAPiece(xPos, yPos)){
                if (pieceInteraction.findPieceInThatPosition(xPos, yPos).getColor() != pieceInfo.getColor()){
                    pieceTakes();
                }else {
                    restartPreviousMove();
                    return;
                }
            }
            updatePosition();

        }else {
            restartPreviousMove();
        }
    }



    /**
     * Ne poziten nga parametrat e metodes move(), kjo e mundeson levizjen e figures kur plotesohet kushti nese levizja eshte e lejuar.
     */
    public void updatePosition(){
        int tempX = pieceInfo.getLastX();
        int tempY = pieceInfo.getLastY();
        pieceInfo.getPieceLabel().setLocation(xPos*100, yPos*100);
        pieceInfo.setPiecePosition(xPos, yPos);
        if (kingChecked() && !pieceInteraction.kingGotChecked){ //? Ne qofte se nje figure sulmon mbretin e kthen nje variabel true
            pieceInteraction.kingGotChecked = true;
            pieceInteraction.pieceThatAttacked = this;
        }
        if (pieceInteraction.pieceThatAttacked != this && ifMovePreventsCheck() && (!this.pieceInfo.getName().equals("K"))) { //? Nese figura nuk e ndal sulmin, le te kthehet ne poziten fillestare.
            restartMove(tempX, tempY);
            return;
        }
        pieceInteraction.whiteOrBlackTurn = pieceInfo.getColor() == 'W' ? 'B' : 'W';
    }

    /**
     * Testohet nese levizja qe po ben lojtari eshte e lejueshme apo jo.
     * @return true nese levizja eshte e mundun.
     * false nese levizja nuk eshte e mundun.
     */
    public boolean isLegalMove() {return
            isItYourTurn() &&
            !isAnyPieceOnTheWay(xPos, yPos) &&
            isMoveInPieceScope();
    }

    /**
     * E pamundeson qe te behen dy levizje e me shume nga i njejti lojtar.
     * @return true nese eshte rendi i atij lojtari akoma.
     * false nese nuk eshte.
     */
    public boolean isItYourTurn() {
        return pieceInfo.getColor() == pieceInteraction.whiteOrBlackTurn;
    }

    /**
     * Kur figuren e leviz ne te njejtin vend atehere nuk duhet qe te te humb rendi.
     * @return true nese ke levizur ne te njejten pozite qe ke me heret.
     * false nese eshte ne nje pozite te re.
     */
    public boolean ifPieceDidNotMove(){return pieceInfo.getLastX() == xPos && pieceInfo.getLastY() == yPos;}

    /**
     * Shikon nese mbreti eshte ne "scope" e nje figure.
     * @param xKing pozita x e mbretit, nga 0 - 9.
     * @param yKing pozita y e mbretit, nga 0 - 9.
     * @return true nese mbreti gjendet brenda "scope" te figures.
     * false nese nuk gjendet ne "scope" te figures.
     */
    public boolean isKingInPieceScope(int xKing, int yKing) {return false;}

    /**
     * Kjo metode shikon nese mbreti gjendet nen shah apo jo.
     * @return true nese eshte nen shah.
     * false nese nuk eshte nen shah.
     */
    public boolean kingChecked() {
        int x = pieceInteraction.getPieceInfos()[pieceInfo.getColor()=='B' ? 2:0][4].getLastX();
        int y = pieceInteraction.getPieceInfos()[pieceInfo.getColor()=='B' ? 2:0][4].getLastY();
        return isKingInPieceScope(x,y) && !isAnyPieceOnTheWay(x,y);
    }

    /**
     * Kur nje figure eshte duke sulmuar mbretin kundershtar, dhe nje figure e pengon sulmin.
     * @return true nese figura qe levize e ndal sulmin e kundershtarit.
     * false nese figura nuk e ndal sulmin e kundershtarit.
     */
    public boolean ifMovePreventsCheck(){
        return pieceInteraction.kingGotChecked && pieceInteraction.pieceThatAttacked.kingChecked();
    }

    public void setXandY(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }



    //* Metodat abstrakte

    /**
     * Para se me marre nje figure e shikon nese eshte e mundur te merret ajo, ne qofte se po thirret metoda piecekilled().
     */
    public abstract void pieceTakes();

    /**
     * Metoda shikon nese mes figures dhe pozites kur deshirojme ta levizim ndodhet ndonje figure qe e pamundeson levizjen.
     * @param x pozita e x ku deshirojme ta levizim, nga 0 - 9.
     * @param y pozita e y ku deshirojme ta levizim, nga 0 - 9.
     * @return true nese ndonje figure e bllokon rrugen.
     * false ne qofte se jo.
     */
    public abstract boolean isAnyPieceOnTheWay(int x, int y);

    /**
     * Shikon tere "scope" te figures dhe nese levizja e deshiruar gjendet brenda asaj scope.
     * @return true nese levizja gjendet ne "scope".
     * false nese levizja nuk gjendet ne "scope".
     */
    public abstract boolean isMoveInPieceScope();


}
