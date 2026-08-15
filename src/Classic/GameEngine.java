package Classic;


public class GameEngine {

    private String playerXName;
    private String playerOName;
    private char winner;

    private char[][] board; // 'X', 'O', or '\0'
    private char currentPlayer;

    private boolean gameOver;

    public GameEngine() {
        board = new char[3][3];

//        default names
        playerXName = "Player X";
        playerOName = "Player O";

        reset();//Initalize board
    }

    //resets board to empty and x goes first
    public void reset()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = '-';
            }
        }
        currentPlayer = 'X';
        gameOver = false;
        winner = '-';
    }

    public boolean makeMove(int row, int col) {
        if (gameOver || board[row][col] != '-') return false;

        board[row][col] = currentPlayer;   //mark symbols

        if (checkWin(currentPlayer)) {
            gameOver = true;
            winner = currentPlayer;

        } else if (isDraw()) {
            gameOver = true;
            winner = 'D';
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;


    }

    private boolean checkWin(char p) {

        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
                return true;
        }

        // columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == p && board[1][j] == p && board[2][j] == p)
                return true;
        }

        // diagonals left to right
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
            return true;

        //diagonals right to left
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p)
            return true;

        return false;
    }

    // ---------------- DRAW CHECK ----------------
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return true;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    public void setPlayerNames(String x, String o) {

        if(x == null || x.trim().isEmpty()) {
            playerXName = "Player X";
        } else {
            playerXName = x.trim();
        }

        if(o == null || o.trim().isEmpty()) {
            playerOName = "Player O";
        } else {
            playerOName = o.trim();
        }
    }

    public String getPlayerXName() {
        return playerXName;
    }

    public String getPlayerOName() {
        return playerOName;
    }



}
