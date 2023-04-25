import java.util.ArrayList;

public class Board {
    private ArrayList<Card> board = new ArrayList<Card>();

    public Board(Deck deck){
        for(int i =0; i<4;i++){
            addCard(deck);
        }
    }
    public void addCard(Deck deck){
        board.add(deck.transferCard());
    }
    public String getCard(){
        return board.get(board.size()-1).getCard();
    }
    public ArrayList<Card> getBoard(){
        return board;
    }
    public void seeBoard(){
        for(Card c:board){
            System.out.print(c.getCard()+" ");
        }
    }

}
