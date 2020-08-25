package prog;

public class PieceMovement {

    private static String invalidMove = "This move is invalid please try again.";

    public PieceMovement() {
    }

    public static void pieceMovement(Piece piece, int newRow, int newColumn) {
        switch (piece.chessType) {
            case Queen:
                queenMovement(piece, newRow, newColumn);
                break;
            case King:
                kingMovement(piece, newRow, newColumn);
                break;
            case Bishop:
                bishopMovement(piece, newRow, newColumn);
                break;
            case Rook:
                rookMovement(piece, newRow, newColumn);
                break;
            case Nknight:
                knightMovement(piece, newRow, newColumn);
                break;
            case Pawn:
                pawnMovement(piece, newRow, newColumn);
        }

    }

    public static void queenMovement(Piece piece, int newRow, int newColumn) {
        if (
                (newColumn == piece.column++ && newRow == piece.row++) ||
                (newColumn == piece.column-- && newRow == piece.row--) ||
                (newColumn == piece.column++ && newRow == piece.row--) ||
                (newColumn == piece.column-- && newRow == piece.row++) ||
                (newColumn == piece.column++ && newRow == piece.row) ||
                (newColumn == piece.column-- && newRow == piece.row) ||
                (newRow == piece.row++ && newColumn == piece.column) ||
                (newRow == piece.row-- && newColumn == piece.column)
        ) {
            piece.column = newColumn;
            piece.row = newRow;
        }

    }

    public static void kingMovement(Piece piece, int newRow, int newColumn) {
        if (
                (newColumn == piece.column++ && newRow == piece.row++) ||
                (newColumn == piece.column-- && newRow == piece.row--) ||
                (newColumn == piece.column++ && newRow == piece.row--) ||
                (newColumn == piece.column-- && newRow == piece.row++) ||
                (newColumn == piece.column++ && newRow == piece.row) ||
                (newColumn == piece.column-- && newRow == piece.row) ||
                (newRow == piece.row++ && newColumn == piece.column) ||
                (newRow == piece.row-- && newColumn == piece.column)
        ) {
            piece.column = newColumn;
            piece.row = newRow;
        }
       // switch(newColumn){
       //     case :
        //}

    }

    public static void bishopMovement(Piece piece, int newRow, int newColumn) {
        if (
                (newColumn == piece.column++ && newRow == piece.row++) ||
                (newColumn == piece.column-- && newRow == piece.row--) ||
                (newColumn == piece.column++ && newRow == piece.row--) ||
                (newColumn == piece.column-- && newRow == piece.row++)
        ) {
            piece.column = newColumn;
            piece.row = newRow;
        }

    }

    public static void knightMovement(Piece piece, int newRow, int newColumn) {
        if (newRow == piece.row + 2 && (newColumn == piece.column - 1 || newColumn == piece.column + 1) || newRow == piece.row - 2 && (newColumn == piece.column - 1 || newColumn == piece.column + 1) || newColumn == piece.column + 2 && (newRow == piece.row - 1 || newRow == piece.row + 1) || newColumn == piece.column - 2 && (newRow == piece.row - 1 || newRow == piece.row + 1)) {
            piece.row = newRow;
            piece.column = newColumn;
        }

    }

    public static void rookMovement(Piece piece, int newRow, int newColumn) {
        if (newRow == piece.row) {
            piece.row = newRow;
        } else if (newColumn == piece.column) {
            piece.column = newColumn;
        } else {
            System.out.println(invalidMove);
        }

    }

    public static void pawnMovement(Piece piece, int newRow, int newColumn) {
        if (piece.isWhite) {
            if (piece.row == 7 && newColumn == piece.column && (newRow == piece.row-- || newRow == piece.row - 2)) {
                piece.row = newRow;
            } else{
                if(newColumn == piece.column && newRow == piece.row--){
                    piece.row = newRow;
                }
            }
        } else {
            if (piece.row == 2 && newColumn == piece.column && (newRow == piece.row++ || newRow == piece.row + 2)) {
                piece.row = newRow;
            }
        }

    }
}
