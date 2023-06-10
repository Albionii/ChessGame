public class PieceFactory {
    public static Piece createPiece(PieceInfo pieceInfo) {
        switch (pieceInfo.getName()) {
            case "P":
                return new Pawn(pieceInfo);
            case "R":
                return new Rook(pieceInfo);
            case "N":
                return new Knight(pieceInfo);
            case "B":
                return new Bishop(pieceInfo);
            case "Q":
                return new Queen(pieceInfo);
            case "K":
                return new King(pieceInfo);
        }
        return null;
    }
}
