import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {
    public ServerSocket serverSocket;
    public Socket player1;
    public Socket player2;
    public ObjectOutputStream outputStreamP1;
    public ObjectOutputStream outputStreamP2;
    public ObjectInputStream inputStreamP1;
    public ObjectInputStream inputStreamP2;

    public static boolean isFirstMove = true;
    public int numberOfPlayersConnected;

    public boolean isPlayer1White;
    public char player1Color, player2Color;


    public ChessServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server waiting for connections ...");
            while (numberOfPlayersConnected < 2){
                Socket socket = serverSocket.accept();
                if (numberOfPlayersConnected == 0){
                    player1 = socket;
                    outputStreamP1 = new ObjectOutputStream(player1.getOutputStream());
                    inputStreamP1 = new ObjectInputStream(player1.getInputStream());
                }
                else {
                    player2 = socket;
                    outputStreamP2 = new ObjectOutputStream(player2.getOutputStream());
                    inputStreamP2 = new ObjectInputStream(player2.getInputStream());
                }
                System.out.println("One client has been connected!");
                numberOfPlayersConnected++;
            }
            decideColorOfPlayers();
            startServer();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void decideColorOfPlayers(){
        isPlayer1White = Math.random() < 0.5;
        player1Color = isPlayer1White ? 'W' : 'B';
        player2Color = !isPlayer1White ? 'W' : 'B';
//        isPlayer1White = true;
    }

    public void startServer(){
        ChessInfo chessInfoWhite;
        ChessInfo chessInfoBlack;

        try {
            outputStreamP1.writeObject(player1Color);
            outputStreamP2.writeObject(player2Color);

        }catch (Exception e) {
            e.printStackTrace();
        }

        while (player1.isConnected() && player2.isConnected()){
            try {
                if (isPlayer1White){
                    if (!isFirstMove){
                        System.out.print("Black1: ");
                        chessInfoBlack = (ChessInfo) inputStreamP2.readObject();
                        outputStreamP1.writeObject(chessInfoBlack);
                        System.out.println(chessInfoBlack.piece.pieceInfo.getName());
                    }
                    System.out.print("White1: ");
                    chessInfoWhite = (ChessInfo) inputStreamP1.readObject();
                    outputStreamP2.writeObject(chessInfoWhite);
                }
                else{
                    if (!isFirstMove){
                        System.out.print("Black2: ");
                        chessInfoBlack = (ChessInfo) inputStreamP1.readObject();
                        outputStreamP2.writeObject(chessInfoBlack);
                        System.out.println(chessInfoBlack.piece.pieceInfo.getName());
                    }
                    System.out.print("White2: ");
                    chessInfoWhite = (ChessInfo) inputStreamP2.readObject();
                    outputStreamP1.writeObject(chessInfoWhite);

                }
                isFirstMove = false;
                System.out.println(chessInfoWhite.piece.pieceInfo.getName());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





    public static void main(String[] args) {
        int port = 1234;
        new ChessServer(port);
    }
}
