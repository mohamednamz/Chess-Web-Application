package Chess;

public class Piece {
    private boolean isKilled = false;
    private boolean isWhite = false;
    private boolean hasMoved = false;

    private String type;

    public char letter;

    public String getType() {
        return this.type;
    }

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isKilled() {
        return this.isKilled;
    }

    public void setKilled(boolean killed) {
        this.isKilled = killed;
    }

    public void setIsHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Piece(boolean white) {
        this.setWhite(white);
        //super function accesses this.
    }
    public boolean isWhite() {
        return this.isWhite;
    }
    public void setWhite(boolean white) {
        this.isWhite = white;
    }

    public boolean isHasMoved() {
        return this.hasMoved;
    }

    //TODO
    public boolean canMove(Board board, Spot start, Spot End) {

        return false;
    }




}
