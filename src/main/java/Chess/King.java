package Chess;

public class King extends Piece {

    private Board board;

    private String type = "King";

    private char letter = 'K';
    private boolean isCastlingValid = true;
    private boolean isCastlingDone = false;
    private boolean isKingInCheck = false;

    public King(boolean white) {
        super(white);
    }

    public String getType() {
        return this.type;
    }

    public char getLetter() {
        return this.letter;
    }

    public boolean isCastlingValid() {
        return this.isCastlingValid;
    }

    public void setIsCastlingValid(boolean isCastlingValid) {
        this.isCastlingValid = isCastlingValid;
    }

    public boolean isCastlingDone() {
        return this.isCastlingDone;
    }

    public boolean isKingInCheck() {
        return this.isKingInCheck;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.isCastlingDone = castlingDone;
    }

    public void setKingInCheck(boolean isKingInCheck) {
        this.isKingInCheck = isKingInCheck;
    }

    public boolean getIsCastlingValid() {
        return this.isCastlingValid;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        isValidCastling(board, start, end);

        if (getIsCastlingValid()) {
            return true;
        }

        if (end.getX() == start.getX() + 1 && end.getY() == start.getY() || end.getY() == start.getY() + 1 && end.getX() == start.getX() ||
                end.getX() == start.getX() + 1 && end.getY() == start.getY() + 1 || end.getX() == start.getX() + 1 && end.getY() == start.getY() - 1) {
            return true;
        }
        if (end.getX() == start.getX() - 1 && end.getY() == start.getY() || end.getY() == start.getY() - 1 && end.getX() == start.getX() ||
                end.getX() == start.getX() - 1 && end.getY() == start.getY() - 1) {
            return true;
        }
        return false;

    }

    public void isValidCastling(Board board, Spot start, Spot end) {

        if (this.isCastlingDone()) {
            setIsCastlingValid(false);
        } else {
            if (isCastlingMove(board, start, end)) {
                setIsCastlingValid(true);
            } else {
                setIsCastlingValid(false);
            }
        }
    }

    private boolean isCastlingMove(Board board, Spot start, Spot end) {
        if (isHasMoved() || isKingInCheck()) {
            return false;
        }

        if (isWhite()) {
            if (start.getX() == 0 && start.getY() == 4 && end.getX() == 0 && end.getY() == 0) {
                for (int x = 0; x < 1; x++) {
                    for (int y = 1; y < 4; y++) {
                        if (board.boxes[x][y].getPiece() != null) {
                            return false;
                        }
                    }
                }
            } else if (start.getX() == 0 && start.getY() == 4 && end.getX() == 0 && end.getY() == 7) {
                for (int x = 0; x < 1; x++) {
                    for (int y = 5; y < 7; y++) {
                        if (board.boxes[x][y].getPiece() != null) {
                            return false;
                        }
                    }
                }
            }
        }

        if (!isWhite()) {
            if (start.getX() == 7 && start.getY() == 4 && end.getX() == 7 && end.getY() == 0) {
                for (int x = 7; x > 6; x--) {
                    for (int y = 3; y > 0; y--) {
                        if (board.boxes[x][y].getPiece() != null) {
                            return false;
                        }
                    }
                }
            } else if (start.getX() == 7 && start.getY() == 4 && end.getX() == 7 && end.getY() == 7) {
                for (int x = 7; x > 6; x--) {
                    for (int y = 5; y < 7; y--) {
                        if (board.boxes[x][y].getPiece() != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}


































