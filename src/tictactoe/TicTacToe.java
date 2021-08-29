package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static final Scanner inp = new Scanner(System.in);
    private static String[][] board = new String[3][3];
    String[]  menuChoice;

    public void start(){
        while (true) {
            System.out.print("Input command: > ");

            this.menuChoice = inp.nextLine().split(" ");
            /*
            * The Split method is used to split what is written and assign it to a string.
            * This way you can start and enter the player type together.
            * */

            if ( (!menuChoice[0].equals("start") || menuChoice.length != 3) && !menuChoice[0].equals("exit")) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (menuChoice[0].equals("exit"))
                break;

            //We create our abstract objects
            Player player1;
            Player player2;
            switch (menuChoice[1]){
                case "easy":
                    player1 = new Easy("X");
                    break;
                case "medium":
                    player1 = new Medium("X");
                    break;
                case "hard":
                    player1 = new Hard("X");
                    break;
                case "user":
                    player1 = new User("X");
                    break;
                default:
                    System.out.println("Bad parameters!");
                    continue;
            }
            switch (menuChoice[2]){
                case "easy":
                    player2 = new Easy("O");
                    break;
                case "medium":
                    player2 = new Medium("O");
                    break;
                case "hard":
                    player2 = new Hard("O");
                    break;
                case "user":
                    player2 = new User("O");
                    break;
                default:
                    System.out.println("Wrong input player!");
                    continue;
            }
            gameLoop(player1, player2);
        }

    }

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
            } else if (!isBoardEmpty()) {
                showBoard();
                System.out.println("Draw");
                break;
            }

            showBoard();
            if (player2.gameTurn()) {
                showBoard();
                System.out.println("O wins");
                break;
            } else if (!isBoardEmpty()) {
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
                    return true;
            }
        }
        return false;
    }
}
