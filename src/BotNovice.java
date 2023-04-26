import java.util.Random;
public class BotNovice extends Player implements Playable{
    Random random;
    public BotNovice(){
        random = new Random();
        super.setName("Novice Bot");
    }

    @Override
    public int play(Board board) {//plays a random card
        return random.nextInt(getHand().size());
    }
}
