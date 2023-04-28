import java.util.Random;

public class BotNormal extends Player implements Playable{
    public BotNormal(String name){
        setName(name);
    }

    @Override
    public int play(Board board) {// returns the index of the desired card
        //int topCardNum = board.getTopCard().getNumber();
        return 0;
    }
}
