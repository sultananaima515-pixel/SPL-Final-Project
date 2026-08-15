package TimeMode;

import javax.swing.*;

public class GameEngine5x5 {
    private char[][][] boards;      // 9 small boards
    private char[] boardWinners;    // winner of each small board

    private char currentPlayer;

    private boolean gameOver;
    private char winner;

    private String playerXName;
    private String playerOName;


    public GameEngine5x5() {

        boards = new char[25][3][3];
        boardWinners = new char[25];

        playerXName = "Player X";
        playerOName = "Player O";

        reset();
    }

    public void reset() {

        for(int b=0;b<25;b++) {

            boardWinners[b] = '-';

            for(int r=0;r<3;r++) {
                for(int c=0;c<3;c++) {
                    boards[b][r][c] = '-';
                }
            }
        }

        currentPlayer = 'X';


        gameOver = false;
        winner = '-';
    }

    public boolean isValidMove(int board, int row, int col) {

        if(gameOver)
            return false;

        if(board<0 || board>=25)
            return false;
        if(row<0 || row>=3 || col<0 || col>=3)
            return false;

        if(boardWinners[board] != '-')
            return false;

        return boards[board][row][col] == '-';
    }

    public boolean makeMove(int board, int row, int col) {

        if(!isValidMove(board,row,col))
            return false;

        boards[board][row][col] = currentPlayer;

        updateSmallBoard(board);

        updateBigBoard();

        if(!gameOver) {

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

//    check winner for small board
    private char checkWinner(char[][] board) {

        for(int i=0;i<3;i++) {

            //row
            if(board[i][0] != '-'
                    && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2] )
                return board[i][0];

            //column
            if(board[0][i] != '-'
                    && board[0][i] == board[1][i]
                    && board[1][i] == board[2][i])
                return board[0][i];
        }

        //diagonal
        if(board[0][0] != '-'
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2] )
            return board[0][0];
//right to left diagonal
        if(board[0][2] != '-'
                && board[0][2] == board[1][1]
                && board[1][1] == board[2][0] )
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

    public char getBoardWinner(int board) {
        return boardWinners[board];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void stopGame() {

        gameOver = true;
        int x = getXBoardsWon();
        int o = getOBoardsWon();

        if(x>o)
            winner = 'X';

        else if(x<o)
            winner = 'O';

        else
            winner = 'D';
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


    public int getXBoardsWon() {

        int count = 0;

        for(char c : boardWinners) {

            if(c == 'X')
                count++;
        }

        return count;
    }

    public int getOBoardsWon() {

        int count = 0;

        for(char c : boardWinners) {

            if(c == 'O')
                count++;
        }

        return count;
    }

    public int getDrawBoards()
    {
        int count = 0;
        for(char c : boardWinners) {
            if(c == 'D')
                count++;
        }
        return count;
    }

}


