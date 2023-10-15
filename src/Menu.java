import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    public JFrame startmMenu;
    public JButton startButton;

    public void draw(){
        startmMenu = new JFrame("Start");
        startmMenu.setSize(300, 500);
        startmMenu.setLocationRelativeTo(null);
        startmMenu.setLayout(null);
        startButton = new JButton("Click To Start");
        startButton.setBounds(75, 235, 150, 30);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startmMenu.add(startButton);
        startmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startmMenu.setVisible(true);
    }

//    public static void main(String[] args) {
//        Menu m = new Menu();
//        m.draw();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startmMenu.dispose();
            new Game();
        }
    }
}
