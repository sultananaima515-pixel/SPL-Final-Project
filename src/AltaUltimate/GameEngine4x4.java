package AltaUltimate;
import java.awt.*;
import java.awt.event.*;


public class GameEngine4x4 {
    private char[][][] boards;      // 9 small boards
    private char[] boardWinners;    // winner of each small board

    private char currentPlayer;
    private int activeBoard;        // -1 = any board

    private boolean gameOver;
    private char winner;

    private String playerXName;
    private String playerOName;

    public GameEngine4x4() {

        boards = new char[16][4][4];
        boardWinners = new char[16];

        playerXName = "Player X";
        playerOName = "Player O";

        reset();
    }

    public void reset() {

        for(int b=0;b<16;b++) {

            boardWinners[b] = '-';

            for(int r=0;r<4;r++) {
                for(int c=0;c<4;c++) {
                    boards[b][r][c] = '-';
                }
            }
        }

        currentPlayer = 'X';
        activeBoard = -1;

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

        boards[board][row][col] = currentPlayer;

        updateSmallBoard(board);

        updateBigBoard();

        if(!gameOver) {

            int nextBoard = row * 4 + col;

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

        char[][] meta = new char[4][4];

        for(int i=0;i<16;i++) {
            meta[i/4][i%4] = boardWinners[i];
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

        for(int i=0;i<4;i++) {

            if(board[i][0] != '-' && board[i][0]!='D'
                    && board[i][0] == board[i][1]
                    && board[i][1] == board[i][2]
                    && board[i][2] == board[i][3])
                return board[i][0];

            if(board[0][i] != '-' && board[0][i]!='D'
                    && board[0][i] == board[1][i]
                    && board[1][i] == board[2][i]
                    && board[2][i] == board[3][i])
                return board[0][i];
        }

        if(board[0][0] != '-' && board[0][0]!='D'
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]
                && board[2][2] == board[3][3])
            return board[0][0];

        if(board[0][3] != '-' && board[0][3]!='D'
                && board[0][3] == board[1][2]
                && board[1][2] == board[2][1]
                && board[2][1] == board[3][0])
            return board[0][3];

        return '-';
    }

    private boolean isBoardFull(int board) {

        for(int r=0;r<4;r++) {
            for(int c=0;c<4;c++) {

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

