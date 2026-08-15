package AltaUltimate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Main.MainUI;
import Main.WinnerDialog;
import sound.SoundManager;

public class Ultimate4x4 extends JPanel {
    private GameEngine4x4 gameEngine2;

    private JButton[][][] buttons;
    private JPanel[] smallBoards;
    private JLabel[] boardWinnerOverlay;

    private JLabel xName;
    private JLabel oName;
    private JLabel VSLabel;

    private JPanel playerXBox;
    private JPanel playerOBox;


  private WinnerDialog winnerDialog;

    public Ultimate4x4(MainUI parent) {

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

        gameEngine2 = new GameEngine4x4();

        buttons = new JButton[16][4][4];
        smallBoards = new JPanel[16];
        boardWinnerOverlay = new JLabel[16];

        JLabel titleLabel = new JLabel("ULTIMATE 4x4");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT);

        contentPane.add(titleLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 4)));


        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        playerPanel.setOpaque(false);

        playerXBox = new JPanel();
        playerXBox.setPreferredSize(new Dimension(430,55));
        playerXBox.setBackground(new Color(153, 96, 48));
        playerXBox.setLayout(new BorderLayout());
        playerXBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        xName = new JLabel(gameEngine2.getPlayerXName());
        xName.setAlignmentX(Component.CENTER_ALIGNMENT);
        xName.setFont(new Font("SansSerif", Font.BOLD, 16));
        xName.setForeground(Color.WHITE);

        JLabel xSymbol = new JLabel("X  ");
        xSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        xSymbol.setFont(new Font("SansSerif", Font.PLAIN, 18));
        xSymbol.setForeground(Color.WHITE);


        playerXBox.add(xSymbol, BorderLayout.WEST);
        playerXBox.add(xName, BorderLayout.CENTER);


        VSLabel = new JLabel("VS");
        VSLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        VSLabel.setFont(new Font("Arial", Font.BOLD, 20));
        VSLabel.setForeground(Color.WHITE);

        playerOBox = new JPanel();
        playerOBox.setPreferredSize(new Dimension(430,55));
        playerOBox.setBackground(new Color(153, 96, 48));
        playerOBox.setLayout(new BorderLayout());
        playerOBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        oName = new JLabel(gameEngine2.getPlayerOName());
        oName.setAlignmentX(Component.CENTER_ALIGNMENT);
        oName.setFont(new Font("SansSerif", Font.BOLD, 16));
        oName.setForeground(Color.WHITE);

        JLabel oSymbol = new JLabel("O  ");
        oSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        oSymbol.setFont(new Font("SansSerif", Font.PLAIN, 18));
        oSymbol.setForeground(Color.WHITE);



        playerOBox.add(oSymbol, BorderLayout.WEST);
        playerOBox.add(oName, BorderLayout.CENTER);

        playerPanel.add(playerXBox, BorderLayout.WEST);
        playerPanel.add(VSLabel, BorderLayout.CENTER);
        playerPanel.add(playerOBox, BorderLayout.EAST);

        contentPane.add(playerPanel);


        JPanel board = new JPanel(new FlowLayout(FlowLayout.CENTER));
        board.setOpaque(false);

        JPanel boardPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        boardPanel.setBackground(new Color(45, 25, 15));
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int b = 0; b < 16; b++) {
            JPanel smallBoard = new JPanel();
            smallBoard.setLayout(new OverlayLayout(smallBoard));
            smallBoard.setBackground(PANEL_BG);
            smallBoard.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
            smallBoards[b] = smallBoard;
            int boardIndex = b;

            JPanel tilePanel = new JPanel(new GridLayout(4, 4, 2, 2));
            tilePanel.setBackground(PANEL_BG);

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    JButton btn = new JButton();
                    btn.setPreferredSize(new Dimension(46,26));
                    btn.setBackground(TILE);
                    btn.setFont(new Font("Arial", Font.BOLD, 12));
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
                        SoundManager.playSound("buttonclick.wav");

                        if (gameEngine2.makeMove(boardIndex, row, col)) {

                            refreshBoard();
                            updateBoardUI();
                            highlightCurrentPlayer();

                            if (gameEngine2.isGameOver()) {
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
            overlay.setFont(new Font("Arial", Font.BOLD, 80));
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
        btn1.setPreferredSize(new Dimension(120, 30));
        btn1.setBackground(CARD_BG);
        btn1.setForeground(TEXT);
        btn1.setFont(new Font("Serif", Font.PLAIN, 14));
        btn1.setBorder(BorderFactory.createLineBorder(new Color(160, 110, 65), 1));
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(true);
        btn1.setOpaque(true);
        btn1.addActionListener(e -> {

            SoundManager.playSound("click.wav");
          resetGame();
        });


        JButton btn2 = new JButton("Back");
        btn2.setPreferredSize(new Dimension(120, 30));
        btn2.setBackground(CARD_BG);
        btn2.setForeground(TEXT);
        btn2.setFont(new Font("Serif", Font.PLAIN, 14));
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
        contentPane.add(Box.createRigidArea(new Dimension(0, 2)));
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

        char winner = gameEngine2.getWinner();
        String title, message;

        if (winner == 'D') {
            title = "Draw!";
            message = "Nobody wins!";
        } else if (winner == 'X') {
            title = "Winner!";
            message = gameEngine2.getPlayerXName() + " [X] Wins!";
        } else {
            title = "Winner!";
            message = gameEngine2.getPlayerOName() + " [O] Wins!";
        }

        winnerDialog.showResult(title, message);
    }

    private void hideWinScreen() {
        winnerDialog.setVisible(false);
    }

    private void refreshBoard() {

        for (int b = 0; b < 16; b++) {

            for (int r = 0; r < 4; r++) {

                for (int c = 0; c < 4; c++) {

                    char value =
                            gameEngine2.getCell(b, r, c);

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

        if (gameEngine2.getCurrentPlayer() == 'X') {

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

        gameEngine2.setPlayerNames(playerX, playerO);

        xName.setText(gameEngine2.getPlayerXName());
        oName.setText(gameEngine2.getPlayerOName());
    }

    private void updateBoardUI() {

        int active = gameEngine2.getActiveBoard();

        for (int b = 0; b < 16; b++) {

            String winner = String.valueOf(gameEngine2.getBoardWinner(b));

            // CASE 1: BOARD FINISHED
            if (!winner.equals("-")) {

                smallBoards[b].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        buttons[b][r][c].setEnabled(false);
                        buttons[b][r][c].setText("");
                    }
                }

                boolean alreadyShown = boardWinnerOverlay[b].isVisible();
                boardWinnerOverlay[b].setVisible(true);

                if (winner.equals("X")) {
//                    SoundManager.playSound("victory.wav");

                    smallBoards[b].setBackground(new Color(180,50,50)); // red
                    boardWinnerOverlay[b].setText("X");
                    boardWinnerOverlay[b].setForeground(new Color(255, 60, 60, 180));
                }
                else if (winner.equals("O")) {
//                    SoundManager.playSound("victory.wav");

                    smallBoards[b].setBackground(new Color(50,90,200)); // blue
                    boardWinnerOverlay[b].setText("O");
                    boardWinnerOverlay[b].setForeground(new Color(60, 120, 255, 180));
                }
                else {
                    smallBoards[b].setBackground(new Color(120,120,120)); // draw
                    boardWinnerOverlay[b].setText("-");
                    boardWinnerOverlay[b].setForeground(new Color(150, 150, 150, 180));
                }

                if(!alreadyShown) {
                    SoundManager.playSound("victory.wav");
                }

                continue;
            }

            // CASE 2: ACTIVE BOARD

            boardWinnerOverlay[b].setVisible(false);
            boolean activeBoard =
                    (active == -1 || active == b);

            for(int r=0;r<4;r++) {
                for(int c=0;c<4;c++) {

                    if(gameEngine2.getBoardWinner(b) == '-') {

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
        gameEngine2.reset();

        for (int b = 0; b < 16; b++) {

            boardWinnerOverlay[b].setVisible(false);
            boardWinnerOverlay[b].setText("");

            smallBoards[b].setEnabled(true);
            smallBoards[b].setBackground(new Color(88,51,22));

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {

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

