package tictactoe;

public class Medium extends Player {
    String OppositeChar = this.getPlayerChar().equals("X") ? "O" : "X";   //We're creating an opposing character.

    public Medium(String playerNum) {
        super(playerNum);
    }

    @Override
    protected boolean gameTurn() {
        System.out.println("Making move level \"medium\"");
        int[] AICoordinates;
        AICoordinates = mediumMove(getPlayerChar());
        if (AICoordinates[0] == -1) {
            randomChoice();
        } else {
            board[AICoordinates[0]][AICoordinates[1]] = getPlayerChar();
        }
        return winner(getPlayerChar());
    }

    private int[] mediumMove(String player) {
        int[] move;
        move = win(player);
        if (move[0] != -1) {
            return move;
        }
        move = win(OppositeChar);

        return move;
    }

    private int[] win(String charPlayer) {
        /*
         * If it already has two in a row and can win with one further move, it does so.
         * If its opponent can win with one move, it plays the move necessary to block this.
         * Here we determine these moves.
         * */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    board[i][j] = charPlayer;
                    if (winner(charPlayer)) {
                        board[i][j] = " ";
                        return new int[]{i, j};
                    }
                    board[i][j] = " ";
                }
            }
        }
        return new int[]{-1, -1};
    }

}
