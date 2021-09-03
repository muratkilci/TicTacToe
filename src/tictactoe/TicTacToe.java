package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private final String[][] board = new String[3][3];
    private final Scanner inp = new Scanner(System.in);

    // TODO: We read code from the top, therefore the functions should be declared in this order.
    //  Therefore move 'start' function to the top, to make the code easier to read.

    private void gameLoop(Player player1, Player player2) {

        // TODO: What we are doing here?
        Arrays.stream(board).forEach(row ->
                Arrays.fill(row, " "));

        player1.setBoard(board);
        player2.setBoard(board);

        // TODO: 'true' in a loop is generally not a good idea.
        //  Because it reduces the readability of the code, therefore considered as bad coding practice.
        //  Instead, use flags, named in accordance with what it does.
        //  Also, 'break' and 'continue' can make code hard to read, use them sparely.

        boolean finishFlag = true;
        while (finishFlag) {
            // Player1 make its move,
            makeMove(player1);

            if (!isGameFinished(player1, true)) {
                // Player2 make its move,
                makeMove(player2);
                if (isGameFinished(player2, false)) {
                    finishFlag = false;
                }

            } else {
                finishFlag = false;
            }
        }
    }

    private void makeMove(Player player) {
        showBoard();
        player.gameTurn();
    }

    private boolean isGameFinished(Player player, boolean isFirstPlayer) {
        boolean isWin = Player.winner(player.getPlayerChar());
        // We check if game finished or not.
        boolean isGameFinished;

        if (isWin || isBoardEmpty()) {
            showBoard();
            String result;

            if (isWin) result = isFirstPlayer ? "X wins" : "O wins";
            else result = "Draw";

            System.out.println(result);
            isGameFinished = true;

        } else {
            isGameFinished = false;
        }

        return isGameFinished;
    }

    private void showBoard() {
        // Printing on board.
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    private boolean isBoardEmpty() {
        //Board check. Used for draw control.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" "))
                    return false;
            }
        }
        return true;
    }

    private int getIntInput(String option) {
        int upLimit = 0;

        if (option.equals("1")) {
            upLimit = 2;
        } else if (option.equals("2")) {
            upLimit = 4;
        }

        while (true) {
            int getInteger = getInt();
            inp.nextLine();
            if (getInteger > 0 && getInteger <= upLimit) {
                return getInteger;
            } else {
                System.out.println("Please enter a number from 1 to " + upLimit);
            }
        }
    }

    private int getInt() {
        while (!inp.hasNextInt()) {
            System.out.println("please enter your choice :");
            inp.next();
        }
        return inp.nextInt();
    }

    public void start() {
        // TODO: 'true' in a loop is generally not a good idea.
        //  Because it reduces the readability of the code, therefore considered as bad coding practice.
        //  Instead, use flags, named in accordance with what it does.
        //  Also, 'break' and 'continue' can make code hard to read, use them sparely.
        boolean keepPlaying = true;
        while (keepPlaying) {
            System.out.println("""
                    1)Start
                    2)Exit""");
            System.out.print("Input command: > ");

            String entry = String.valueOf(getIntInput("1"));
            if (entry.equals("1")) {
                System.out.println("Enter Player1 ");

                // TODO: You can go to method declaration with CTRL + B,
                //  or right click Go To -> Declaration or usages.
                printPlayerTypes();
                String firstPlayer = String.valueOf(getIntInput("2"));

                System.out.println("Enter Player2 ");
                printPlayerTypes();
                String secondPlayer = String.valueOf(getIntInput("2"));

                //We create our abstract objects
                Player player1;
                Player player2;
                try {
                    player1 = choosePlayerTypes(true, firstPlayer);
                    player2 = choosePlayerTypes(false, secondPlayer);
                    gameLoop(player1, player2);
                } catch (Exception e) {
                    // TODO: We already check the player input with 'getIntInput' method,
                    //  If an invalid entry is made despite this, it means that there is an error our code.
                    //  That's why we throw IllegalStateException error in 'choosePlayerTypes' function,
                    //  but still we choose to catch the error in 'catch' so that the program doesn't crash.
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }

            } else if (entry.equals("2")) {
                keepPlaying = false;
            }
        }
    }

    private void printPlayerTypes() {
        System.out.println("""
                        1)Easy
                        2)Medium
                        3)Hard
                        4)User""");
    }

    private Player choosePlayerTypes(boolean isFirstPlayer, String playerType) {
        Player player;

        String mark = isFirstPlayer ? "X" : "O";

        switch (playerType) {
            case "1" -> player = new Easy(mark);
            case "2" -> player = new Medium(mark);
            case "3" -> player = new Hard(mark);
            case "4" -> player = new User(mark);
            default ->
                    throw new IllegalStateException("Wrong input player!");
        }
        return player;
    }
}