package Chess;

public class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Piece getPieceMoved() {
        return this.pieceMoved;
    }

    public Spot getStart() {
        return this.start;
    }

    public void setPieceKilled(Piece killedPiece) {
        this.pieceKilled = killedPiece;
    }

    public Spot getEnd() {
        return this.end;
    }

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }




}
