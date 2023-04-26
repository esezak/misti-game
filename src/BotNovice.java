import java.util.Random;
public class BotNovice extends Player implements Playable{
    Random random;
    public BotNovice(String name){
        setName(name);
        random = new Random();
    }

    @Override
    public int play(Board board) {//plays a random card
        return random.nextInt(getHand().size());
    }
}
