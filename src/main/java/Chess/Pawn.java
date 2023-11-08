package Chess;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    private String type = "Pawn";

    public String getType() {
        return this.type;
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        if (isHasMoved()) {
            if (isWhite()) {
                if (end.getX() == start.getX() + 1 && start.getY() == end.getY()) {
                    return true;
                }
            } else {
                if ((end.getX() == start.getX() - 1 && start.getY() == end.getY())) {
                    return true;
                }
            }
        }
        if (!isHasMoved()) {
            setIsHasMoved(true);
            if (end.getX() <= start.getX() + 2 && start.getY() == end.getY()) {
                return true;
            }
        }

        //TODO write code that says if x or y is greater than 1 than return false to stop the check.

        if (start.getX() > end.getX() + 1 || start.getY() > end.getY() + 1) {
            return false;
        }

        if (isWhite()) {
            if (start.getX() != end.getX() + 1 && start.getY() != end.getY() + 1 && end.getPiece() != null || start.getX() != end.getX() + 1 && start.getY() != end.getY() - 1 && end.getPiece() != null) {
                return true;
            }
        } else {
            if (start.getX() != end.getX() - 1 && start.getY() != end.getY() - 1 && end.getPiece() != null || start.getX() != end.getX() - 1 && start.getY() != end.getY() + 1 && end.getPiece() != null) {
                return true;
            }
        }

        return false;
    }

}
