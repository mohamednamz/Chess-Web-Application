package Chess;

public class Game {
    Player[] players;
    private Board board;
    private Player currentTurn;
    private List<Move> movesPlayed;
    private Stack<Move> movesPlayedd;
    private List<Move> checkMate;
    private Move mostRecentWhiteKingMove;
    private Move mostRecentBlackKingMove;
    private Move mostRecentMove;

    private Move king;
    private List<Piece> piecesKilled;
    private boolean check = false;
    private boolean gameOver = false;
    private boolean currentlyCastling = false;

    public void isCheck(boolean check) {
        this.check = check;
    }

    public Move getMostRecentWhiteKingMove() {
        return this.mostRecentWhiteKingMove;
    }

    public Move getMostRecentBlackKingMove() {
        return this.mostRecentBlackKingMove;
    }

    public void setIsCurrentlyCastling(boolean currentlyCastling) {
        this.currentlyCastling = currentlyCastling;
    }

    public boolean getIsCurrentlyCastling() {
        return this.currentlyCastling;
    }

    public boolean getCheck() {
        return this.check;
    }

    public boolean isGameOver(boolean gameOver) {
        return this.gameOver = gameOver;
    }

    public boolean getIsGameOver() {
        return this.gameOver;
    }


    public void initialiseGame(Player p1, Player p2) {
        players = new Player[2];
        board = new Board();
        movesPlayed = new List<>();
        checkMate = new List<>();
        piecesKilled = new List<>();
        movesPlayedd = new Stack<>();

        p1.setIsWhiteSide(true);

        players[0] = p1;
        players[1] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }
        if (movesPlayed.get(0) != null) {
            movesPlayed.clear();
        }
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {

        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) throws Exception {

        if (getIsGameOver()) {
            printGameOver();
            return false;
        }

        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            return false;
        }

        if (player != currentTurn) {
            printNotYourMove();
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            printNotYourMove();
            return false;
        }

        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            printThisMoveCantBePlayed();
            return false;
        }

        if (move.getEnd().getPiece() != null) {
            if (move.getEnd().getPiece().isWhite() == sourcePiece.isWhite()) {
                if ((sourcePiece instanceof King) && (move.getEnd().getPiece() instanceof Castle)) {
                    System.out.println("Castling");
                } else {
                    printThisMoveCantBePlayed();
                    return false;
                }
            }
        }

        Piece canKillPiece = move.getEnd().getPiece();

        if (canKillPiece != null && canKillPiece.isWhite() != player.isWhiteSide()) {
            canKillPiece.setKilled(true);
            printKilled(canKillPiece);
            move.setPieceKilled(canKillPiece);
            piecesKilled.add(canKillPiece);
        }

