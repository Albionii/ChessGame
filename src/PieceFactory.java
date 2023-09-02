public class PieceFactory {
    public static Piece createPiece(PieceInfo pieceInfo) {
        return switch (pieceInfo.getName()) {
            case "P" -> new Pawn(pieceInfo);
            case "R" -> new Rook(pieceInfo);
            case "N" -> new Knight(pieceInfo);
            case "B" -> new Bishop(pieceInfo);
            case "Q" -> new Queen(pieceInfo);
            case "K" -> new King(pieceInfo);
            default -> null;
        };
    }
}
