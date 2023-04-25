import java.util.Random;
public class BotNovice extends Player implements Playable{
    Random random;
    public BotNovice(){
        random = new Random();
        super.setName("Novice Bot");
    }

    @Override
    public boolean turn(Board board) {
        int topCardNum = board.getTopCard().getNumber();
        int selectedNum = play();
        if(selectedNum==11 || topCardNum == selectedNum){
            return true;
        }
        return false;
    }

    @Override
    public int play() {//plays a random card
        return random.nextInt(getHand().size());
    }
}
