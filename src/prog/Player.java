package prog;

public class Player {

    enum Color {
        BLACK,
        WHITE
    }

    int playerNum;

    public Player() {

    }

    public Player(Color color, int playerNum) {

    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
}
