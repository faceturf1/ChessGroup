package prog;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
class Chess {

    public static void main(String[] args) {

            ChessBoard board = new ChessBoard();
            System.out.println(board);

    }
}

enum Type {

    Pawn,
    Rook,
    Nknight,
    Bishop,
    Queen,
    King,

}

class Piece {

    int column;
    int row;
    boolean isWhite; //Tile
    Type chessType;

    Piece(int column, int row, boolean isWhite, Type chessType) {

        this.column = column;
        this.row = row;
        this.isWhite = isWhite;
        this.chessType = chessType;

    }
}

class ChessBoard {

    private Set<Piece> chessPieces = new HashSet<>();

    //Adds and stores all the enum types to the hashset to be used to be placed later
    ChessBoard() {

        chessPieces.add(new Piece(3, 0, false, Type.Queen));
        chessPieces.add(new Piece(3, 7, true , Type.Queen));

        chessPieces.add(new Piece(4, 0, false, Type.King));
        chessPieces.add(new Piece(4, 7, true , Type.King));

        for (int i = 0; i < 2; i++) {

            chessPieces.add(new Piece(i * 7, 0, false, Type.Rook));
            chessPieces.add(new Piece(i * 7, 7, true , Type.Rook));

            chessPieces.add(new Piece(1 + i * 5, 0, false, Type.Nknight));
            chessPieces.add(new Piece(1 + i * 5, 7, true , Type.Nknight));

            chessPieces.add(new Piece(2 + i * 3, 0, false, Type.Bishop));
            chessPieces.add(new Piece(2 + i * 3, 7, true , Type.Bishop));
        }

        for (int i = 0; i < 8; i++) {
            chessPieces.add(new Piece(i, 1, false, Type.Pawn)); //Since there are more pawns it is easier to loop and place
            chessPieces.add(new Piece(i, 6, true , Type.Pawn));
        }
    }

    Piece piecePlacement(int column, int row) {

        for (Piece place : chessPieces) {

            if (place.column == column && place.row == row) {

                return place;
            }
        }
        return null;
    }
    @Override
    public String toString() {

        String brdStr = ""; //This is the upper left corner of the printed board
        brdStr += "  A  B  C  D  E  F  G  H \n";

        for (int row = 0; row < 8; row++) { //Creates Rows
            brdStr += (8 - row) + "";
            for (int column = 0; column < 8; column++) { //Creates Columns
                Piece p = piecePlacement(column, row);
                if (p == null) {
                    brdStr += " ▭";
                } else {
                    switch (p.chessType) {

                        //Prints out pieces to board
                        case Pawn: brdStr += p.isWhite ? " ♙" : " ♟";
                        break; //Left is white - right is black
                        case Rook: brdStr += p.isWhite ? " ♖" : " ♜";
                        break;
                        case Nknight: brdStr += p.isWhite ? " ♘" : " ♞";
                        break;
                        case Bishop: brdStr += p.isWhite ? " ♗" : " ♝";
                        break;
                        case Queen: brdStr += p.isWhite ? " ♕" : " ♛";
                        break;
                        case King: brdStr += p.isWhite ? " ♔" : " ♚";
                        break;

                    }
                }
            }
            brdStr += " " + (8 - row) + "\n";
        }
        brdStr += "  A  B  C  D  E  F  G  H \n";
        return brdStr;
    }
}