//TODO clean this code up
        if (sourcePiece instanceof King) {
            if (move.getEnd().getPiece() instanceof Castle) {
                if (move.getEnd().getPiece().isHasMoved()) {
                    return false;
                } else {
                    if (((King) sourcePiece).getIsCastlingValid()) {
                        if (move.getEnd().getY() < move.getStart().getY()) {
                            move.getStart().setY(2);
                            setIsCurrentlyCastling(true);
                            playerMove(player, move.getEnd().getX(), move.getEnd().getY(), move.getStart().getY(),
                                    move.getStart().getX());
                            setIsCurrentlyCastling(false);
                            ((King) sourcePiece).setCastlingDone(true);
                        }
                        if (move.getEnd().getY() > move.getStart().getY()) {
                            move.getStart().setY(5);
                            setIsCurrentlyCastling(true);
                            playerMove(player, move.getStart().getX(), move.getEnd().getY(), move.getEnd().getX(),
                                    move.getStart().getY());
                            move.getEnd().setY(6);
                            move.getStart().setY(4);
                            setIsCurrentlyCastling(false);
                            ((King) sourcePiece).setCastlingDone(true);
                        }
                    }
                }
            }
        }

        if (sourcePiece instanceof King) {
            if (sourcePiece.isWhite()) {
                king = mostRecentWhiteKingMove;
                mostRecentWhiteKingMove = move;
            } else {
                king = mostRecentBlackKingMove;
                mostRecentBlackKingMove = move;
            }
        }

        Piece start = move.getStart().getPiece();

        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if (getCheck()) {
            isCheck(movesPlayedd.peek(), movesPlayedd.peek().getEnd().getPiece());
            if (getCheck()) {
                move.getStart().setPiece(start);
                move.getEnd().setPiece(null);
                if (sourcePiece.isWhite() && sourcePiece instanceof King) {
                    mostRecentWhiteKingMove = king;
                } else if (!sourcePiece.isWhite() && sourcePiece instanceof King) {
                    mostRecentBlackKingMove = king;
                }
                printCheck();
                return false;
            }
        }

        movesPlayed.add(move);
        movesPlayedd.push(move);
        print(sourcePiece, move);

        isCheck(move, sourcePiece);

        if (getCheck()) {
            if (sourcePiece.isWhite()) {
                if (isCheckMate(mostRecentBlackKingMove, move)) {
                    if (getIsGameOver()) {
                        printGameOver();
                        return false;
                    }
                }
                if (!sourcePiece.isWhite()) {
                    if (isCheckMate(mostRecentWhiteKingMove, move)) {
                        if (getIsGameOver()) {
                            printGameOver();
                            return false;
                        }
                    }
                }
            }
        }

        if (canKillPiece instanceof King) {
            if (player.isWhiteSide()) {
                System.out.println("white wins");
            } else {
                System.out.println("black wins");
            }
        }

        if (!getIsCurrentlyCastling()) {
            if (this.currentTurn == players[0]) {
                this.currentTurn = players[1];
            } else {
                this.currentTurn = players[0];
            }
        }
        mostRecentMove = move;

        return true;

    }

    private void print(Piece sourcePiece, Move move) {
        System.out.println(sourcePiece.getType() + " has moved from coordinates " + move.getStart().getX() + "," + move.getStart().getY() +
                " to " + move.getEnd().getX() + "," + move.getEnd().getY());
    }

    private void printGameOver() {
        //TODO fix print
        System.out.println(this.currentTurn.isWhiteSide() + " has moved won the game, Game Over");
    }

    private void printKilled(Piece sourcePiece) {
        System.out.println(sourcePiece.getType() + " has been killed ");
    }

    private void printCheck() {
        System.out.println("This move cannot be played because your King is in check, please move your King");
    }

    private void printNotYourMove() {
        System.out.println("It is not your turn");
    }

    private void printThisMoveCantBePlayed() {
        System.out.println("This move can't be played");
    }

    private void isCheck(Move move, Piece piece) {

        int counterOne = 0;
        int counterTwo = 0;
        int counterThree = 0;
        int counterFour = 0;

        if (piece != null) {
            if (piece.isWhite()) {
                if (mostRecentBlackKingMove != null) {
                    if (piece instanceof Queen || piece instanceof Bishop) {
                        counterOne = checkDiagonal(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Castle) {
                        counterTwo = checkVerticalAndHorizontal(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Knight) {
                        counterThree = checkKnight(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Pawn) {
                        counterFour = checkPawn(mostRecentBlackKingMove);
                    }
                }
            } else {
                if (mostRecentWhiteKingMove != null) {
                    if (piece instanceof Queen || piece instanceof Bishop) {
                        counterOne = checkDiagonal(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Castle) {
                        counterTwo = checkVerticalAndHorizontal(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Knight) {
                        counterThree = checkKnight(mostRecentBlackKingMove);
                    }
                    if (piece instanceof Pawn) {
                        counterFour = checkPawn(mostRecentBlackKingMove);
                    }
                }
            }
        }

        if (counterOne > 0 || counterTwo > 0 || counterThree > 0 || counterFour > 0) {
            isCheck(true);
        } else {
            isCheck(false);
        }
    }

    private boolean isCheckMate(Move move, Move sourcePiece) {

        if (!getCheck()) {
            return false;
        }
        int counter = 1;
        int counterTwo = 0;
        int counterThree = 0;
        int counterFour = 0;
        int counterFive = 0;
        int finalCounter = 0;
        int totalCounter = 0;
        int yCounter = 0;
        int xCounter = 0;
        boolean moveCantBePlayed = false;

        int setX = 0;
        int setY = 0;

        if (move != null) {
            if (mostRecentBlackKingMove != null) {
                if (sourcePiece.getEnd().getPiece().isWhite() != mostRecentBlackKingMove.getEnd().getPiece().isWhite()) {
                    setX = mostRecentBlackKingMove.getEnd().getX();
                    setY = mostRecentBlackKingMove.getEnd().getY();
                    for (int x = mostRecentBlackKingMove.getEnd().getX() - 1; xCounter <= 3; x++) {
                        if (x > 7) {
                            break;
                        }
                        for (int y = mostRecentBlackKingMove.getEnd().getY() - 1; yCounter <= 3 || y < 8; y++) {
                            if (y > 7) {
                                break;
                            }
                            mostRecentBlackKingMove.getEnd().setX(x);
                            mostRecentBlackKingMove.getEnd().setY(y);

                            if (board.boxes[mostRecentBlackKingMove.getEnd().getX()][mostRecentBlackKingMove.getEnd().getY()].getPiece() != null
                                    && !(board.boxes[mostRecentBlackKingMove.getEnd().getX()][mostRecentBlackKingMove.getEnd().getY()].getPiece() instanceof King)) {
                                if (board.boxes[mostRecentBlackKingMove.getEnd().getX()][mostRecentBlackKingMove.getEnd().getY()].getPiece().isWhite() == mostRecentBlackKingMove.getEnd().getPiece().isWhite()) {
                                    counterFive++;
                                    moveCantBePlayed = true;
                                }
                            }

                            if (!moveCantBePlayed) {
                                counterTwo = checkMateVerticalAndHorizontal(mostRecentBlackKingMove);
                                counterThree = checkDiagonal(mostRecentBlackKingMove);
                                counterFour = checkMateKnight(mostRecentBlackKingMove);

                                if (counterTwo > 0 || counterThree > 0 || counterFour > 0) {
                                    finalCounter++;
                                }
                            }

                            yCounter++;
                            moveCantBePlayed = false;

                            if (yCounter > 2) {
                                xCounter++;
                                yCounter = 0;
                                mostRecentBlackKingMove.getEnd().setY(setY);
                                break;
                            }
                        }
                    }
                }
            }

            if (mostRecentBlackKingMove != null) {
                if (sourcePiece.getEnd().getPiece().isWhite() != mostRecentBlackKingMove.getEnd().getPiece().isWhite()) {
                    mostRecentBlackKingMove.getEnd().setX(setX);
                    mostRecentBlackKingMove.getEnd().setY(setY);
                }
            }

            if (mostRecentWhiteKingMove != null) {
                if (sourcePiece.getEnd().getPiece().isWhite() != mostRecentWhiteKingMove.getEnd().getPiece().isWhite()) {
                    setX = mostRecentWhiteKingMove.getEnd().getX();
                    setY = mostRecentWhiteKingMove.getEnd().getY();
                    for (int x = mostRecentWhiteKingMove.getEnd().getX() - 1; xCounter <= 3; x++) {
                        if (x > 7) {
                            break;
                        }
                        for (int y = mostRecentWhiteKingMove.getEnd().getY() - 1; yCounter <= 3 || y < 8; y++) {
                            if (y > 7) {
                                break;
                            }
                            mostRecentWhiteKingMove.getEnd().setX(x);
                            mostRecentWhiteKingMove.getEnd().setY(y);

                            if (board.boxes[mostRecentWhiteKingMove.getEnd().getX()][mostRecentWhiteKingMove.getEnd().getY()].getPiece() != null
                                    && !(board.boxes[mostRecentWhiteKingMove.getEnd().getX()][mostRecentWhiteKingMove.getEnd().getY()].getPiece() instanceof King)) {
                                if (board.boxes[mostRecentWhiteKingMove.getEnd().getX()][mostRecentWhiteKingMove.getEnd().getY()].getPiece().isWhite() == mostRecentWhiteKingMove.getEnd().getPiece().isWhite()) {
                                    counterFive++;
                                    moveCantBePlayed = true;
                                }
                            }

                            if (!moveCantBePlayed) {
                                counterTwo = checkMateVerticalAndHorizontal(mostRecentWhiteKingMove);
                                counterThree = checkDiagonal(mostRecentWhiteKingMove);
                                counterFour = checkMateKnight(mostRecentWhiteKingMove);

                                if (counterTwo > 0 || counterThree > 0 || counterFour > 0) {
                                    finalCounter++;
                                }
                            }

                            yCounter++;
                            moveCantBePlayed = false;

                            if (yCounter > 2) {
                                xCounter++;
                                yCounter = 0;
                                mostRecentWhiteKingMove.getEnd().setY(setY);
                                break;
                            }
                        }
                    }
                }
            }

            if (mostRecentWhiteKingMove != null) {
                if (sourcePiece.getEnd().getPiece().isWhite() != mostRecentWhiteKingMove.getEnd().getPiece().isWhite()) {
                    mostRecentWhiteKingMove.getEnd().setX(setX);
                    mostRecentWhiteKingMove.getEnd().setY(setY);
                }
            }

            if (finalCounter + counterFive == 9) {
                isGameOver(true);
                return true;
            }
        }
        return false;
    }
    private int checkPawn(Move move) {

        int counter = 0;

        if (move.getEnd().getPiece() != null) {
            if (move.getEnd().getPiece().isWhite()) {

                Piece possibleMoveOne = board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() + 1].getPiece();
                Piece possibleMoveTwo = board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() - 1].getPiece();

                if (possibleMoveOne instanceof Pawn) {
                    counter++;
                }
                if (possibleMoveTwo instanceof Pawn) {
                    counter++;
                }
            } else {
                Piece possibleMoveOne = board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() + 1].getPiece();
                Piece possibleMoveTwo = board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() - 1].getPiece();

                if (possibleMoveOne instanceof Pawn) {
                    counter++;
                }
                if (possibleMoveTwo instanceof Pawn) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private int checkVerticalAndHorizontal(Move move) {

        int counter = 0;
        int x = move.getEnd().getX();
        int j = move.getEnd().getY();


        boolean pieceInBetween = false;

        for (int y = 0; y < 8; y++) {
            if (board.boxes[x][y].getPiece() != null) {
                Piece piece = board.boxes[x][y].getPiece();
                if (piece instanceof Queen || piece instanceof Castle) {
                    counter++;
                    pieceInBetween = false;
                } else if (board.boxes[x][y].getPiece() == move.getEnd().getPiece()) {
                    break;
                } else {
                    pieceInBetween = true;
                }
            }
        }

        for (int z = 0; z < 8; z++) {
            if (board.boxes[z][j].getPiece() != null) {
                Piece piece = board.boxes[z][j].getPiece();
                if (piece instanceof Queen || piece instanceof Castle) {
                    counter++;
                    pieceInBetween = false;
                } else if (board.boxes[z][j].getPiece() == move.getEnd().getPiece()) {
                    break;
                } else {
                    pieceInBetween = true;
                }
            }
        }

        if (pieceInBetween) {
            counter = 0;
        }

        return counter;
    }

    private int checkDiagonal(Move move) {

        int counter = 0;
        int counterTwo = 0;

        boolean isThereAPieceInBetween = false;
        boolean isThereAPieceInBetweenOne = false;
        boolean isThereAPieceInBetweenTwo = false;
        boolean isThereAPieceInBetweenThree = false;

        int j = move.getEnd().getY();
        int y = move.getEnd().getY();
        int x = move.getEnd().getX();
        int z = move.getEnd().getX();

        int breakPoint = 0;

        counterTwo = checkPawn(move);

        while (breakPoint < 8) {
            if (z < 8 && y < 8) {
                if (board.boxes[z][y].getPiece() != null) {
                    Piece piece = board.boxes[z][y].getPiece();
                    if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                        if (!isThereAPieceInBetween) {
                            if (piece instanceof Queen || piece instanceof Bishop) {
                                counter++;
                            }
                        }
                    } else {
                        if (!(piece instanceof King)) {
                            isThereAPieceInBetween = true;
                        }
                    }
                }
            }
            if (z < 8 && j >= 0) {
                if (board.boxes[z][j].getPiece() != null) {
                    Piece piece = board.boxes[z][j].getPiece();
                    if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                        if (!isThereAPieceInBetweenOne) {
                            if (piece instanceof Queen || piece instanceof Bishop) {
                                counter++;
                            }
                        }
                    } else {
                        if (!(piece instanceof King)) {
                            isThereAPieceInBetweenOne = true;
                        }
                    }
                }
            }
            if (x >= 0 && y < 8) {
                if (board.boxes[x][y].getPiece() != null) {
                    Piece piece = board.boxes[x][y].getPiece();
                    if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                        if (!isThereAPieceInBetweenTwo) {
                            if (piece instanceof Queen || piece instanceof Bishop) {
                                counter++;
                            }
                        }
                    } else {
                        if (!(piece instanceof King)) {
                            isThereAPieceInBetweenTwo = true;
                        }
                    }
                }
            }
            if (x >= 0 && j >= 0) {
                if (board.boxes[x][j].getPiece() != null) {
                    Piece piece = board.boxes[x][j].getPiece();
                    if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                        if (!isThereAPieceInBetweenThree) {
                            if (piece instanceof Queen || piece instanceof Bishop) {
                                counter++;
                            }
                        }
                    } else {
                        if (!(piece instanceof King)) {
                            isThereAPieceInBetweenThree = true;
                        }
                    }
                }
            }
            y++;
            j--;
            x--;
            z++;

            breakPoint++;
        }

        return counter + counterTwo;
    }


    private int checkKnight(Move move) {

        int counter = 0;

        if (move.getEnd().getX() == move.getStart().getX() + 2 && move.getEnd().getY() == move.getStart().getY() + 1) {
            counter++;
        }
        if (move.getEnd().getY() == move.getStart().getY() + 2 && move.getEnd().getX() == move.getStart().getX() + 1) {
            counter++;
        }
        if (move.getEnd().getX() == move.getStart().getX() + 2 && move.getEnd().getY() == move.getStart().getY() - 1) {
            counter++;
        }
        if (move.getEnd().getY() == move.getStart().getY() + 2 && move.getEnd().getX() == move.getStart().getX() - 1) {
            counter++;
        }
        if (move.getEnd().getX() == move.getStart().getX() - 2 && move.getEnd().getY() == move.getStart().getY() + 1) {
            counter++;
        }

        if (move.getEnd().getY() == move.getStart().getY() - 2 && move.getEnd().getX() == move.getStart().getX() + 1) {
            counter++;
        }
        if (move.getEnd().getX() == move.getStart().getX() - 2 && move.getEnd().getY() == move.getStart().getY() - 1) {
            counter++;
        }
        if (move.getEnd().getY() == move.getStart().getY() - 2 && move.getEnd().getX() == move.getStart().getX() - 1) {
            counter++;
        }


        return counter;
    }

    private int checkMateVerticalAndHorizontal(Move move) {

        int counter = 0;
        int x = move.getEnd().getX();
        int j = move.getEnd().getY();

        boolean pieceInBetween = false;

        for (int y = move.getEnd().getY() + 1; y < 8; y++) {
            if (board.boxes[x][y].getPiece() != null) {
                Piece piece = board.boxes[x][y].getPiece();
                if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                    if (piece instanceof Queen || piece instanceof Castle) {
                        counter++;
                        break;
                    }
                } else if (board.boxes[x][y].getPiece() != null) {
                    break;
                }
            }
        }

        for (int y = move.getEnd().getY() - 1; y >= 0; y--) {
            if (board.boxes[x][y].getPiece() != null) {
                Piece piece = board.boxes[x][y].getPiece();
                if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                    if (piece instanceof Queen || piece instanceof Castle) {
                        counter++;
                        break;
                    }
                } else if (board.boxes[x][y].getPiece() != null) {
                    break;
                }
            }
        }

        for (int z = move.getEnd().getX() + 1; z < 8; z++) {
            if (board.boxes[z][j].getPiece() != null) {
                Piece piece = board.boxes[z][j].getPiece();
                if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                    if (piece instanceof Queen || piece instanceof Castle) {
                        counter++;
                        break;
                    }
                } else if (board.boxes[z][j].getPiece() != null) {
                    break;
                }
            }
        }

        for (int z = move.getEnd().getX() - 1; z >= 0; z--) {
            if (board.boxes[z][j].getPiece() != null) {
                Piece piece = board.boxes[z][j].getPiece();
                if (piece.isWhite() != move.getEnd().getPiece().isWhite()) {
                    if (piece instanceof Queen || piece instanceof Castle) {
                        counter++;
                        break;
                    }

                } else if (board.boxes[z][j].getPiece() != null) {
                    break;
                }
            }
        }
        return counter;
    }

    private int checkMateKnight(Move move) {

        int counter = 0;

        if (move.getEnd().getX() + 2 < 8 && move.getEnd().getY() + 1 < 8) {
            if (board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() + 1] != null) {
                if (board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() + 1].getPiece() instanceof Knight &&
                        board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() + 1].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }
        if (move.getEnd().getX() + 1 < 8 && move.getEnd().getY() + 2 < 8) {
            if (board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() + 2] != null) {
                if (board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() + 2].getPiece() instanceof Knight &&
                        board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() + 2].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }

        if (move.getEnd().getX() + 2 < 8 && move.getEnd().getY() - 1 >= 0) {
            if (board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() - 1] != null) {
                if (board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() - 1].getPiece() instanceof Knight
                        && board.boxes[move.getEnd().getX() + 2][move.getEnd().getY() - 1].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }

        if (move.getEnd().getX() - 1 >= 0 && move.getEnd().getY() + 2 < 8) {
            if (board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() + 2] != null) {
                if (board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() + 2].getPiece() instanceof Knight
                        && board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() + 2].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }

        if (move.getEnd().getX() - 2 >= 0 && move.getEnd().getY() + 1 < 8) {
            if (board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() + 1] != null) {
                if (board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() + 1].getPiece() instanceof Knight
                        && board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() + 1].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }

        if (move.getEnd().getX() + 1 < 8 && move.getEnd().getY() - 2 >= 0) {
            if (board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() - 2] != null) {
                if (board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() - 2].getPiece() instanceof Knight && board.boxes[move.getEnd().getX() + 1][move.getEnd().getY() - 2].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }
        if (move.getEnd().getX() - 2 >= 0 && move.getEnd().getY() - 1 >= 0) {
            if (board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() - 1] != null) {
                if (board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() - 1].getPiece() instanceof Knight
                        && board.boxes[move.getEnd().getX() - 2][move.getEnd().getY() - 1].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }

        if (move.getEnd().getX() - 1 >= 0 && move.getEnd().getY() - 2 >= 0) {
            if (board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() - 2] != null) {
                if (board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() - 2].getPiece() instanceof Knight
                        && board.boxes[move.getEnd().getX() - 1][move.getEnd().getY() - 2].getPiece().isWhite() != move.getEnd().getPiece().isWhite()) {
                    counter++;
                }
            }
        }
        return counter;
    }


}







































