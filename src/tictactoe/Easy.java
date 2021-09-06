package tictactoe;

public class Easy extends Player {

    public Easy(String playerNum) {
        super(playerNum);
    }

    @Override
    protected void gameTurn() {
        System.out.println("Making move level \"easy\"");
        randomChoice();
    }
}
