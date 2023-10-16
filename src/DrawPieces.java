import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawPieces {
    private JLabel boardLabel;
    private BufferedImage[][] pieceImages = new BufferedImage[4][8];
    private JLabel [][] pieceLabels = new JLabel[4][8];
    private PieceInfo[][] pieceInfos = new PieceInfo[4][8];

    public char playerColor;

    public DrawPieces(JLabel boardLabel, char playerColor) {
        this.playerColor = playerColor;
        this.boardLabel = boardLabel;
        draw();
        PieceInteraction pI = new PieceInteraction(pieceInfos, pieceLabels);
        new MovePieces(pieceInfos, boardLabel, pI);

    }

    public void draw() {
        try {

            //Black Pieces
            pieceImages[0][0] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_rdt45.svg.png"));
            pieceImages[0][1] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_ndt45.svg.png"));
            pieceImages[0][2] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_bdt45.svg.png"));
            pieceImages[0][3] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_qdt45.svg.png"));
            pieceImages[0][4] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_kdt45.svg.png"));
            pieceImages[0][5] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_bdt45.svg.png"));
            pieceImages[0][6] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_ndt45.svg.png"));
            pieceImages[0][7] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_rdt45.svg.png"));
            pieceImages[1][0] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][1] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][2] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][3] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][4] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][5] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][6] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));
            pieceImages[1][7] = ImageIO.read(getClass().getResource("/Pieces/Black/Chess_pdt45.svg.png"));


            //White Pieces

            pieceImages[2][0] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_rlt45.svg.png"));
            pieceImages[2][1] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_nlt45.svg.png"));
            pieceImages[2][2] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_blt45.svg.png"));
            pieceImages[2][3] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_qlt45.svg.png"));
            pieceImages[2][4] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_klt45.svg.png"));
            pieceImages[2][5] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_blt45.svg.png"));
            pieceImages[2][6] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_nlt45.svg.png"));
            pieceImages[2][7] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_rlt45.svg.png"));
            pieceImages[3][0] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][1] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][2] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][3] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][4] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][5] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][6] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));
            pieceImages[3][7] = ImageIO.read(getClass().getResource("/Pieces/White/Chess_plt45.svg.png"));

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 8; j++) {
                    if (playerColor == 'W'){
                        Image image = pieceImages[i][j].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        pieceLabels[i][j] = new JLabel(new ImageIcon(image));
                        if (i < 2) {
                            pieceLabels[i][j].setBounds(j * 100, i * 100, 100, 100);
                            pieceInfos[i][j] = new PieceInfo()
                                    .setColor('B')
                                    .addLabel(pieceLabels[i][j])
                                    .setPiecePosition(j ,i);
                        }
                        else{
                            pieceLabels[i][j].setBounds(j * 100, (9 - i) * 100, 100, 100);
                            pieceInfos[i][j] = new PieceInfo()
                                    .setColor('W')
                                    .addLabel(pieceLabels[i][j])
                                    .setPiecePosition(j, 9-i);
                        }

                        boardLabel.add(pieceLabels[i][j]);
                    }else if (playerColor == 'B'){
                        Image image = pieceImages[i][7-j].getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        pieceLabels[i][7-j] = new JLabel(new ImageIcon(image));
                        if (i < 2) {
                            pieceLabels[i][7-j].setBounds(j * 100, (7-i) * 100, 100, 100);
                            pieceInfos[i][7-j] = new PieceInfo()
                                    .setColor('B')
                                    .addLabel(pieceLabels[i][7-j])
                                    .setPiecePosition(j ,7-i);
                        }
                        else {
                            pieceLabels[i][7-j].setBounds(j * 100, (i-2) * 100, 100, 100);
                            pieceInfos[i][7-j] = new PieceInfo()
                                    .setColor('W')
                                    .addLabel(pieceLabels[i][7-j])
                                    .setPiecePosition(j, i-2);
                        }

                        boardLabel.add(pieceLabels[i][7-j]);
                    }

                 }
            }
            nameThePieces();
            promotionPieces();
            pieceImages = null;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nameThePieces() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1 || i == 3) {
                    pieceInfos[i][j].addPieceInicial("P");
                }
            }
        }
        pieceInfos[0][0].addPieceInicial("R");
        pieceInfos[2][0].addPieceInicial("R");
        pieceInfos[0][1].addPieceInicial("N");
        pieceInfos[2][1].addPieceInicial("N");
        pieceInfos[0][2].addPieceInicial("B");
        pieceInfos[2][2].addPieceInicial("B");
        pieceInfos[0][3].addPieceInicial("Q");
        pieceInfos[2][3].addPieceInicial("Q");
        pieceInfos[0][4].addPieceInicial("K");
        pieceInfos[2][4].addPieceInicial("K");
        pieceInfos[0][5].addPieceInicial("B");
        pieceInfos[2][5].addPieceInicial("B");
        pieceInfos[0][6].addPieceInicial("N");
        pieceInfos[2][6].addPieceInicial("N");
        pieceInfos[0][7].addPieceInicial("R");
        pieceInfos[2][7].addPieceInicial("R");
    }

    public void promotionPieces(){
        BufferedImage [][] promotePieces = new BufferedImage[4][2];

        promotePieces[0][0] = pieceImages[2][3];
        promotePieces[1][0] = pieceImages[2][0];
        promotePieces[2][0] = pieceImages[2][1];
        promotePieces[3][0] = pieceImages[2][2];

        promotePieces[0][1] = pieceImages[0][3];
        promotePieces[1][1] = pieceImages[0][0];
        promotePieces[2][1] = pieceImages[0][1];
        promotePieces[3][1] = pieceImages[0][2];
        Promote.pieceImages = promotePieces;
        Promote.labels = pieceLabels;
    }
}
