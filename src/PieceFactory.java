public class PieceFactory {
    public static Piece createPiece(PieceInfo pieceInfo) {
        switch (pieceInfo.getName()) {
            case "P":
                return new Pawn(pieceInfo);
//            case "R":
//                return new Rook(pieceInfo);
        }
        return null;
    }
}
