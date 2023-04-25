import java.util.ArrayList;

public class Board {
    Deck deck = new Deck();
    private ArrayList<Card> board = new ArrayList<Card>();
    public void addCard(Deck deck){
        deck.transferTopCard(board);
    }
    public String getTopCard(){
        return board.get(board.size()-1).getCard();
    }
    public ArrayList<Card> getBoard(){
        return board;
    }
}
