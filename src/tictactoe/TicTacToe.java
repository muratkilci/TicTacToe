package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static final Scanner inp = new Scanner(System.in);
    private static final String[][] board = new String[3][3];

    private static void gameLoop(Player player1, Player player2) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        player1.setBoard(board);
        player2.setBoard(board);
        while (true) {
            showBoard();
            if (player1.gameTurn()) {
                showBoard();
                System.out.println("X wins");
                break;
            } else if (isBoardEmpty()) {
                showBoard();
                System.out.println("Draw");
                break;
            }

            showBoard();
            if (player2.gameTurn()) {
                showBoard();
                System.out.println("O wins");
                break;
            } else if (isBoardEmpty()) {
                showBoard();
                System.out.println("Draw");
                break;
            }
        }
    }

    private static void showBoard() {
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

    private static boolean isBoardEmpty() {
        //Board check. Used for draw control.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" "))
                    return false;
            }
        }
        return true;
    }

    private static int getIntInput(String option) {
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

    private static int getInt() {
        while (!inp.hasNextInt()) {
            System.out.println("please enter your choice :");
            inp.next();
        }
        return inp.nextInt();
    }

    public void start() {
        while (true) {
            System.out.println("""
                    1)Start
                    2)Exit""");
            System.out.print("Input command: > ");
            String entry;

            entry = String.valueOf(getIntInput("1"));
            if (entry.equals(String.valueOf(1))) {
                System.out.println("Enter Player1 ");
                System.out.println("""
                        1)Easy
                        2)Medium
                        3)Hard
                        4)User""");
                String firstPlayer = String.valueOf(getIntInput("2"));

                System.out.println("Enter Player2 ");
                System.out.println("""
                        1)Easy
                        2)Medium
                        3)Hard
                        4)User""");
                String secondPlayer = String.valueOf(getIntInput("2"));


                //We create our abstract objects
                Player player1;
                Player player2;
                switch (firstPlayer) {
                    case "1" -> player1 = new Easy("X");
                    case "2" -> player1 = new Medium("X");
                    case "3" -> player1 = new Hard("X");
                    case "4" -> player1 = new User("X");
                    default -> {
                        System.out.println("Wrong input player!");
                        continue;
                    }
                }
                switch (secondPlayer) {
                    case "1" -> player2 = new Easy("O");
                    case "2" -> player2 = new Medium("O");
                    case "3" -> player2 = new Hard("O");
                    case "4" -> player2 = new User("O");
                    default -> {
                        System.out.println("Wrong input player!");
                        continue;
                    }
                }
                gameLoop(player1, player2);
            }

            if (entry.equals(String.valueOf(2)))
                break;

        }

    }
}