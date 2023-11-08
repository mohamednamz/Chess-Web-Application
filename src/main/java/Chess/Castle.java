package Chess;

public class Castle extends Piece {

    public Castle(boolean white) {
        super(white);
    }

    private String type = "Castle";

    public String getType() {
        return this.type;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

//        TODO i've removed this from all functions, want to do one check in game, some reason not working.
//        if (end.getPiece().isWhite() == this.isWhite()) {
//            return false;
//        }

        if (end.getX() > start.getX() && end.getY() == start.getY() || end.getX() < start.getX() && end.getY() == start.getY()) {
            if (end.getX() > start.getX()) {
                for (int x = start.getX() + 1; x < end.getX(); x++) {
                    if (board.boxes[x][end.getY()].getPiece() != null) {
                        return false;
                    }
                }
            } else {
                for (int x = start.getX() - 1; x >= end.getX(); x--) {
                    if (board.boxes[x][end.getY()].getPiece() != null) {
                        return false;
                    }
                }
            }
            return true;
        }

        if (end.getY() > start.getY() && end.getX() == start.getX() || end.getY() < start.getY() && end.getX() == start.getX()) {
            if (end.getY() > start.getY()) {
                for (int y = start.getY() + 1; y <= end.getY(); y++) {
                    if (board.boxes[end.getX()][y].getPiece() != null) {
                        return false;
                    }
                }
            } else {
                for (int y = start.getY() - 1; y >= end.getY(); y--) {
                    if (board.boxes[end.getX()][y].getPiece() != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


}
