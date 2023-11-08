package Chess;

public class Player {

    public boolean winner;
    public String name;
    public boolean isInGame;
    public int numberOfQuits;

    public int userId;

    private boolean whiteSide;
    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public void setIsWhiteSide(boolean whiteSide) {
        this.whiteSide = whiteSide;
    }

}
