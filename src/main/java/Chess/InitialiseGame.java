package Chess;

public class InitialiseGame {
    Player playerOne;
    Player playerTwo;
    Game chessMatch = new Game();

    boolean newGame = true;

    public void initialiseGame(Player playerOne, Player playerTwo) {
        chessMatch.initialiseGame(playerOne,playerTwo);
    }

}

