/*
Play Card
Get Card
*/


import java.util.ArrayList;

public class Player implements Playable{
    protected ArrayList<Card> hand = new ArrayList<Card>();
    protected ArrayList<Card> cardsTaken = new ArrayList<Card>();
    private String name;
    private int point;
    private boolean lastTake=false;//indicates who last took cards from the board
    public void see(){//mainly for human player and debug
        if(hand.size()!=0){
            for(int i = 0; i<hand.size();i++){
                System.out.print("("+(i+1)+")"+hand.get(i).getCard()+" ");
            }
        }
    }
    public void addCard(Deck deck){
        Card card = deck.transferCard();
        hand.add(card);
    }
    public void addPoint(int point){
        this.point+=point;
    }
    public int getPoint(){
        return point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Card getCard(int index){
        return hand.get(index);
    }
    public void removeCard(int index){
        hand.remove(index);
        hand.trimToSize();
    }
    public int selectedCardNum(int index){//for comparison with the card on deck
        return hand.get(index).getNumber();
    }
    public ArrayList<Card> getHand() {
        return hand;
    }

    public void seeCardsTaken() {
        for(Card c: cardsTaken){
            System.out.print(c.getCard()+", ");
        }
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public void takeCard(Card card){
        cardsTaken.add(card);
    }
    public void takeBoard(Board board){
        for(Card c : board.getBoard()){
            takeCard(c);
            addPoint(board.getBoardPoint());
            board.flushBoard();
        }
    }

    @Override
    public int play(Board board) {
        return 0;
    }
    public void resetLastTake(){//reset this property
        lastTake = false;
    }
    public void lastTookCards(){//find if it took cards last
        lastTake = true;
    }

    public boolean isLastTake() {
        return lastTake;
    }
}
