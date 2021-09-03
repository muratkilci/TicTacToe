package tictactoe;

import java.util.Random;

public abstract class Player {
    protected final Random random = new Random();
    protected static String[][] board = new String[3][3];
    protected String playerChar;

    Player(String playerChar) {
        this.playerChar = playerChar;
    }

    public String getPlayerChar() {
        return playerChar;
    }

    public void setBoard(String[][] board) {
        Player.board = board;
    }

    protected void randomChoice() {
        //Random value function for easy and medium method.
        int[] Coordinates = new int[2];

        while (true) {
            Coordinates[0] = random.nextInt(3);
            Coordinates[1] = random.nextInt(3);
            if (board[Coordinates[0]][Coordinates[1]].equals(" ")) {
                board[Coordinates[0]][Coordinates[1]] = getPlayerChar();
                break;
            }
        }
    }

    protected boolean winner(String playerNum) {
        /*
         * Here there are a total of 8 ways to win 3 horizontal, 3 vertical and 2 diagonal
         * we wrote a series containing all these paths and made the winning control with this series.
         * */
        String[] strings = new String[8];
        strings[0] = board[0][0] + board[0][1] + board[0][2];
        strings[1] = board[1][0] + board[1][1] + board[1][2];
        strings[2] = board[2][0] + board[2][1] + board[2][2];
        strings[3] = board[0][0] + board[1][0] + board[2][0];
        strings[4] = board[0][1] + board[1][1] + board[2][1];
        strings[5] = board[0][2] + board[1][2] + board[2][2];
        strings[6] = board[0][0] + board[1][1] + board[2][2];
        strings[7] = board[0][2] + board[1][1] + board[2][0];
        for (String string : strings) {
            if (string.equals(playerNum + playerNum + playerNum)) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean gameTurn();    //The artificial intelligence that plays this game will be encoded in it.

}
