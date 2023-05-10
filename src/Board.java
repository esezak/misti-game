import java.util.ArrayList;

public class Board {
    private ArrayList<Card> board = new ArrayList<Card>();
    private int boardPoint;

    public Board(Deck deck){//initialises the board
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
    public void addCard(Deck deck){//adds cards from deck
        Card card = deck.transferCard();
        board.add(card);
        BotExpert.cardTracker(card.getNumber());
        addPoint(card.getPoint());
    }
    public void addCard(Card card){//for adding cards to the board from players
        board.add(card);
        BotExpert.cardTracker(card.getNumber());
        addPoint(card.getPoint());
    }
    public Card getTopCard(){//gives the last card on the deck
        if(board.size()>0) {
            return board.get(board.size() - 1);
        }else{
            return new Card("0","0",0);//just in case
        }
    }
    public ArrayList<Card> getBoard(){
        return board;
    }
    public void seeBoard(){//prints board as Board: ... \n
        System.out.print("Board: ");
        for(Card c:board){
            System.out.print(c.getCard()+" ");
        }
        System.out.println("\nPoints on board: "+getBoardPoint());
    }

    public void flushBoard(){
        board.clear();
        boardPoint = 0;
    }
    public int getSize(){
        return board.size();
    }

}
