package TimeMode;

import Main.MainUI;
import Main.WinnerDialog;
import sound.SoundManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ultimate5x5 extends JPanel {
    private GameEngine5x5 gameEngine3;

    private JButton[][][] buttons;
    private JPanel[] smallBoards;
    private JLabel[] boardWinnerOverlay;

    private JLabel xName;
    private JLabel oName;

    private JPanel playerXBox;
    private JPanel playerOBox;

    private Timer gameTimer;
    private int timeRemaining = 120;

    private JLabel timerLabel;

    private JLabel xScoreLabel;
    private JLabel oScoreLabel;

    private WinnerDialog winnerDialog;

    public Ultimate5x5(MainUI parent) {

        gameEngine3 = new GameEngine5x5();

        buttons = new JButton[25][3][3];
        smallBoards = new JPanel[25];
        boardWinnerOverlay = new JLabel[25];

        Color BACKGROUND = new Color(121, 71, 31);
        Color PANEL_BG = new Color(88, 51, 22);
        Color CARD_BG = new Color(102, 62, 30);
        Color ACCENT = new Color(195, 152, 63);
        Color TEXT = new Color(240, 215, 170);
        Color TILE = new Color(224, 176, 126);

        setBackground(BACKGROUND);
        setLayout(new OverlayLayout(this));
        setBorder(new EmptyBorder(20, 20, 20, 20));


//        topPnael
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("ULTIMATE 5x5");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 34));
        titleLabel.setForeground(TEXT);

        contentPane.add(titleLabel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 6)));


//        Player
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

        xName = new JLabel(gameEngine3.getPlayerXName());
        xName.setAlignmentX(Component.CENTER_ALIGNMENT);
        xName.setFont(new Font("SansSerif", Font.BOLD, 16));
        xName.setForeground(Color.WHITE);

        JLabel xSymbol = new JLabel("X  ");
        xSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        xSymbol.setFont(new Font("SansSerif", Font.PLAIN, 18));
        xSymbol.setForeground(Color.WHITE);

        xScoreLabel = new JLabel("0");
        xScoreLabel.setFont(new Font("Arial",Font.BOLD,28));

        playerXBox.add(xSymbol, BorderLayout.WEST);
        playerXBox.add(xName, BorderLayout.CENTER);
        playerXBox.add(xScoreLabel, BorderLayout.EAST);

        timerLabel = new JLabel("02 : 00");
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.WHITE);

        playerOBox = new JPanel();
        playerOBox.setPreferredSize(new Dimension(430,55));
        playerOBox.setBackground(new Color(153, 96, 48));
        playerOBox.setLayout(new BorderLayout());
        playerOBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        oName = new JLabel(gameEngine3.getPlayerOName());
        oName.setAlignmentX(Component.CENTER_ALIGNMENT);
        oName.setFont(new Font("SansSerif", Font.BOLD, 16));
        oName.setForeground(Color.WHITE);

        JLabel oSymbol = new JLabel("O  ");
        oSymbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        oSymbol.setFont(new Font("SansSerif", Font.PLAIN, 18));
        oSymbol.setForeground(Color.WHITE);

        oScoreLabel = new JLabel("0");
        oScoreLabel.setFont(new Font("Arial",Font.BOLD,28));

        playerOBox.add(oSymbol, BorderLayout.WEST);
        playerOBox.add(oName, BorderLayout.CENTER);
        playerOBox.add(oScoreLabel, BorderLayout.EAST);

        playerPanel.add(playerXBox, BorderLayout.WEST);
        playerPanel.add(timerLabel, BorderLayout.CENTER);
        playerPanel.add(playerOBox, BorderLayout.EAST);

        contentPane.add(playerPanel);


        JPanel board = new JPanel(new FlowLayout(FlowLayout.CENTER));
        board.setOpaque(false);

        JPanel boardPanel = new JPanel(new GridLayout(5, 5, 5,5));
        boardPanel.setBackground(new Color(45, 25, 15));
        boardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int b = 0; b < 25; b++) {
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
                    btn.setPreferredSize(new Dimension(55, 30));
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

                        if (gameEngine3.makeMove(boardIndex, row, col)) {

                            refreshBoard();
                            updateBoardUI();
                            updateScore();
                            highlightCurrentPlayer();

                            if (gameEngine3.isGameOver()) {

                                if (gameTimer != null) {
                                    gameTimer.stop();
                                }

                                finishTimedGame();
                            }
                        }
                    });

                    tilePanel.add(btn);

                }
            }

            // Create overlay for small board winner
