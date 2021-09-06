package tictactoe;

import java.util.Scanner;

public class User extends Player {
    Scanner inp = new Scanner(System.in);

    public User(String playerChar) {
        super(playerChar);
    }

    private boolean numControl(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Override
    protected void gameTurn() {
        boolean isNotValid = true;
        while (isNotValid) {

            System.out.print("Enter the coordinates: ");
            String[] userInput = inp.nextLine().split(" ");
            /*
             * The Split method is used to split what is written and assign it to a string. This is how we enter the coordinates.
             * But gets a string type in the input made.
             * This is why we convert data to integers using the parseInt method
             * */

            if (userInput.length != 2 || numControl(userInput[0]) || numControl(userInput[1])) {
                System.out.println("You should enter numbers!");

            } else if (Integer.parseInt(userInput[0]) < 1 || Integer.parseInt(userInput[1]) < 1
                    || Integer.parseInt(userInput[0]) > 3 || Integer.parseInt(userInput[1]) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");

            } else if (!board[Integer.parseInt(userInput[0]) - 1][Integer.parseInt(userInput[1]) - 1].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");

            } else {
                board[Integer.parseInt(userInput[0]) - 1][Integer.parseInt(userInput[1]) - 1] = getPlayerChar();
                isNotValid = false;
            }
        }
    }
}
