package tictactoe;


import java.util.ArrayList;
import java.util.List;

public class Hard extends Player{

    public Hard(String playerChar) {
        super(playerChar);
    }

    String oppositeChar =this.getPlayerChar().equals("X") ? "O" :"X";      //We're creating an opposing character.

    @Override
    protected boolean gameTurn() {
        System.out.println("Making move level \"hard\"");
        int[] result = minimax(3, getPlayerChar()); // depth, max turn
        board[result[1]][result[2]] = getPlayerChar();
        return winner(getPlayerChar());
    }

    private int[] minimax(int depth,String player) {
        List<int[]> nextMoves = makeMove();

        int bestScore = (player.equals(getPlayerChar())) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            bestScore = score();
        }
        else {
            for (int[] move : nextMoves) {                  //Detecting the coordinate that best score
                board[move[0]][move[1]] = player;
                if (player.equals(getPlayerChar())) {
                    currentScore = minimax(depth - 1, oppositeChar)[0];  //Here it tries all the possibilities by repeating the same function.
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                else {
                    currentScore = minimax(depth - 1, getPlayerChar())[0];   //Here it tries all the possibilities by repeating the same function.As in the case of If
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                board[move[0]][move[1]] = " ";
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }

    private List<int[]> makeMove(){

        /*
        * In this part, it assigns an array by subtracting all the moves that may occur after it.
        */

        List<int[]> nextMoves = new ArrayList<int[]>();

        if (winner(getPlayerChar())|| winner(oppositeChar)){
            return nextMoves;
        }
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (board[row][col].equals(" ")) {
                    nextMoves.add(new int[] {row, col});
                }
            }
        }
        return nextMoves;
    }

    private int score(){

        int score =0;

        score += scoreControl(0, 0, 0, 1, 0, 2);  // row 1
        score += scoreControl(1, 0, 1, 1, 1, 2);  // row 2
        score += scoreControl(2, 0, 2, 1, 2, 2);  // row 3
        score += scoreControl(0, 0, 1, 0, 2, 0);  // col 1
        score += scoreControl(0, 1, 1, 1, 2, 1);  // col 2
        score += scoreControl(0, 2, 1, 2, 2, 2);  // col 3
        score += scoreControl(0, 0, 1, 1, 2, 2);  // diagonal 1
        score += scoreControl(0, 2, 1, 1, 2, 0);  // diagonal 2
        return score;

    }

    private int scoreControl(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (board[row1][col1].equals(getPlayerChar())) {
            score = 1;
        }
        else if (board[row1][col1].equals(oppositeChar)) {
            score = -1;
        }
        /*
        * If the first cell is PlayerChar + 1, the opposite character becomes a score if -1
        */

        // Second cell
        if (board[row2][col2].equals(getPlayerChar())) {
            if (score == 1) {
                score = 10;
            }
            else if (score == -1) {
                return 0;
            }
            else {  // cell1 is empty
                score = 1;
            }
        } else if (board[row2][col2].equals(oppositeChar)) {
            if (score == -1) {
                score = -10;
            }
            else if (score == 1) {
                return 0;
            }
            else {  // cell1 is empty
                score = -1;
            }
        }
        /*
        * In the second cell, if it is equal to PlayerChar, this time it gets + 10 points, if it is not equal it gets -1 points.
        * If the first cell is empty, it gets + 1 points.
        * For the opposite character, the event is the opposite.
        */


        // Third cell
        if (board[row3][col3].equals(getPlayerChar())) {
            if (score > 0) {
                score *= 10;
            }
            else if (score < 0) {
                return 0;
            }
            else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (board[row3][col3].equals(oppositeChar)) {
            if (score < 0) {
                score *= 10;
            }
            else if (score > 1) {
                return 0;
            }
            else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        /*
        * In the third cell, if it is equal to Playerchar, it gets the maximum score this time.
        * If it is not equal, it returns 0 points. If the third cell is full and the second cell is empty, this time it is separated by +1 points.
        * The same event applies to the opposing character.
        */

        return score;
    }

}