//            =======================================
            JLabel overlay = new JLabel("");
            overlay.setHorizontalAlignment(SwingConstants.CENTER);
            overlay.setVerticalAlignment(SwingConstants.CENTER);
            overlay.setFont(new Font("Arial", Font.BOLD, 55));
            overlay.setAlignmentX(0.5f);
            overlay.setAlignmentY(0.5f);
            overlay.setForeground(new Color(255, 255, 255, 220));
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
            resetGame(true);

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
            resetGame(false);

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
        updateScore();
        highlightCurrentPlayer();

        winnerDialog = new WinnerDialog(parent, new WinnerDialog.DialogActions() {
            @Override
            public void onPlayAgain() {
                resetGame(true);
            }

            @Override
            public void onBackToMenu() {
                resetGame(false);
                parent.showMenu();
            }
        });


    }


    private void showWinScreen() {

        char winner = gameEngine3.getWinner();
        int xScore = gameEngine3.getXBoardsWon();
        int oScore = gameEngine3.getOBoardsWon();

        String title, message;
        String score = "Final Score: X " + xScore + " - " + oScore + " O";

        if (winner == 'D') {
            title = "Time Up";
            message = "Match Draw";
        } else if (winner == 'X') {
            title = "Winner";
            message = gameEngine3.getPlayerXName() + " [X] Wins!";
        } else {
            title = "Winner";
            message = gameEngine3.getPlayerOName() + " [O] Wins!";
        }

        winnerDialog.showResult(title, message, score);
    }


    private void hideWinScreen() {

       winnerDialog.setVisible(false);
    }


    private void refreshBoard() {

        for (int b = 0; b < 25; b++) {

            for (int r = 0; r < 3; r++) {

                for (int c = 0; c < 3; c++) {

                    char value =
                            gameEngine3.getCell(b, r, c);

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

        if (gameEngine3.getCurrentPlayer() == 'X') {

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

        gameEngine3.setPlayerNames(playerX, playerO);

        xName.setText(gameEngine3.getPlayerXName());
        oName.setText(gameEngine3.getPlayerOName());
    }

    private void updateBoardUI() {

        for (int b = 0; b < 25; b++) {

            String winner = String.valueOf(gameEngine3.getBoardWinner(b));

            // CASE 1: BOARD FINISHED
            if (!winner.equals("-")) {

                smallBoards[b].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        buttons[b][r][c].setEnabled(false);
                        buttons[b][r][c].setText("");
                    }
                }

                boolean alreadyShown = boardWinnerOverlay[b].isVisible();
                boardWinnerOverlay[b].setVisible(true);

                if (winner.equals("X")) {
                    smallBoards[b].setBackground(new Color(180, 50, 50)); // red
                    boardWinnerOverlay[b].setText("X");
                    boardWinnerOverlay[b].setForeground(new Color(255, 60, 60, 180));
                } else if (winner.equals("O")) {
                    smallBoards[b].setBackground(new Color(50, 90, 200)); // blue
                    boardWinnerOverlay[b].setText("O");
                    boardWinnerOverlay[b].setForeground(new Color(60, 120, 255, 180));
                } else {
                    smallBoards[b].setBackground(new Color(120, 120, 120)); // draw
                    boardWinnerOverlay[b].setText("=");
                    boardWinnerOverlay[b].setForeground(new Color(150, 150, 150, 180));
                }
                if(!alreadyShown) {
                    SoundManager.playSound("victory.wav");
                }

                continue;
            }

            // CASE 2: ACTIVE BOARD / not finished board
            boardWinnerOverlay[b].setVisible(false);

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {

                    if (gameEngine3.getBoardWinner(b) == '-') {

                        buttons[b][r][c].setEnabled(gameEngine3.getBoardWinner(b) == '-');
                    }
                }
            }


            revalidate();
            repaint();
        }
    }



        private void startTimer(int seconds){

            if (gameTimer != null) {
                gameTimer.stop();
            }

            timeRemaining = seconds;

            timerLabel.setText("02 : 00");

            gameTimer = new Timer(1000, e -> {

                timeRemaining--;

                int minutes = timeRemaining / 60;
                int sec = timeRemaining % 60;

                timerLabel.setText(String.format("%02d : %02d", minutes, sec));

                updateScore();

                if (timeRemaining <= 0) {

                    gameTimer.stop();

                    finishTimedGame();
                }
            });

            gameTimer.start();
        }


        private void finishTimedGame () {

            gameEngine3.stopGame();

            for (int b = 0; b < 25; b++) {

                for (int r = 0; r < 3; r++) {

                    for (int c = 0; c < 3; c++) {

                        buttons[b][r][c].setEnabled(false);
                    }
                }
            }

            showWinScreen();
        }


        private void updateScore() {

            int x = gameEngine3.getXBoardsWon();
            int o = gameEngine3.getOBoardsWon();


            xScoreLabel.setText(String.valueOf(x));
            oScoreLabel.setText(String.valueOf(o));

        }

    private void resetGame(boolean restartTimer) {

        gameEngine3.reset();

        if (restartTimer) {
            startTimer(120);
        }

        else {
            stopTimer();
            timerLabel.setText("02 : 00");
        }
        for (int b = 0; b < 25; b++) {

            boardWinnerOverlay[b].setVisible(false);
            boardWinnerOverlay[b].setText("");

            smallBoards[b].setEnabled(true);
            smallBoards[b].setBorder(BorderFactory.createLineBorder(new Color(195,152,63),2));
            smallBoards[b].setBackground(new Color(88,51,22));

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {

                    JButton btn = buttons[b][r][c];

                    btn.setText("");
                    btn.setEnabled(true);
                    btn.setVisible(true);
                }
            }
        }

        refreshBoard();
        updateBoardUI();
        updateScore();
        highlightCurrentPlayer();
        hideWinScreen();

        revalidate();
        repaint();
    }

    private void stopTimer() {
        if(gameTimer != null) {
            gameTimer.stop();
        }
    }

    public void startSession()
    {
        resetGame(true);
    }
    }


