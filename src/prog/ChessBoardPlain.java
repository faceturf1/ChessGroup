package prog;

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

    int pieceColumn;
    int pieceRow;
    boolean isWhite; //Tile
    Type chessType;

    Piece(int column, int row, boolean isWhite, Type chessType) {

        pieceColumn = column;
        pieceRow = row;
        this.isWhite = isWhite;
        this.chessType = chessType;

    }
}

class ChessBoard {

    private Piece[][] chessPieces = new Piece[8][8];

    //Adds and stores all the enum types to the 2-D Array to be used to be placed later
    ChessBoard() {
        for (int i = 0; i < chessPieces.length; i++) {
            for (int j = 0; j < chessPieces[i].length; j++) {
                chessPieces[i][j]=null;

            }

        }
        chessPieces[0][3]=new Piece(3, 0, false, Type.Queen);
        chessPieces[7][3]=new Piece(3, 7, true , Type.Queen);

        chessPieces[0][4]=new Piece(4, 0, false, Type.King);
        chessPieces[7][4]=new Piece(4, 7, true , Type.King);

        for (int i = 0; i < 2; i++) {

            chessPieces[0][i*7]=new Piece(i * 7, 0, false, Type.Rook);
            chessPieces[7][i*7]=new Piece(i * 7, 7, true , Type.Rook);

            chessPieces[0][1 + i * 5]=new Piece(1 + i * 5, 0, false, Type.Nknight);
            chessPieces[7][1 + i * 5]=new Piece(1 + i * 5, 7, true , Type.Nknight);

            chessPieces[0][2 + i * 3]=new Piece(2 + i * 3, 0, false, Type.Bishop);
            chessPieces[7][2 + i * 3]=new Piece(2 + i * 3, 7, true , Type.Bishop);
        }

        for (int i = 0; i < 8; i++) {
            chessPieces[1][i]=new Piece(i, 1, false, Type.Pawn); //Since there are more pawns it is easier to loop and place
            chessPieces[6][i]=new Piece(i, 6, true , Type.Pawn);
        }
    }

    public void updateBoard(int newRow ,int newColumn,int oldRow,int oldColumn, boolean isWhite, Type type){
        if(chessPieces[newRow][newColumn]==null){
            chessPieces[newRow][newColumn]= new Piece(newColumn, newRow, isWhite , type);
            chessPieces[oldRow][oldColumn]= null;
        }
        else if((chessPieces[newRow][newColumn]!= null)&&(chessPieces[newRow][newColumn].isWhite!=chessPieces[oldRow][oldColumn].isWhite)){
            chessPieces[newRow][newColumn]= null;
            chessPieces[newRow][newColumn]= new Piece(newColumn, newRow, isWhite , type);
            chessPieces[oldRow][oldColumn]= null;
        }
    }


    @Override
    public String toString() {

        String brdStr = ""; //This is the upper left corner of the printed board
        brdStr += "   A  B  C   D   E   F  G  H \n";
        Piece p;
            for (int row = 0; row < 8; row++) { //Creates Rows
                brdStr +=row + "";
                for (int column = 0; column < 8; column++) { //Creates Columns

                    if (chessPieces[row][column]==null) {
                        brdStr += " ▭";
                    } else {
                        switch (chessPieces[row][column].chessType) {

                            //Prints out pieces to board
                            case Pawn:
                                brdStr += chessPieces[row][column].isWhite ? " ♙" : " ♟";
                                break; //Left is white - right is black
                            case Rook:
                                brdStr += chessPieces[row][column].isWhite ? " ♖" : " ♜";
                                break;
                            case Nknight:
                                brdStr += chessPieces[row][column].isWhite ? " ♘" : " ♞";
                                break;
                            case Bishop:
                                brdStr += chessPieces[row][column].isWhite ? " ♗" : " ♝";
                                break;
                            case Queen:
                                brdStr += chessPieces[row][column].isWhite ? " ♕" : " ♛";
                                break;
                            case King:
                                brdStr += chessPieces[row][column].isWhite ? " ♔" : " ♚";
                                break;

                        }
                    }
                }
                brdStr += " " +  row + "\n";
            }
            brdStr += "   A  B  C  D   E  F   G  H \n";
        return brdStr;

    }

    public Piece[][] getChessPieces() {
        return chessPieces;
    }
}

