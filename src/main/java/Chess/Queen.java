package Chess;

public class Queen extends Piece {

    private String type = "Queen";
    Bishop bishop;

    private char letter = 'Q';

    public Queen(boolean white) {
        super(white);
    }

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

        if (end.getX() > start.getX() && end.getY() == start.getY() || end.getX() < start.getX() && end.getY() == start.getY()) {
            return true;
        }

        if (end.getY() > start.getY() && end.getX() == start.getX() || end.getY() < start.getY() && end.getX() == start.getX()) {
            return true;
        }

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
