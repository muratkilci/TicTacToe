package tictactoe;

public class User extends Player{
    private String[] userInput;

    public User(String playerChar) {
        super(playerChar);
    }

    @Override
    protected boolean gameTurn() {
        while (true) {

            System.out.print("Enter the coordinates: ");
            userInput = inp.nextLine().split(" ");
            /*
            * The Split method is used to split what is written and assign it to a string. This is how we enter the coordinates.
            * But gets a string type in the input made.
            * This is why we convert data to integers using the parseInt method
            * */

            if (userInput.length != 2 || numControl(userInput[0]) || numControl(userInput[1])){
                System.out.println("You should enter numbers!");
                continue;
            }

            if (Integer.parseInt(userInput[0]) < 1 || Integer.parseInt(userInput[1]) < 1
                    || Integer.parseInt(userInput[0]) > 3 || Integer.parseInt(userInput[1]) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!board[Integer.parseInt(userInput[0]) - 1][Integer.parseInt(userInput[1]) - 1].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            board[Integer.parseInt(userInput[0]) - 1][Integer.parseInt(userInput[1]) - 1] = getPlayerChar();
            break;

        }
        return winner(getPlayerChar());
    }

    private static boolean numControl(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch(NumberFormatException e){
            return true;
        }
    }
}
