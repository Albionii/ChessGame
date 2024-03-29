import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChessClient {
    public Socket socket;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public boolean alternatingBoolean;

    public Menu m;
    public char playerColor;

    public ChessClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Player has been added!");
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            move();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move(){
        ChessInfo chessInfo = new ChessInfo();
        try {
            playerColor = (char)inputStream.readObject();
            PieceInteraction.whiteOrBlackTurn = playerColor;
            alternatingBoolean = playerColor != 'W';
            Piece.didPieceMove = alternatingBoolean;
        }catch (Exception e) {
            e.printStackTrace();
        }
        m = new Menu(playerColor);
        while (socket.isConnected()){
            try {
                if (alternatingBoolean){
                    System.out.println("waiting...");
                    chessInfo = (ChessInfo) inputStream.readObject();
                    System.out.println("My turn now!");
                    int xPos = chessInfo.x;
                    int yPos = chessInfo.y;
                    int u = chessInfo.u;
                    int v = chessInfo.v;
                    if (ChessInfo.didPiecePromote){
                        chessInfo.updateOpponentPieceToPromotion(PieceInteraction.pieces[u][v]);
                    }
                    PieceInteraction.pieces[u][v].moveForOpponent(7-xPos, 7-yPos);
                    Piece.didPieceMove = false;
                    alternatingBoolean = false;
                }


                if (Piece.didPieceMove && PieceInteraction.lastPieceMoved != null){
                    alternatingBoolean = true;
                    chessInfo.u = PieceInteraction.lastU;
                    chessInfo.v = PieceInteraction.lastV;
                    chessInfo.x = PieceInteraction.pieces[chessInfo.u][chessInfo.v].xPos;
                    chessInfo.y = PieceInteraction.pieces[chessInfo.u][chessInfo.v].yPos;
                    outputStream.writeObject(chessInfo);
                    PieceInteraction.lastPieceMoved = null;
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int port = 1234;
        String host = "localhost";
        new ChessClient(host, port);
    }
}
