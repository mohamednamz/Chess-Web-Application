package Chess;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    private String type = "Knight";

    private char letter = 'k';

    public String getType() {
        return this.type;
    }

    public char getLetter() {
        return this.letter;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        if (end.getX() == start.getX() + 2 && end.getY() == start.getY() + 1) {
            return true;
        }
        if (end.getY() == start.getY() + 2 && end.getX() == start.getX() + 1) {
            return true;
        }
        if (end.getX() == start.getX() + 2 && end.getY() == start.getY() - 1) {
            return true;
        }
        if (end.getY() == start.getY() + 2 && end.getX() == start.getX() - 1) {
            return true;
        }
        if (end.getX() == start.getX() - 2 && end.getY() == start.getY() + 1) {
            return true;
        }
        if (end.getY() == start.getY() - 2 && end.getX() == start.getX() + 1) {
            return true;
        }
        if (end.getX() == start.getX() - 2 && end.getY() == start.getY() - 1) {
            return true;
        }
        if (end.getY() == start.getY() - 2 && end.getX() == start.getX() - 1) {
            return true;
        }
        return false;
    }


}
