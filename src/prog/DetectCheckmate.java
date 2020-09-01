package prog;

import java.util.ArrayList;

public class DetectCheckmate {
    private boolean checkMate = false;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<String> blackTrue = new ArrayList<>();
    private ArrayList<String> whiteTrue = new ArrayList<>();
    private Piece blackKing = null;
    private Piece whiteKing = null;
    private boolean isWhite;
    private int player = 0;
    private String checkMateMessage = "Checkmate. Game Over. Player" + player + "wins!";

    public String detectCheckmate(ChessBoard board){

        pieces.clear();
        readBoard(board);
        pullOutKing(pieces);
        if (blackKing != null){
            isWhite = false;
            compareToKing(blackKing);
            if(checkmateOrNot(blackTrue)){
                checkMate = true;
                player = 1;
            }
        }
        if (whiteKing != null){
            isWhite = true;
            compareToKing(whiteKing);
            if(checkmateOrNot(whiteTrue)){
                checkMate = true;
             player = 2;
            }
        }

        return checkMateMessage;
    }

    public void readBoard(ChessBoard board){
        for (int row = 0; row < board.getChessPieces().length; row++) {
            for (int column = 0; column < board.getChessPieces()[row].length; column++) {
                pieces.add(board.getChessPieces()[row][column]);
            }
        }
    }
    public void pullOutKing(ArrayList<Piece> pieces){
        for(Piece piece : pieces){
            if(piece.chessType == Type.King){
                if (piece.isWhite){
                    whiteKing = piece;
                }else {
                    blackKing = piece;
                }
            }
        }
    }
    public void compareToKing(Piece king){
        for(Piece piece : pieces){
            switch (piece.chessType){
                case Queen:
                    queenCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
                case King:
                    kingCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
                case Bishop:
                    bishopCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
                case Nknight:
                    knightCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
                case Rook:
                    rookCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
                case Pawn:
                    pawnCapture(piece.pieceColumn, piece.pieceRow, king.pieceColumn, king.pieceRow, isWhite);
                    break;
            }
        }
    }
    public boolean checkmateOrNot(ArrayList<String> trueOrFalse){
        boolean checkMate = false;

        if (pieces.size() == trueOrFalse.size()){
            checkMate = true;
        }

        return checkMate;
    }

    public void queenCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(isWhite){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow) ||
                            (newRow == currentRow + 1 && newColumn == currentColumn) ||
                            (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                whiteTrue.add("true");
            }
        }else{
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow) ||
                            (newRow == currentRow + 1 && newColumn == currentColumn) ||
                            (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                blackTrue.add("true");
            }
        }
    }
    public void kingCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(isWhite){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow) ||
                            (newRow == currentRow + 1 && newColumn == currentColumn) ||
                            (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                whiteTrue.add("true");
            }
        }else{
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow) ||
                            (newRow == currentRow + 1 && newColumn == currentColumn) ||
                            (newRow == currentRow - 1 && newColumn == currentColumn)
            ) {
                blackTrue.add("true");
            }
        }
    }
    public void bishopCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(isWhite){
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1)
            ) {
                whiteTrue.add("true");
            }

        }else {
            if (
                    (newColumn == currentColumn + 1 && newRow == currentRow + 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn + 1 && newRow == currentRow - 1) ||
                            (newColumn == currentColumn - 1 && newRow == currentRow + 1)
            )blackTrue.add("true");
            }

        }

    public void knightCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(isWhite){
            if (
                    (newRow == currentRow + 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                            (newRow == currentRow - 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                            (newColumn == currentColumn + 2 && (newRow == currentRow - 1 || newRow == currentRow + 1)) ||
                            (newColumn == currentColumn - 2 && (newRow == currentRow - 1 || newRow == currentRow + 1))
            ) {
                whiteTrue.add("true");
            }

        }else{
            if (
                    (newRow == currentRow + 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                            (newRow == currentRow - 2 && (newColumn == currentColumn - 1 || newColumn == currentColumn + 1)) ||
                            (newColumn == currentColumn + 2 && (newRow == currentRow - 1 || newRow == currentRow + 1)) ||
                            (newColumn == currentColumn - 2 && (newRow == currentRow - 1 || newRow == currentRow + 1))
            ) {
                blackTrue.add("true");
            }

        }
    }
    public void rookCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite) {
        if(isWhite){
            if (newRow == currentRow) {
                whiteTrue.add("true");
            } else if (newColumn == currentColumn) {
                whiteTrue.add("true");
            }
        }else {
            if (newRow == currentRow) {
                blackTrue.add("true");
            } else if (newColumn == currentColumn) {
                blackTrue.add("true");
            }
        }
    }
    public void pawnCapture(int currentColumn, int currentRow, int newRow, int newColumn, boolean isWhite){
        if(isWhite && newRow == currentRow - 1){
            if(newColumn == currentColumn - 1){
                whiteTrue.add("true");
            }else if(newColumn == currentColumn + 1){
                whiteTrue.add("true");
            }
        }else if(!isWhite && newRow == currentRow + 1){
            if(newColumn == currentColumn - 1){
                blackTrue.add("true");
            }else if(newColumn == currentColumn + 1){
                blackTrue.add("true");
            }
        }

    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public String getCheckMateMessage() {
        return checkMateMessage;
    }
}




