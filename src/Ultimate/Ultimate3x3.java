package Ultimate;

import Main.MainUI;
import Main.WinnerDialog;
import sound.SoundManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ultimate3x3 extends JPanel {
    private GameEngine3x3 gameEngine1;

    private JButton[][][] buttons;
    private JPanel[] smallBoards;
    private JLabel[] boardWinnerOverlay;

    private JLabel xName;
    private JLabel oName;

    private JPanel playerXBox;
    private JPanel playerOBox;

    private WinnerDialog winnerDialog;


    public Ultimate3x3(MainUI parent) {

        Color BACKGROUND = new Color(121, 71, 31);
        Color PANEL_BG = new Color(88, 51, 22);
        Color CARD_BG = new Color(102, 62, 30);
        Color ACCENT = new Color(195, 152, 63);
        Color TEXT = new Color(240, 215, 170);
        Color TILE = new Color(224, 176, 126);

        setBackground(BACKGROUND);
        setLayout(new OverlayLayout(this));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        gameEngine1 = new GameEngine3x3();

        buttons = new JButton[9][3][3];
        smallBoards = new JPanel[9];
        boardWinnerOverlay = new JLabel[9];

        JLabel titleLabel = new JLabel("ULTIMATE 3x3");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 34));
        titleLabel.setForeground(TEXT);

        contentPane.add(titleLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 6)));

        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        playerPanel.setOpaque(false);

        playerXBox = new JPanel();
        playerXBox.setPreferredSize(new Dimension(120, 90));
        playerXBox.setBackground(new Color(153, 96, 48));
        playerXBox.setLayout(new BoxLayout(playerXBox, BoxLayout.Y_AXIS));
        playerXBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        xName = new JLabel(gameEngine1.getPlayerXName());
        xName.setAlignmentX(Component.CENTER_ALIGNMENT);
        xName.setFont(new Font("SansSerif", Font.BOLD, 18));
        xName.setForeground(Color.WHITE);

        JLabel xSymbol = new JLabel("X");
        xSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        xSymbol.setFont(new Font("SansSerif", Font.PLAIN, 24));
        xSymbol.setForeground(Color.WHITE);

        playerXBox.add(Box.createVerticalGlue());
        playerXBox.add(xName);
        playerXBox.add(Box.createRigidArea(new Dimension(0, 10)));
        playerXBox.add(xSymbol);
        playerXBox.add(Box.createVerticalGlue());

        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        vsLabel.setForeground(new Color(220, 190, 140));

        playerOBox = new JPanel();
        playerOBox.setPreferredSize(new Dimension(120, 90));
        playerOBox.setBackground(new Color(153, 96, 48));
        playerOBox.setLayout(new BoxLayout(playerOBox, BoxLayout.Y_AXIS));
        playerOBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        oName = new JLabel(gameEngine1.getPlayerOName());
        oName.setAlignmentX(Component.CENTER_ALIGNMENT);
        oName.setFont(new Font("SansSerif", Font.BOLD, 18));
        oName.setForeground(Color.WHITE);

        JLabel oSymbol = new JLabel("O");
        oSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        oSymbol.setFont(new Font("SansSerif", Font.PLAIN, 24));
        oSymbol.setForeground(Color.WHITE);

        playerOBox.add(Box.createVerticalGlue());
        playerOBox.add(oName);
        playerOBox.add(Box.createRigidArea(new Dimension(0, 10)));
        playerOBox.add(oSymbol);
        playerOBox.add(Box.createVerticalGlue());

        playerPanel.add(playerXBox);
        playerPanel.add(vsLabel);
        playerPanel.add(playerOBox);

        contentPane.add(playerPanel);


        JPanel board = new JPanel(new FlowLayout(FlowLayout.CENTER));
        board.setOpaque(false);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 7, 7));
        boardPanel.setBackground(new Color(45, 25, 15));
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int b = 0; b < 9; b++) {
            JPanel smallBoard = new JPanel();
            smallBoard.setLayout(new OverlayLayout(smallBoard));
            smallBoard.setBackground(PANEL_BG);
            smallBoard.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
            smallBoards[b] = smallBoard;
            int boardIndex = b;

            JPanel tilePanel = new JPanel(new GridLayout(3, 3, 2, 2));
            tilePanel.setBackground(PANEL_BG);

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    JButton btn = new JButton();
                    btn.setPreferredSize(new Dimension(60, 40));
                    btn.setBackground(TILE);
                    btn.setFont(new Font("Arial", Font.BOLD, 32));
                    btn.setForeground(Color.BLACK);
                    btn.setOpaque(true);
                    btn.setBorderPainted(false);
                    btn.setFocusPainted(false);
                    btn.setContentAreaFilled(true);

                    buttons[b][r][c] = btn;
                    int row = r;
                    int col = c;

                    btn.addActionListener(e -> {

                        if (!smallBoards[boardIndex].isEnabled()) return;


                        if (gameEngine1.makeMove(boardIndex, row, col)) {

                            SoundManager.playSound("buttonclick.wav");

                            refreshBoard();
                            updateBoardUI();
                            highlightCurrentPlayer();

                            if (gameEngine1.isGameOver()) {
                                showWinScreen();
                            }
                        }
                    });

                    tilePanel.add(btn);

                }
            }

            // Create overlay for small board winner
            JLabel overlay = new JLabel("");
            overlay.setHorizontalAlignment(SwingConstants.CENTER);
            overlay.setVerticalAlignment(SwingConstants.CENTER);
            overlay.setFont(new Font("Arial", Font.BOLD, 100));
            overlay.setAlignmentX(0.5f);
            overlay.setAlignmentY(0.5f);
            overlay.setForeground(new Color(255, 255, 255,220));
            overlay.setOpaque(false);
            overlay.setVisible(false);

            boardWinnerOverlay[b] = overlay;

            smallBoard.add(overlay);
            smallBoard.add(tilePanel);

            boardPanel.add(smallBoard);

        }
        board.add(boardPanel);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        bottomPanel.setOpaque(false);

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

        });


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
        contentPane.add(Box.createRigidArea(new Dimension(0, 14)));

        contentPane.add(bottomPanel);

        add(contentPane);

        refreshBoard();
        updateBoardUI();
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

    }

    private void showWinScreen() {

        char winner = gameEngine1.getWinner();
        String title, message;

        if (winner == 'D') {
            title = "Draw";
            message = "Nobody Wins";
        } else if (winner == 'X') {
            title = "Winner";
            message = gameEngine1.getPlayerXName() + " [X] Wins!";
        } else {
            title = "Winner";
            message = gameEngine1.getPlayerOName() + " [O] Wins!";
        }

        winnerDialog.showResult(title, message);
    }

    private void hideWinScreen() {

        winnerDialog.setVisible(false);
    }


    private void refreshBoard() {

        for (int b = 0; b < 9; b++) {

            for (int r = 0; r < 3; r++) {

                for (int c = 0; c < 3; c++) {

                    char value =
                            gameEngine1.getCell(b, r, c);

                    buttons[b][r][c].setText(
                            value == '-' ? "" :
                                    String.valueOf(value)
                    );
                }
            }
        }
        highlightCurrentPlayer();
    }

    private void highlightCurrentPlayer() {

        if (gameEngine1.getCurrentPlayer() == 'X') {

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

    public void setPlayerNames(String playerX, String playerO) {

        gameEngine1.setPlayerNames(playerX, playerO);

        xName.setText(gameEngine1.getPlayerXName());
        oName.setText(gameEngine1.getPlayerOName());
    }

    private void updateBoardUI() {

        int active = gameEngine1.getActiveBoard();

        for (int b = 0; b < 9; b++) {

            String winner = String.valueOf(gameEngine1.getBoardWinner(b));

            // CASE 1: BOARD FINISHED
            if (!winner.equals("-")) {

                smallBoards[b].setBorder(BorderFactory.createLineBorder(Color.BLACK,5));

                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        buttons[b][r][c].setEnabled(false);
                        buttons[b][r][c].setText("");
                    }
                }

                if (!boardWinnerOverlay[b].isVisible()) {
                    SoundManager.playSound("victory.wav");
                }

                boardWinnerOverlay[b].setVisible(true);

                if (winner.equals("X")) {
                    smallBoards[b].setBackground(new Color(180,50,50)); // red
                    boardWinnerOverlay[b].setText("X");
                    boardWinnerOverlay[b].setForeground(new Color(255, 60, 60, 180));
                }
                else if (winner.equals("O")) {
                    smallBoards[b].setBackground(new Color(50,90,200)); // blue
                    boardWinnerOverlay[b].setText("O");
                    boardWinnerOverlay[b].setForeground(new Color(60, 120, 255, 180));
                }
                else {
                    smallBoards[b].setBackground(new Color(120,120,120)); // draw
                    boardWinnerOverlay[b].setText("=");
                    boardWinnerOverlay[b].setForeground(new Color(150, 150, 150, 180));
                }
                continue;
            }

            // CASE 2: ACTIVE BOARD / not finished board
            boardWinnerOverlay[b].setVisible(false);
            boolean activeBoard =
                    (active == -1 || active == b);

            for(int r=0;r<3;r++) {
                for(int c=0;c<3;c++) {

                    if(gameEngine1.getBoardWinner(b) == '-') {

                        buttons[b][r][c].setEnabled(activeBoard);
                    }
                }
            }

            if(activeBoard) {

                smallBoards[b].setBorder(
                        BorderFactory.createLineBorder(
                                new Color(255,215,0),
                                4)
                );
                smallBoards[b].setBackground(new Color(120,80,40));
            }

            // CASE 3: INACTIVE BOARD
            else {

                smallBoards[b].setBorder(
                        BorderFactory.createLineBorder(new Color(100, 70, 40), 2)
                );
                smallBoards[b].setBackground(new Color(88,51,22));

            }
        }

        revalidate();
        repaint();
    }

    public void resetGame() {


        gameEngine1.reset();

        for (int b = 0; b < 9; b++) {

            boardWinnerOverlay[b].setVisible(false);
            boardWinnerOverlay[b].setText("");

            smallBoards[b].setEnabled(true);
            smallBoards[b].setBackground(new Color(88,51,22));

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {

                    buttons[b][r][c].setEnabled(true);
                    buttons[b][r][c].setVisible(true);
                    buttons[b][r][c].setText("");
                }
            }
        }

        refreshBoard();
        updateBoardUI();
        highlightCurrentPlayer();
        hideWinScreen();
    }
}