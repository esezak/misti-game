import java.util.Random;

public class BotNormal extends Player implements Playable{
    public BotNormal(String name){
        setName(name+ " (Normal)");
    }
    private boolean[] okToPlay;
    @Override
    public int play(Board board) {// returns the index of the desired card
        boolean hasJack = false;
        boolean hasSelected = false;
        okToPlay = new boolean[]{true, true, true, true};
        int jackIndex = 0;
        int selectedIndex = 0;
        if (getHand().size() == 1) {//shortcut when we have 1 card left at hand
            return selectedIndex;
        }
        {
            int counter = 0;//local variable
            for (Card c : getHand()) {//if it can take the board it will take it (if board has positive points after played card)
                if (board.getTopCard().getNumber().equals(c.getNumber()) && !c.getNumber().equals("J")) {
                    /*
                    We can take the board but if we play we get negative points,
                    we should avoid playing this card
                    */
                    if (simulatePoint(board, c) < 0) {
                        okToPlay[counter] = false;
                    } else {
                        selectedIndex = counter;
                        hasSelected = true;
                    }
                } else if (c.getNumber().equals("J")) {
                    if (simulatePoint(board, c) < 0) {
                        okToPlay[counter] = false;
                    } else {
                        hasJack = true;
                        jackIndex = counter;//backup plan
                    }
                }
                counter++;
            }

            if (hasSelected) {//this part is for playing if a board can be taken
                System.out.println("Has board taken?");//for debug
                return selectedIndex;
            } else if (hasJack) {
                System.out.println("Has board taken?");//for debug
                return jackIndex;
            }

            for(int i=0;i<getHand().size();i++){
                if(okToPlay[i]){//play the first card that is ok to play
                    return i;
                }
            }
            
            return 0;   // if there are no ok card to play, play the first card
        }
    }
}