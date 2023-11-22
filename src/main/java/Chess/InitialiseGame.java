package Chess;

import java.security.PublicKey;

public class InitialiseGame {
    Player playerOne;
    Player playerTwo;
    Game chessMatch = new Game();

    boolean newGame = true;

    int startX = 10;
    int startY = 10;

    public void initialiseGame(Player playerOne, Player playerTwo) {
        chessMatch.initialiseGame(playerOne,playerTwo);
    }

    public boolean MakeMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
        return chessMatch.playerMove(player,startX,startY,endX,endY);
    }

    public List<Move> getMovesPlayed() {
        return chessMatch.getMovesPlayed();
    }

    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }
    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Piece getMostRecentMove() {
        return chessMatch.getMostRecentMove().getPieceMoved();
    }

    public boolean getCheck() {
        return chessMatch.getCheck();
    }

    public char[][] getBoard() {
       return chessMatch.virtualBoard;
    }

    public Player getCurrentTurn() {
       return chessMatch.getCurrentTurn();
    }

    public boolean getIsGameOver() {
        return chessMatch.getIsGameOver();
    }
    public void SetGameOver(boolean setGameOver) {
        chessMatch.SetGameOver(setGameOver);
    }

}

