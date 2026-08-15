package Classic;

import Main.MainUI;
import Main.WinnerDialog;
import sound.SoundManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Classic3x3 extends JPanel {

    private GameEngine gameEngine0;

    private JButton[][] buttons;

    private JLabel xName;
    private JLabel oName;

    private JPanel playerXBox;
    private JPanel playerOBox;

    private WinnerDialog winnerDialog;

    public Classic3x3(MainUI parent) {
        Color BACKGROUND = new Color(121, 71, 31);
        Color PANEL_BG = new Color(88, 51, 22);
        Color CARD_BG = new Color(102, 62, 30);
        Color TEXT = new Color(240, 215, 170);
        Color TILE = new Color(224, 176, 126);

//        main panel
        setBackground(BACKGROUND);
        setLayout(new OverlayLayout(this));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));


        gameEngine0 = new GameEngine();

        buttons = new JButton[3][3];

//        title ______
        JLabel titleLabel = new JLabel("CLASSIC TIC TAC TOE");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 34));
        titleLabel.setForeground(TEXT);

        contentPane.add(titleLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 6)));

//        contentPane.add(Box.createRigidArea(new Dimension(0, 18)));

//        player info_____
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        playerPanel.setOpaque(false);

//        player x box
        playerXBox = new JPanel();
        playerXBox.setPreferredSize(new Dimension(120, 90));
        playerXBox.setBackground(new Color(153,96,48));
        playerXBox.setLayout(new BoxLayout(playerXBox, BoxLayout.Y_AXIS));
        playerXBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194,137,74),2),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        xName = new JLabel(gameEngine0.getPlayerXName());
        xName.setAlignmentX(Component.CENTER_ALIGNMENT);
        xName.setFont(new Font("SansSerif", Font.BOLD, 18));
        xName.setForeground(Color.WHITE);

        JLabel xSymbol = new JLabel("X");
        xSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        xSymbol.setFont(new Font("SansSerif", Font.PLAIN, 24));
        xSymbol.setForeground(Color.WHITE);

        playerXBox.add(Box.createVerticalGlue());
        playerXBox.add(xName);
        playerXBox.add(Box.createRigidArea(new Dimension(0,10)));
        playerXBox.add(xSymbol);
        playerXBox.add(Box.createVerticalGlue());

//        vs
        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        vsLabel.setForeground(new Color(220,190,140));

//        Player O box
        playerOBox = new JPanel();
        playerOBox.setPreferredSize(new Dimension(120, 90));
        playerOBox.setBackground(new Color(153,96,48));
        playerOBox.setLayout(new BoxLayout(playerOBox, BoxLayout.Y_AXIS));
        playerOBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194,137,74), 2),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        oName = new JLabel(gameEngine0.getPlayerOName());
        oName.setAlignmentX(Component.CENTER_ALIGNMENT);
        oName.setFont(new Font("SansSerif", Font.BOLD, 18));
        oName.setForeground(Color.WHITE);

        JLabel oSymbol = new JLabel("O");
        oSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        oSymbol.setFont(new Font("SansSerif", Font.PLAIN, 24));
        oSymbol.setForeground(Color.WHITE);

        playerOBox.add(Box.createVerticalGlue());
        playerOBox.add(oName);
        playerOBox.add(Box.createRigidArea(new Dimension(0,10)));
        playerOBox.add(oSymbol);
        playerOBox.add(Box.createVerticalGlue());

        playerPanel.add(playerXBox);
        playerPanel.add(vsLabel);
        playerPanel.add(playerOBox);

        contentPane.add(playerPanel);


//        board area
        JPanel board = new JPanel(new FlowLayout(FlowLayout.CENTER));
        board.setOpaque(false);

        JPanel tilePanel = new JPanel(new GridLayout(3, 3, 2, 2));
        tilePanel.setBackground(PANEL_BG);


        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(150, 130));
                btn.setBackground(TILE);
                btn.setFont(new Font("Serif", Font.BOLD, 50));
                btn.setOpaque(true);
                btn.setBorderPainted(true);
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(true);

                buttons[i][j] = btn;
                int row = i;
                int col = j;

                btn.addActionListener(e -> {

//                    if (gameEngine0.isGameOver()) return;

                    if (gameEngine0.makeMove(row, col)) {
                        SoundManager.playSound("buttonclick.wav");

                        btn.setText(String.valueOf(gameEngine0.getCell(row, col)));

                        highlightCurrentPlayer();

                        if (gameEngine0.isGameOver()) {

                            freezeBoard();          // lock board
                            highlightWinner();      //  highlight
                            showWinScreen();        // dialog
                        }
                    }
                });
                tilePanel.add(btn);
            }
        }

        board.add(tilePanel);


//Bottom Button panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        bottomPanel.setOpaque(false);

//        reset all btn
        JButton btn1 = new JButton("Reset");
        btn1.setPreferredSize(new Dimension(120, 34));
        btn1.setBackground(CARD_BG);
        btn1.setForeground(TEXT);
        btn1.setFont(new Font("Serif", Font.PLAIN, 16));
        btn1.setBorder(BorderFactory.createLineBorder(new Color(160, 110, 65), 1));
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(true);
        btn1.setOpaque(true);

        btn1.addActionListener(e -> {

            SoundManager.playSound("click.wav");
                resetGame();

            winnerDialog.setVisible(false);

        });


