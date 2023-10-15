import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChessClient {
    public Socket socket;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;

    public boolean broWhatNameToPutIt = false;

    public ChessClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Player has been added!");
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Menu m = new Menu();
            m.draw();
            move();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move(){
        ChessInfo chessInfo = new ChessInfo();
        while (socket.isConnected()){
            try {
                if (broWhatNameToPutIt){
                    System.out.println("waiting... c1");
                    chessInfo = (ChessInfo) inputStream.readObject();
                    System.out.println("My turn now!");
                    int xPos = chessInfo.piece.pieceInfo.getLastX();
                    int yPos = chessInfo.piece.pieceInfo.getLastY();
                    int u = chessInfo.u;
                    int v = chessInfo.v;
                    PieceInteraction.pieces[u][v].move(xPos, yPos);
                    Piece.didPieceMove = false;
                    broWhatNameToPutIt = false;
                }


                if (Piece.didPieceMove && PieceInteraction.lastPieceMoved != null){
                    System.out.println("Po bon ??");
                    broWhatNameToPutIt = true;
                    chessInfo.u = PieceInteraction.lastU;
                    chessInfo.v = PieceInteraction.lastV;
                    chessInfo.setPiece(PieceInteraction.lastPieceMoved);
                    outputStream.writeObject(chessInfo);
//                    Piece.didPieceMove = false;
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
