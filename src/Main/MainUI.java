package Main;

import AltaUltimate.Ultimate4x4;
import Classic.Classic3x3;
import TimeMode.Ultimate5x5;
import Ultimate.Ultimate3x3;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards;
    private ImageIcon icon;
    private FrontPage frontPage;
    private StartingUI menuPanel;
    private Classic3x3 gamePanel0;
    private Ultimate3x3 gamePanel;
    private Ultimate4x4 gamePanel2;
    private Ultimate5x5 gamePanel3;

    public MainUI() {
        icon = new ImageIcon(getClass().getResource("tic-tac-toe.png"));
        this.setIconImage(icon.getImage());

        setTitle("Ultimate Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();//layout for swithching screens
        cards = new JPanel(cardLayout);

//        "this" to call back
        frontPage= new FrontPage(this);
         menuPanel = new StartingUI(this);

        gamePanel0 = new Classic3x3(this);
        gamePanel = new Ultimate3x3(this);
        gamePanel2 = new Ultimate4x4(this);
        gamePanel3 = new Ultimate5x5(this);

//        each panel to the cardlayour container with key name
        cards.add(frontPage, "Front");
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel0, "Game0");
        cards.add(gamePanel, "Game1");
        cards.add(gamePanel2, "Game2");
        cards.add(gamePanel3, "Game3");


        setContentPane(cards);
        showFrontPage();
    }

    public void showFrontPage() {
        SoundManager.playBackgroundMusic("ambient.wav");
        cardLayout.show(cards, "Front");
    }

    public void showMenu() {
        cardLayout.show(cards, "Menu");
    }

    public  void showGame0() {
        cardLayout.show(cards, "Game0");
    }

    public void showGame() {
        cardLayout.show(cards, "Game1");
    }

    public  void showGame2() {
        cardLayout.show(cards, "Game2");
    }

    public void showGame3() {
        gamePanel3.startSession();
        cardLayout.show(cards,
                "Game3");
    }


    //    getters to set player name in starting ui
    public  Classic3x3 getGamePanel0() {
        return gamePanel0;
    }
    public Ultimate3x3 getGamePanel() {
        return gamePanel;
    }

    public Ultimate4x4 getGamePanel2() {return gamePanel2;}
    public Ultimate5x5 getGamePanel3() {return gamePanel3;}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI frame = new MainUI();
            frame.setVisible(true);
        });
    }
}