//back btn
        JButton btn2 = new JButton("Back");
        btn2.setPreferredSize(new Dimension(120, 34));
        btn2.setBackground(CARD_BG);
        btn2.setForeground(TEXT);
        btn2.setFont(new Font("Serif", Font.PLAIN, 16));
        btn2.setBorder(BorderFactory.createLineBorder(new Color(160, 110, 65), 1));
        btn2.setFocusPainted(false);
        btn2.setContentAreaFilled(true);
        btn2.setOpaque(true);
        btn2.addActionListener(e -> {
            SoundManager.playSound("click.wav");
            resetGame();
            parent.showMenu();
            SoundManager.playBackgroundMusic("ambient.wav");
        });

        bottomPanel.add(btn1);
        bottomPanel.add(btn2);

        contentPane.add(board);
        contentPane.add(Box.createRigidArea(new Dimension(0,14)));

        contentPane.add(bottomPanel);

        add(contentPane);

        refreshBoard();
        updateBoard();
        highlightCurrentPlayer();

        winnerDialog = new WinnerDialog(parent, new WinnerDialog.DialogActions() {
            @Override
            public void onPlayAgain() {
                resetGame();
            }

            @Override
            public void onBackToMenu() {
                resetGame();
                parent.showMenu();
            }
        });

        SoundManager.stopBackgroundMusic();

    }

    //    freeze all btn when game end
    private void freezeBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }


private void showWinScreen() {

    char w = gameEngine0.getWinner();
    String title, message;

    if (w == 'D') {
        title = "Draw!!";
        message = "Nobody wins";
    } else if (w == 'X') {
        title = "Winner!!";
        message = gameEngine0.getPlayerXName() + " [X] Wins!";
    } else {
        title = "Winner!";
        message = gameEngine0.getPlayerOName() + " [O] Wins!";
    }

    winnerDialog.showResult(title, message);
}
    private void hideWinScreen(){
        if(winnerDialog !=null){
            winnerDialog.dispose();
        }
    }

    private void highlightWinner() {


        char p = gameEngine0.getWinner();

        if (p == '-' || p == 'D') return;

        Color WIN_COLOR = new Color(255, 215, 0);

        // rows
        for (int i = 0; i < 3; i++) {
            if (gameEngine0.getCell(i,0) == p &&
                    gameEngine0.getCell(i,1) == p &&
                    gameEngine0.getCell(i,2) == p) {

                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setBackground(WIN_COLOR);

                }
                return;
            }
        }

        // columns
        for (int j = 0; j < 3; j++) {
            if (gameEngine0.getCell(0,j) == p &&
                    gameEngine0.getCell(1,j) == p &&
                    gameEngine0.getCell(2,j) == p) {

                for (int i = 0; i < 3; i++) {
                    buttons[i][j].setBackground(WIN_COLOR);
                }
                return;
            }
        }

        // diagonal left to right
        if (gameEngine0.getCell(0,0) == p &&
                gameEngine0.getCell(1,1) == p &&
                gameEngine0.getCell(2,2) == p) {

            buttons[0][0].setBackground(WIN_COLOR);
            buttons[1][1].setBackground(WIN_COLOR);
            buttons[2][2].setBackground(WIN_COLOR);
            return;
        }
//diagonal right to left
        if (gameEngine0.getCell(0,2) == p &&
                gameEngine0.getCell(1,1) == p &&
                gameEngine0.getCell(2,0) == p) {

            buttons[0][2].setBackground(WIN_COLOR);
            buttons[1][1].setBackground(WIN_COLOR);
            buttons[2][0].setBackground(WIN_COLOR);

        }
    }


    private void refreshBoard(){

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                char value =
                        gameEngine0.getCell(i,j);

                buttons[i][j].setText(
                        value == '-' ? "" :
                                String.valueOf(value)
                );
            }
        }
        highlightCurrentPlayer();
    }
    private void highlightCurrentPlayer()
    {
        if (gameEngine0.getCurrentPlayer() == 'X') {

            playerXBox.setBackground(new Color(255, 215, 0));

            playerOBox.setBackground(
                    new Color(153, 96, 48)
            );
        } else {

            playerOBox.setBackground(new Color(255, 215, 0));

            playerXBox.setBackground(
                    new Color(153, 96, 48)
            );
        }
    }

    private void updateBoard() {

        if (!gameEngine0.isGameOver()) return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void setPlayerName(String playerX, String playerO) {

        gameEngine0.setPlayerNames(playerX, playerO);

        xName.setText(gameEngine0.getPlayerXName());
        oName.setText(gameEngine0.getPlayerOName());
    }

    public void resetGame() {


        gameEngine0.reset();
        Color TILE = new Color(224, 176, 126);


        for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {

                    buttons[r][c].setEnabled(true);
                    buttons[r][c].setVisible(true);
                    buttons[r][c].setText("");
                    buttons[r][c].setBackground(TILE);
                }
            }


        refreshBoard();
        highlightCurrentPlayer();
    }

}
