package Ultimate;

public class GameEngine3x3 {
    private char[][][] boards;      // 9 small boards
    private char[] boardWinners;    // winner of each small board

    private char currentPlayer;
    private int activeBoard;        // -1 = any board

    private boolean gameOver;
    private char winner;

    private String playerXName;
    private String playerOName;

    public GameEngine3x3() {

        boards = new char[9][3][3];
        boardWinners = new char[9];

        playerXName = "Player X";
        playerOName = "Player O";

        reset();
    }

    public void reset() {

        for(int b=0;b<9;b++) {

            boardWinners[b] = '-';

            for(int r=0;r<3;r++) {
                for(int c=0;c<3;c++) {
                    boards[b][r][c] = '-';
                }
            }
        }

        currentPlayer = 'X';
        activeBoard = -1; //first move can be any board

        gameOver = false;
        winner = '-';
    }

    public boolean isValidMove(int board, int row, int col) {

        if(gameOver)
            return false;

        if(boardWinners[board] != '-')
            return false;

        if(activeBoard != -1 && board != activeBoard)
            return false;

        return boards[board][row][col] == '-';
    }

    public boolean makeMove(int board, int row, int col) {

        if(!isValidMove(board,row,col))
            return false;

//        Place X/O on that position
        boards[board][row][col] = currentPlayer;

        //check small is finished or not
        updateSmallBoard(board);
//check whole meta board is finished or not
        updateBigBoard();

//        if game is not over then find next active board

        if(!gameOver) {

            int nextBoard = row * 3 + col;

            if(boardWinners[nextBoard] == '-') {
                activeBoard = nextBoard;
            }
            else {
                activeBoard = -1;

            }

            currentPlayer =
                    (currentPlayer == 'X') ? 'O' : 'X';
        }

        return true;
    }

    private void updateSmallBoard(int board) {

        char w = checkWinner(boards[board]);

        if(w != '-') {
            boardWinners[board] = w;
        }
        else if(isBoardFull(board)) {
            boardWinners[board] = 'D';
        }
    }

    private void updateBigBoard() {

        char[][] meta = new char[3][3];

        for(int i=0;i<9;i++) {
            meta[i/3][i%3] = boardWinners[i];
        }

        char w = checkWinner(meta);

        if(w != '-') {

            winner = w;
            gameOver = true;
            return;
        }

        boolean finished = true;

        for(char c : boardWinners) {

            if(c == '-') {
                finished = false;
                break;
            }
        }

        if(finished) {

            winner = 'D';
            gameOver = true;
        }
    }

    private char checkWinner(char[][] board) {

        for(int i=0;i<3;i++) {

            //row
            if(board[i][0] != '-' && board[i][0] !='D'
                    && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2])
                return board[i][0];

            //column
            if(board[0][i] != '-' && board[0][i] !='D'
                    && board[0][i] == board[1][i]
                    && board[1][i] == board[2][i])
                return board[0][i];
        }

        //diagonal
        if(board[0][0] != '-' && board[0][0] !='D'
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2])
            return board[0][0];
//right to left diagonal
        if(board[0][2] != '-'  && board[0][2] !='D'
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0])
            return board[0][2];

        return '-';
    }

    private boolean isBoardFull(int board) {

        for(int r=0;r<3;r++) {
            for(int c=0;c<3;c++) {

                if(boards[board][r][c] == '-')
                    return false;
            }
        }

        return true;
    }
    public char getCell(int board,int row,int col) {
        return boards[board][row][col];
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public int getActiveBoard() {
        return activeBoard;
    }

    public char getBoardWinner(int board) {
        return boardWinners[board];
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

