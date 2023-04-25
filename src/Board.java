import java.util.ArrayList;

public class Board {
    private ArrayList<Card> board = new ArrayList<Card>();
    private int boardPoint;

    public Board(Deck deck){
        boardPoint=0;
        for(int i =0; i<4;i++){
            addCard(deck);
        }
    }
    public void addPoint(int point){
        boardPoint+=point;
    }
    public int getBoardPoint(){
        return boardPoint;
    }
    public void addCard(Deck deck){
        Card card = deck.transferCard();
        board.add(card);
        addPoint(card.getPoint());
    }
    public Card getTopCard(){
        if(board.size()>0) {
            return board.get(board.size() - 1);
        }else{
            return new Card(0,"0",0);
        }
    }
    public ArrayList<Card> getBoard(){
        return board;
    }
    public void seeBoard(){
        for(Card c:board){
            System.out.print(c.getCard()+" ");
        }
    }

    public void flushBoard(){
        board.clear();
        boardPoint = 0;
    }
    public void info(){//debugging method
        seeBoard();
        System.out.println("\nBoard point: "+getBoardPoint());
    }

}
