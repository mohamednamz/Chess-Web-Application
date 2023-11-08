package Chess;

public class Board {

    Spot[][] boxes;

    public Spot getBox(int x, int y) throws Exception {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("index is out of bounds");
        }
        return boxes[x][y];
    }

    public void resetBoard() {

        boxes = new Spot[28][28];

        boxes[0][0] = new Spot(0, 0, new Castle(true));
        boxes[0][1] = new Spot(0, 1, new Knight(true));
        boxes[0][2] = new Spot(0, 2, new Bishop(true));
        boxes[0][3] = new Spot(0, 3, new Queen(true));
        boxes[0][4] = new Spot(0, 4, new King(true));
        boxes[0][5] = new Spot(0, 5, new Bishop(true));
        boxes[0][6] = new Spot(0, 6, new Knight(true));
        boxes[0][7] = new Spot(0, 7, new Castle(true));

        boxes[1][0] = new Spot(1, 0, new Pawn(true));
        boxes[1][1] = new Spot(1, 1, new Pawn(true));
        boxes[1][2] = new Spot(1, 2, new Pawn(true));
        boxes[1][3] = new Spot(1, 3, new Pawn(true));
        boxes[1][4] = new Spot(1, 4, new Pawn(true));
        boxes[1][5] = new Spot(1, 5, new Pawn(true));
        boxes[1][6] = new Spot(1, 6, new Pawn(true));
        boxes[1][7] = new Spot(1, 7, new Pawn(true));

        //black pieces

        boxes[7][0] = new Spot(7, 0, new Castle(false));
        boxes[7][1] = new Spot(7, 1, new Knight(false));
        boxes[7][2] = new Spot(7, 2, new Bishop(false));
        boxes[7][3] = new Spot(7, 3, new Queen(false));
        boxes[7][4] = new Spot(7, 4, new King(false));
        boxes[7][5] = new Spot(7, 5, new Bishop(false));
        boxes[7][6] = new Spot(7, 6, new Knight(false));
        boxes[7][7] = new Spot(7, 7, new Castle(false));

        boxes[6][0] = new Spot(6, 0, new Pawn(false));
        boxes[6][1] = new Spot(6, 1, new Pawn(false));
        boxes[6][2] = new Spot(6, 2, new Pawn(false));
        boxes[6][3] = new Spot(6, 3, new Pawn(false));
        boxes[6][4] = new Spot(6, 4, new Pawn(false));
        boxes[6][5] = new Spot(6, 5, new Pawn(false));
        boxes[6][6] = new Spot(6, 6, new Pawn(false));
        boxes[6][7] = new Spot(6, 7, new Pawn(false));

        for (int x = 2; x < 6; x++) {
            for (int y = 0; y <= 7; y++) {
                boxes[x][y] = new Spot(x, y, null);
            }
        }


    }


}
