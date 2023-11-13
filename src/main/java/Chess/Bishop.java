package Chess;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    private String type = "Bishop";

    private char letter = 'B';

    public String getType() {
        return this.type;
    }

    public char getLetter() {
        return this.letter;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        int j = start.getY();
        int y = start.getY();
        int x = start.getX();
        int z = start.getX();

        int breakPoint = 0;

        while (breakPoint < 8) {
            if (x == end.getX() && y == end.getY()) {
                return true;
            }
            if (z == end.getY() && j == end.getX()) {
                return true;
            }
            if (z == end.getX() && y == end.getY()) {
                return true;
            }
            if (x == end.getX() && j == end.getY()) {
                return true;
            }
            if (j == end.getY() && z == end.getX()) {
                return true;
            } else {
                y++;
                j--;
                x--;
                z++;
            }
            breakPoint++;
        }
        return false;
    }


}
