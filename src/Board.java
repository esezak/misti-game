import java.util.ArrayList;

public class Board {
    private ArrayList<Card> board = new ArrayList<Card>();
    public void addCard(Deck deck){
        deck.transferTopCard(board);
    }
    public String getCard(){
        return board.get(board.size()-1).getCard();
    }
    public ArrayList<Card> getBoard(){
        return board;
    }

}
